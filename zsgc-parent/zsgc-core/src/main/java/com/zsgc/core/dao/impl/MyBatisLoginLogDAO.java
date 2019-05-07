package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.LoginLogDAO;
import com.zsgc.core.model.LoginLog;

@Repository("loginLogDAO")
public class MyBatisLoginLogDAO extends AbstractDAO<LoginLog, java.lang.Integer> implements LoginLogDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "loginLogDAO";
    }
}
