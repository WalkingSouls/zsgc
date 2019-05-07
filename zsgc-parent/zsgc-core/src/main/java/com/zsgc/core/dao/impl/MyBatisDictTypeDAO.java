package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.DictTypeDAO;
import com.zsgc.core.model.DictType;

@Repository("dictTypeDAO")
public class MyBatisDictTypeDAO extends AbstractDAO<DictType, java.lang.Integer> implements DictTypeDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "dictTypeDAO";
    }
}
