package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.ApiTokenDAO;
import com.zsgc.core.model.ApiToken;

@Repository("apiTokenDAO")
public class MyBatisApiTokenDAO extends AbstractDAO<ApiToken, java.lang.Integer> implements ApiTokenDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "apiTokenDAO";
    }
}
