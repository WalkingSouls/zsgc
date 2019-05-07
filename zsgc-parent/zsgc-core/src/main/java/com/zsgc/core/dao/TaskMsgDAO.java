package com.zsgc.core.dao;

import java.util.Date;

import com.zsgc.core.model.TaskMsg;

public interface TaskMsgDAO {
	//根据TaskId 获得 MsgId
	long selMsgByTid(int tId);
	
	//根据MsgId 更新Bean 
	int updateByMid(long mId,Date updateAt);
	
	//插入Bean
	int insertOne(TaskMsg tm);
	
	//物理删除
	 int delByMid(long mId);
	
}
