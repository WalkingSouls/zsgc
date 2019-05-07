
package com.zsgc.core.dao.impl;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.TaskTimeDAO;
import com.zsgc.core.model.TaskTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository;

@Repository("taskTimeDAO") 
public class MyBatisTaskTimeDAO extends AbstractDAO<TaskTime,java.lang.Integer> implements
TaskTimeDAO{

	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;

	@Override 
	protected SqlSessionTemplate getSqlSessionTemplate() { 
		return sqlSessionTemplate; 
	}

	@Override 
	protected String getNamespace() { 
		return "taskTimeDAO"; 
	}

	@Override
	public List<TaskTime> getList(TaskTime tt) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("condition", tt);
		return getSqlSessionTemplate().selectList(getNamespace()+".getList",params);
	}
}
