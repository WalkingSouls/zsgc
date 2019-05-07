
package com.zsgc.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.ProjectAttentionDAO;
import com.zsgc.core.model.ProjectAttention;

@Repository("projectAttentionDAO") 
public class MyBatisProjectAttentionDAO extends AbstractDAO<ProjectAttention,java.lang.Integer> implements
ProjectAttentionDAO{

	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;

	@Override 
	protected SqlSessionTemplate getSqlSessionTemplate() { 
		return sqlSessionTemplate; 
	}

	@Override 
	protected String getNamespace() { 
		return "projectAttentionDAO"; 
	}

	@Override
	public Integer getAttentionState(Integer uId, Integer pId) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("uId", uId);
		params.put("pId", pId);
		return getSqlSessionTemplate().selectOne(getNamespace()+".getAttentionState",params);
	}

	@Override
	public List<Integer> getpIdList(Integer uId,Integer attention) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("uId", uId);
		params.put("attention", attention);
		return getSqlSessionTemplate().selectList(getNamespace()+".getpIdList",params);
	}

	@Override
	public Integer updateProAtt(ProjectAttention proAtt) {
		return getSqlSessionTemplate().update(getNamespace()+".updateProAtt",proAtt);
	}


}
