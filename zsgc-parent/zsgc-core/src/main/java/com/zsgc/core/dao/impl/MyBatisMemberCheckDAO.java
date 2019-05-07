package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.MemberCheckDAO;
import com.zsgc.core.model.MemberCheck;

@Repository("memberCheckDAO")
public class MyBatisMemberCheckDAO extends AbstractDAO<MemberCheck, java.lang.Integer> implements MemberCheckDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "memberCheckDAO";
    }
}
