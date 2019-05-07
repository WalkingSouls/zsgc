package com.zsgc.core.bo;

import java.util.List;

import com.zsgc.core.model.CustUserMsg;
import com.zsgc.core.model.UserExtend;
import com.zsgc.core.model.WarehouseProjects;

public interface MsgBO {
	void setOneMsg(CustUserMsg msg); 
	void setMoreMsg(List<CustUserMsg> msg_list);
	void commentProjectsMsg(WarehouseProjects wp, int new_uid, int type, double score, double new_score);
	void delProjectsMsg(WarehouseProjects wp, String delCause,List<UserExtend> selAllByPid);
	void changeProjectsMsg(WarehouseProjects wp ,List<UserExtend> selAllByPid);
	void collectionProjectsMsg(WarehouseProjects wp, int old_uid);
}
