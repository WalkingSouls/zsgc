package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.AppVersionDAO;
import com.zsgc.core.model.AppVersion;

@Repository("appVersionDAO")
public class MyBatisAppVersionDAO extends AbstractDAO<AppVersion, java.lang.Integer> implements AppVersionDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "appVersionDAO";
    }
}
