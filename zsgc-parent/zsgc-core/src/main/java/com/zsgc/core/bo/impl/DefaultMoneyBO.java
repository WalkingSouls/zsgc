package com.zsgc.core.bo.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.MoneyBO;
import com.zsgc.core.dao.CashDAO;
import com.zsgc.core.dao.MoneyDAO;
import com.zsgc.core.model.Money;
import com.zsgc.core.model.UcenterMember;

@Service("moneyBO")
public class DefaultMoneyBO extends AbstractBO<Money, java.lang.Integer> implements MoneyBO {
    @Autowired
    private MoneyDAO moneyDAO;
    @Autowired
    private CashDAO cashDAO;

    @Override
    protected DAO<Money, java.lang.Integer> getDAO() {
        return moneyDAO;
    }


    /**
     * @Title: DefaultMoneyBO.java
     * @Prject: zsgc-core
     * @Description: 根据用户获取资金数据
     * @version: V1.0 
     * @param uid
     * @return
     */
    public Money getByUid(int uid){
        Money moneyVO = new Money();
        moneyVO.setUid(uid);
        return moneyDAO.get(moneyVO);
    }

    /**
     * 得到用户资金信息
     * @param uid 用户id
     * @return
     */
    public Map<String, BigDecimal> getUserMoneyInfo(int uid) {
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        Money money = getByUid(uid);
        //账户余额
        map.put("balance", money.getBalance());
        //累计收益
        map.put("totleInterestMoney", money.getTotleInterestMoney());
        //资产总额
        BigDecimal totleProperty = money.getBalance().add(money.getFreeze()).add(money.getReceivableInvestMoney()).add(money.getReceivableInterestMoney());
        map.put("totleProperty", totleProperty);
        //待收本金
        map.put("receivableInvestMoney", money.getReceivableInvestMoney());
        //待收收益
        map.put("receivableInterestMoney", money.getReceivableInterestMoney());
        //待收金额
        BigDecimal totleReceivableMoney = money.getReceivableInvestMoney().add(money.getReceivableInterestMoney());
        map.put("totleReceivableMoney", totleReceivableMoney);
        //冻结总额
        map.put("freeze", money.getFreeze());
        return map;
    }

    /**
     *
     * @return
     */
    @Override
    public BigDecimal sumInvestTotal(UcenterMember codition) {
        return moneyDAO.sumInvestTotal(codition);
    }
}
