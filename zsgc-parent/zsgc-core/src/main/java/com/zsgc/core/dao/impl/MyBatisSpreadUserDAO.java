package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.SpreadUserDAO;
import com.zsgc.core.model.SpreadUser;

@Repository("spreadUserDAO")
public class MyBatisSpreadUserDAO extends AbstractDAO<SpreadUser, java.lang.Integer> implements SpreadUserDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "spreadUserDAO";
    }
}
