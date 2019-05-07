package com.zsgc.core.dao;

import java.util.List;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.TaskDescription;

public interface TaskDescriptionDAO extends DAO<TaskDescription, java.lang.Integer>{

	List<TaskDescription> getList(TaskDescription tdt);
//    int deleteByPrimaryKey(Integer desId);
//
//    int insert(TaskDescription record);
//
//    int insertSelective(TaskDescription record);
//
//    TaskDescription selectByPrimaryKey(Integer desId);
//
//    int updateByPrimaryKeySelective(TaskDescription record);
//
//    int updateByPrimaryKey(TaskDescription record);
	
	String  selByTaskId(int taskId);
}