package com.zsgc.core.dao.impl;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.ActivityAddressDAO;
import com.zsgc.core.model.ActivityAddress;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("activityAddressDAO")
public class MybatisActivityAddressDAO extends AbstractDAO<ActivityAddress,java.lang.Integer> implements ActivityAddressDAO{

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "activityAddressDAO";
    }
}
