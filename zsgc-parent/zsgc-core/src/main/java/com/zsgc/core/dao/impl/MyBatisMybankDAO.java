package com.zsgc.core.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.calanger.common.dao.OrderBy;
import com.google.common.collect.Maps;
import com.zsgc.core.dao.MybankDAO;
import com.zsgc.core.model.BankInfo;
import com.zsgc.core.model.Mybank;

@Repository("mybankDAO")
public class MyBatisMybankDAO extends AbstractDAO<Mybank, java.lang.Integer> implements MybankDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "mybankDAO";
    }

    @Override
    public List<BankInfo> getBankList(Mybank mybankVO, OrderBy orderBy) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("condition", mybankVO);
        params.put("orderBy", orderBy);
        return getSqlSessionTemplate().<BankInfo>selectList(getNamespace() + ".getBankList",params);
    }
}
