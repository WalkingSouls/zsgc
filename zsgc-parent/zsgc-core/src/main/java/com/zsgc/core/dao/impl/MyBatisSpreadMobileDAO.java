package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.SpreadMobileDAO;
import com.zsgc.core.model.SpreadMobile;

@Repository("spreadMobileDAO")
public class MyBatisSpreadMobileDAO extends AbstractDAO<SpreadMobile, java.lang.Integer> implements SpreadMobileDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "spreadMobileDAO";
    }
}
