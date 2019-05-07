
package com.zsgc.core.dao.impl;

import com.calanger.common.dao.AbstractDAO; 
import com.zsgc.core.dao.TaskDAO;
import com.zsgc.core.model.Task;
import com.zsgc.core.model.TaskRecovery;
import com.zsgc.core.utils.PageBean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository;

@Repository("taskDAO") 
public class MyBatisTaskDAO extends AbstractDAO<Task,java.lang.Integer> implements
TaskDAO{

	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;

	@Override 
	protected SqlSessionTemplate getSqlSessionTemplate() { 
		return sqlSessionTemplate; 
	}

	@Override 
	protected String getNamespace() { 
		return "taskDAO"; 
	}

	@Override
	public PageBean<TaskRecovery> selPage(int uId, PageBean<TaskRecovery> page) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", uId);
		map.put("count", (page.getPageNum()-1)*page.getPageSize());
		map.put("PageSize",page.getPageSize());
		List<TaskRecovery> list = sqlSessionTemplate.selectList("taskDAO.selTaskRecovery", map);
		page.setList(list);
		return page;
	}

	@Override
	public int selAllTaskRecovery(int uId) {
		return sqlSessionTemplate.selectOne("taskDAO.selAllTaskRecovery",uId);
	}


	@Override
	public List<Task> getList(Task task) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("condition", task);  
		return getSqlSessionTemplate().selectList(getNamespace()+".getList",params);
	}
	
	@Override
	public Integer getCount(Integer uId, Integer warehouseType, Date date, Integer taskType, Integer taskState) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("uId", uId);
		params.put("warehouseType", warehouseType);
		params.put("date", date);
		params.put("taskType", taskType);
		params.put("taskState", taskState);
		return getSqlSessionTemplate().selectOne(getNamespace()+".getCount",params);
	}

	@Override
	public Integer getCountBegEnd(Integer uId, Integer warehouseType, Date beginTime, Date endTime, Integer taskType,
			Integer taskState) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("uId", uId);
		params.put("warehouseType", warehouseType);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("taskType", taskType);
		params.put("taskState", taskState);
		return getSqlSessionTemplate().selectOne(getNamespace()+".getCountBegEnd",params);
	}

	@Override
	public Task getTodayTask(Integer uId, Integer pId, Date date) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("uId", uId);
		params.put("pId", pId);
		params.put("date", date);
		return getSqlSessionTemplate().selectOne(getNamespace()+".getTodayTask",params);
	}

	@Override
	public List<Task> getTasksByPidTimes(Integer uId, Integer pId, Integer warehouseType, Date beginDate,
			Date endDate) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("uId", uId);
		params.put("pId", pId);
		params.put("warehouseType", warehouseType);
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		return getSqlSessionTemplate().selectList(getNamespace()+".getTasksByPidTimes",params);
	}

	@Override
	public Integer updateTaskByPid(Task task) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("entity", task);
		return getSqlSessionTemplate().update(getNamespace()+".updateTaskByPid",params);
	}

}
