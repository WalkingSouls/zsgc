package com.zsgc.core.bo;

import com.zsgc.core.model.CustUserMsg;

public interface JPushBO {
	// 直接推送
	void ContentPush(CustUserMsg c);
	//插入缓存和mysql定时推送
	void insMysqlAndCache(CustUserMsg msg,int tId);
	//更新缓存和 mysql
	void updateMysqlAndCache(CustUserMsg msg,int tId);
	//物理删除msg
	void delMsg(int tId);
}
