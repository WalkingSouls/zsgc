package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.ActivityInvitationDAO;
import com.zsgc.core.model.ActivityInvitation;

@Repository("activityInvitationDAO")
public class MyBatisActivityInvitationDAO extends AbstractDAO<ActivityInvitation, java.lang.Integer> implements ActivityInvitationDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "activityInvitationDAO";
    }
}
