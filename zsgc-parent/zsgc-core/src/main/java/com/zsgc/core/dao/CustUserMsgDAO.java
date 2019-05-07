package com.zsgc.core.dao;

import java.util.Date;
import java.util.List;

import com.zsgc.core.model.CustUserMsg;
import com.zsgc.core.utils.PageBean;

public interface CustUserMsgDAO {
	int insertMore(List<CustUserMsg> msg_list);
	int insertOne(CustUserMsg msg);	
	PageBean<CustUserMsg> selAllMsgByOrderC(CustUserMsg custUserMsg,PageBean<CustUserMsg> page);
	PageBean<CustUserMsg> selAllMsgByOrderR(CustUserMsg custUserMsg,PageBean<CustUserMsg> page);
	int updateAllMsg(CustUserMsg custUserMsg);
	int updateOneMsg(CustUserMsg custUserMsg);
	int selAllCount(CustUserMsg custUserMsg);
	int updateMsgPush(long id,Date updatedAt);
	List<CustUserMsg> selAllMsgPush(int status);
	//物理删除
	int delByid(long id);
	//根据id 查
	CustUserMsg selByid(long id);
	
}
