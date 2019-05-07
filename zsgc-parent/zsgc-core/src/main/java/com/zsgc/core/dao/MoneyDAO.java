package com.zsgc.core.dao;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.Money;
import com.zsgc.core.model.UcenterMember;

import java.math.BigDecimal;

public interface MoneyDAO extends DAO<Money, java.lang.Integer> {

    /**
     * 
     * @return
     */
    BigDecimal sumInvestTotal(UcenterMember condition);
}
