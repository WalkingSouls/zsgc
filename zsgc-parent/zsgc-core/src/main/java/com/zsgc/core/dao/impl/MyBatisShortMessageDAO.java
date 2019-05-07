package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.ShortMessageDAO;
import com.zsgc.core.model.ShortMessage;

@Repository("shortMessageDAO")
public class MyBatisShortMessageDAO extends AbstractDAO<ShortMessage, java.lang.Integer> implements ShortMessageDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "shortMessageDAO";
    }
}
