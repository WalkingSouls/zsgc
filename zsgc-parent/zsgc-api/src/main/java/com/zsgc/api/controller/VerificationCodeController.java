package com.zsgc.api.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calanger.common.dao.Expressions;
import com.calanger.common.dao.OrderBy;
import com.calanger.common.util.RandomUtils;
import com.google.common.base.Strings;
import com.zsgc.api.dto.ApiResult;
import com.zsgc.core.bo.SecurityTicketBO;
import com.zsgc.core.bo.ShortMessageBO;
import com.zsgc.core.bo.UcenterMemberBO;
import com.zsgc.core.chuanglan.utils.SmsSendUtil;
import com.zsgc.core.model.SecurityTicket;
import com.zsgc.core.model.ShortMessage;
import com.zsgc.core.model.UcenterMember;

/**
 * 短信验证码
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/verification-code")
public class VerificationCodeController {
    @Autowired
    private ShortMessageBO shortMessageBO;
    @Autowired
    private UcenterMemberBO ucenterMemberBO;
    @Autowired
    private SecurityTicketBO securityTicketBO;

    /**
     * 发送短信验证码
     * @param mobile 手机号码
     * @param type 1注册  2忘记登录密码 3绑定银行卡 4暂定 5提现 6设置交易密码 7忘记/修改交易密码 8充值  9修改手机号码(旧号码) 10修改手机号码(新号码)
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> get(String mobile, int type, String ticketId, String picCode, Integer clientType) {
        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (Strings.isNullOrEmpty(mobile)) {
            return new ApiResult<Map<String, Object>>(101, "请输入手机号码");
        }

        if (clientType != null && (type == 9 || type == 10)) {
      /*      if (Strings.isNullOrEmpty(ticketId)) {
                throw new IllegalArgumentException("ticketId is null");
            }
            if (Strings.isNullOrEmpty(picCode)) {
                return new ApiResult<Map<String, Object>>(101, "请输入图形验证码");
            }

            SecurityTicket securityTicketVO = new SecurityTicket();
            securityTicketVO.setId(ticketId);
            securityTicketVO.and(Expressions.gt("expiry_time", new Date()));
            securityTicketVO.setStatus(1);

            SecurityTicket securityTicket = securityTicketBO.get(securityTicketVO);

            if (securityTicket == null || !securityTicket.getTicketData().equalsIgnoreCase(picCode)) {
                return new ApiResult<Map<String, Object>>(201, "图形验证码错误");
            }*/
        }

        if (type != 1 && type != 3 && type != 6 && type != 7 && type != 10) {
            UcenterMember ucenterMemberVO = new UcenterMember();
            ucenterMemberVO.setMobile(mobile);
            UcenterMember ucenterMember = ucenterMemberBO.get(ucenterMemberVO);
            if (ucenterMember == null) {
                return new ApiResult<Map<String, Object>>(0, "success");
            }
        }

        Date date = new Date();

        ShortMessage condition = new ShortMessage();
        condition.setMobile(mobile);
        condition.setType(type);
        Date today = DateUtils.truncate(date, Calendar.DATE);
        condition.and(Expressions.ge("createdAt", today));

        List<ShortMessage> shortMessageList = shortMessageBO.find(condition, new OrderBy().add("id", false));
        if (!shortMessageList.isEmpty()) {
            ShortMessage latest = shortMessageList.get(0);
            if ((System.currentTimeMillis() - latest.getCreatedAt().getTime()) / 1000 < 60) {
                return new ApiResult<Map<String, Object>>(202, "一分钟之内只能发送一条短信验证码");
            }
        }

        String code = RandomUtils.getRandomString("1234567890", 4);
        if (type == 1) {
            UcenterMember ucenterMemberCon = new UcenterMember();
            ucenterMemberCon.setMobile(mobile);
            UcenterMember ucenterMembers = ucenterMemberBO.get(ucenterMemberCon);
            if (ucenterMembers != null) {
                return new ApiResult<Map<String, Object>>(204, "该手机号码已注册");
            }
            String msg = "【云泽信息】亲爱的[" + mobile +  "]，您正在注册招商工厂会员，验证码是: " + code +  "，为了您的账户安全，请不要泄露给其他人。";
            SmsSendUtil.send(msg, mobile);
        } else {
            String msg = "【云泽信息】尊敬的用户，您的验证码是:" + code +  "，为了您的账户安全，请不要泄露给其他人。";
            SmsSendUtil.send(msg, mobile);
        }

        ShortMessage shortMessage = new ShortMessage();
        shortMessage.setMobile(mobile);
        shortMessage.setCode(code);
        shortMessage.setStatus(0);
        shortMessage.setType(type);
        shortMessage.setValidTime(300);
        shortMessage.setRequestTimes(0);
        shortMessage.setStatus(0);
        shortMessageBO.add(shortMessage);

        return new ApiResult<Map<String, Object>>(0, "success", data);
    }

    /**
     * 验证短信验证码
     * @param mobile 手机号码
     * @param code 短信验证码
     * @param type 1注册  2忘记密码 ...
     * @return
     */
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Map<String, Object>> verify(String mobile, String code, int type) {
        int errorCode = shortMessageBO.isValidCode(mobile, code, type);
        if (errorCode != 0) {
            String errorMsg = "";
            switch (errorCode) {
            case 1000:
                errorMsg = "请重新获取验证码";
                break;
            case 1001:
                errorMsg = "验证码错误";
                break;
            case 1002:
                errorMsg = "验证码失效";
                break;
            }
            return new ApiResult<Map<String, Object>>(errorCode, errorMsg);
        }

        return new ApiResult<Map<String, Object>>(0, "success");
    }
}
