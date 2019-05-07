package com.zsgc.core.dao;

import java.util.List;

import com.zsgc.core.model.UserExtend;

public interface UserExtendDAO {
	List<UserExtend> selAllByUid(int uid);

	int insertByScore(UserExtend ue);

	int insertByCollection(UserExtend ue);

	int updateByPrimaryKey(UserExtend ue);

	UserExtend selByUidAndPid(UserExtend ue);

	UserExtend selByNowPId(UserExtend ue);
	
	int upDatePR (UserExtend ue);
	
	int upDateMarketPR(UserExtend ue);
	
	List<UserExtend> selAllByPid(int pid);
}
