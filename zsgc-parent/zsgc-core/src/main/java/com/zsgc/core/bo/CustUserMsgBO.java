package com.zsgc.core.bo;

import com.zsgc.core.model.CustUserMsg;
import com.zsgc.core.utils.PageBean;

public interface CustUserMsgBO {
	PageBean<CustUserMsg>  getAllMsg(int uid,int index,int pageNum,int pageSize);
	int changeAllMsg(CustUserMsg custUserMsg);
	int changeOneMsg(CustUserMsg custUserMsg);
}
