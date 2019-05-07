package com.zsgc.core.bo.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.FollowBO;
import com.zsgc.core.dao.FollowDAO;
import com.zsgc.core.model.Follow;

@Service("followBO")
public class DefaultFollowBO extends AbstractBO<Follow,java.lang.Integer> implements FollowBO {
	
	@Autowired
   private FollowDAO followDao;
   
	@Override
	protected DAO<Follow, Integer> getDAO() {
		return followDao;
	}

}
