package com.zsgc.core.dao;

import java.util.Date;
import java.util.List;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.Task;
import com.zsgc.core.model.TaskRecovery;
import com.zsgc.core.utils.PageBean;

public interface TaskDAO extends DAO<Task, java.lang.Integer>{

	List<Task> getList(Task task);
	
	PageBean<TaskRecovery> selPage(int uId, PageBean<TaskRecovery> page);
	
	int selAllTaskRecovery(int uId);

	Integer getCount(Integer uId, Integer warehouseType, Date date, Integer task_type,Integer taskState);

	Integer getCountBegEnd(Integer uId, Integer warehouseType, Date beginTime, Date endTime, Integer taskType, Integer taskState);

	Task getTodayTask(Integer uId, Integer pId,Date date);

	List<Task> getTasksByPidTimes(Integer uId, Integer pId, Integer warehouseType, Date beginDate, Date endDate);

	Integer updateTaskByPid(Task task);
}