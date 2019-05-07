
package com.zsgc.core.dao.impl;

import com.calanger.common.dao.AbstractDAO; 
import com.zsgc.core.dao.TaskDescriptionDAO;
import com.zsgc.core.model.TaskDescription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository;

@Repository("taskDescriptionDAO") 
public class MyBatisTaskDescriptionDAO extends AbstractDAO<TaskDescription,java.lang.Integer> implements
TaskDescriptionDAO{

	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;

	@Override 
	protected SqlSessionTemplate getSqlSessionTemplate() { 
		return sqlSessionTemplate; 
	}

	@Override 
	protected String getNamespace() { 
		return "taskDescriptionDAO"; 
	}

	@Override
	public List<TaskDescription> getList(TaskDescription tdt) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("condition", tdt);  
		return getSqlSessionTemplate().selectList(getNamespace()+".getList",params);
	}

	@Override
	public String selByTaskId(int taskId) {
		return sqlSessionTemplate.selectOne("taskDescriptionDAO.selByTaskId",taskId);
	}
}
