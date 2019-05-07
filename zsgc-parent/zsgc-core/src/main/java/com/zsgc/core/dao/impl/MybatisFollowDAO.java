package com.zsgc.core.dao.impl;


import java.util.Date;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.FollowDAO;
import com.zsgc.core.model.Follow;

@Repository("followDAO")
public class MybatisFollowDAO extends AbstractDAO<Follow,java.lang.Integer> implements FollowDAO {
	 @Autowired
	    private SqlSessionTemplate sqlSessionTemplate;

	    @Override
	    protected SqlSessionTemplate getSqlSessionTemplate() {
	        return sqlSessionTemplate;
	    }

	    @Override
	    protected String getNamespace() {
	        return "followDAO";
	    }

		@Override
		public Integer getFollowNum(Integer uId) {
			return getSqlSessionTemplate().selectOne(getNamespace()+".getFollowNum",uId);
		}

		@Override
		public Integer getFollowType(Integer myId, Integer uId) {
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("myId", myId);
			params.put("uId", uId);
			return getSqlSessionTemplate().selectOne(getNamespace()+".getFollowType",params);
		}

		@Override
		public Integer changeAtten(Integer myId, Integer uId, int followType,Date update) {
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("myId", myId);
			params.put("uId", uId);
			params.put("followType", followType);
			params.put("update", update);
			return getSqlSessionTemplate().update(getNamespace()+".changeAtten",params);
		}
	

}
