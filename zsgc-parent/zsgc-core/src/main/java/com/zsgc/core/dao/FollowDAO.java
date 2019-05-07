package com.zsgc.core.dao;

import java.util.Date;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.Follow;

public interface FollowDAO extends DAO<Follow,java.lang.Integer>{

	Integer getFollowNum(Integer uId);

	Integer getFollowType(Integer myId, Integer uId);

	Integer changeAtten(Integer myId, Integer uId, int followType, Date update);

}
