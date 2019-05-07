package com.zsgc.core.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.calanger.common.dao.Expressions;
import com.calanger.common.dao.OrderBy;
import com.google.common.collect.Lists;
import com.zsgc.core.bo.ShortMessageBO;
import com.zsgc.core.dao.ShortMessageDAO;
import com.zsgc.core.model.ShortMessage;

@Service("shortMessageBO")
public class DefaultShortMessageBO extends AbstractBO<ShortMessage, java.lang.Integer> implements ShortMessageBO {
    @Autowired
    private ShortMessageDAO shortMessageDAO;

    @Override
    protected DAO<ShortMessage, java.lang.Integer> getDAO() {
        return shortMessageDAO;
    }

    @Override
    @Transactional
    public void insertShortMessage(ShortMessage shortMessage) {
        //修改之前未验证的短信为失效
        ShortMessage shortMessageVO = new ShortMessage();
        ShortMessage invalidMessage = new ShortMessage();
        invalidMessage.setStatus(2);
        shortMessageVO.setType(shortMessage.getType());
        shortMessageVO.setStatus(0);
        shortMessageDAO.update(invalidMessage, shortMessageVO);

        //新增短信验证码
        shortMessageDAO.insert(shortMessage);
    }

    @Override
    @Transactional
    public void updateMessageTimes(ShortMessage shortMessageVO) {
        OrderBy orderBy = new OrderBy();
        orderBy.add("id", false);
        List<ShortMessage> messageList = shortMessageDAO.find(shortMessageVO, orderBy, 1, 1);
        ShortMessage shortMessage = new ShortMessage();
        if (!messageList.isEmpty()) {
            ShortMessage shortMessageBean = messageList.get(0);
            if (shortMessageBean.getRequestTimes() >= 10) {
                shortMessage.setStatus(2);
            } else {
                shortMessage.setRequestTimes(shortMessageBean.getRequestTimes() + 1);
            }
            shortMessageDAO.update(shortMessage, shortMessageVO);
        }
    }

    @Transactional
    @Override
    public void add(ShortMessage shortMessage) {
        // 修改之前未验证的短信状态修改为已失效
        ShortMessage condition = new ShortMessage();
        condition.setType(shortMessage.getType());
        condition.setStatus(0);
        condition.setMobile(shortMessage.getMobile());

        List<ShortMessage> shortMessageList = shortMessageDAO.find(condition);
        if (!shortMessageList.isEmpty()) {
            List<Integer> idList = Lists.newArrayList();
            for (ShortMessage e : shortMessageList) {
                idList.add(e.getId());
            }

            condition = new ShortMessage();
            condition.and(Expressions.in ("id", idList));

            ShortMessage update = new ShortMessage();
            update.setStatus(2);
            shortMessageDAO.update(update, condition);
        }

        shortMessageDAO.insert(shortMessage);
    }

    /**
     * 验证短信验证码
     * 
     * @param mobile 手机号码
     * @param code   短信验证码
     * @param type   1注册, 2忘记密码 ...
     * @return
     *      0 验证通过
     *      1000 请重新获取验证码
     *      1001 验证码错误
     *      1002 验证码失效
     */
    @Override
    public int isValidCode(String mobile, String code, Integer type) {
        int result = 0;

        ShortMessage condition = new ShortMessage();
        condition.setMobile(mobile);
        condition.setStatus(0); // 初始
        condition.setType(type);
        ShortMessage shortMessage = shortMessageDAO.get(condition);
        if (shortMessage != null) {
            if (shortMessage.getCode().equals(code)) {
                if ((System.currentTimeMillis() - shortMessage.getCreatedAt().getTime()) / 1000 > 300 ||
                        shortMessage.getRequestTimes() > 11) { // 验证码失效
                    ShortMessage update = new ShortMessage();
                    update.setStatus(2);
                    shortMessageDAO.update(update, condition);
                    result = 1002;
                }
            } else { // 验证码错误
                ShortMessage update = new ShortMessage();
                update.setRequestTimes(shortMessage.getRequestTimes() + 1);
                shortMessageDAO.update(update, shortMessage.getId());
                result = 1001;
            }
        } else { // 请重新获取验证码
            result = 1000;
        }

        return result;
    }

}
