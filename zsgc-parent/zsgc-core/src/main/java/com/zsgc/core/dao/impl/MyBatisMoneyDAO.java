package com.zsgc.core.dao.impl;

import com.google.common.collect.Maps;
import com.zsgc.core.dao.MoneyDAO;
import com.zsgc.core.model.Money;
import com.zsgc.core.model.UcenterMember;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;

import java.math.BigDecimal;
import java.util.Map;

@Repository("moneyDAO")
public class MyBatisMoneyDAO extends AbstractDAO<Money, java.lang.Integer> implements MoneyDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "moneyDAO";
    }

    @Override
    public BigDecimal sumInvestTotal(UcenterMember condition) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("condition", condition);
        return getSqlSessionTemplate().selectOne(getNamespace() + ".sumInvestTotal",params);
    }
}
