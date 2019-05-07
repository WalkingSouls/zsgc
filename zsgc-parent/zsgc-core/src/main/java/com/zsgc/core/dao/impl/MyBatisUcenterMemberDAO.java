package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.UcenterMemberDAO;
import com.zsgc.core.model.UcenterMember;

@Repository("ucenterMemberDAO")
public class MyBatisUcenterMemberDAO extends AbstractDAO<UcenterMember, java.lang.Integer> implements UcenterMemberDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "ucenterMemberDAO";
    }
}
