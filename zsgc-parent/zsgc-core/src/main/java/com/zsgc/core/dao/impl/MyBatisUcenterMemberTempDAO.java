package com.zsgc.core.dao.impl;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.UcenterMemberTempDAO;
import com.zsgc.core.model.UcenterMember;

@Repository("ucenterMemberTempDAO")
public class MyBatisUcenterMemberTempDAO extends AbstractDAO<UcenterMember, java.lang.Integer> implements UcenterMemberTempDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "ucenterMemberTempDAO";
    }

	@Override
	public Integer updateProOrTaks(UcenterMember ucenterMember) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("ucenterMember", ucenterMember);
		return getSqlSessionTemplate().update(getNamespace()+".updateProOrTask",params);
	}

	@Override
	public Integer updatePro(UcenterMember ucenterMember) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("ucenterMember", ucenterMember);
		return getSqlSessionTemplate().update(getNamespace()+".updatePro",params);
	}

	@Override
	public Integer updatetask(UcenterMember ucenterMember) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("ucenterMember", ucenterMember);
		return getSqlSessionTemplate().update(getNamespace()+".updatetask",params);
	}
}
