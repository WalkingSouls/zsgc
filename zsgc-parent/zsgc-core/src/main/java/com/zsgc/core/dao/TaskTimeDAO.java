package com.zsgc.core.dao;

import java.util.List;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.TaskTime;

public interface TaskTimeDAO extends DAO<TaskTime, java.lang.Integer>{

	List<TaskTime> getList(TaskTime tt);
	
}