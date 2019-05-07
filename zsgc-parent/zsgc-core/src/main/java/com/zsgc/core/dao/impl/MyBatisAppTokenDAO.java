package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.AppTokenDAO;
import com.zsgc.core.model.AppToken;

@Repository("appTokenDAO")
public class MyBatisAppTokenDAO extends AbstractDAO<AppToken, java.lang.Integer> implements AppTokenDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "appTokenDAO";
    }
}
