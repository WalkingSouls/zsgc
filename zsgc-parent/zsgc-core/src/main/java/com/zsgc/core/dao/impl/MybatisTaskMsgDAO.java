package com.zsgc.core.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zsgc.core.dao.TaskMsgDAO;
import com.zsgc.core.model.TaskMsg;

import ch.qos.logback.core.db.dialect.HSQLDBDialect;

@Repository("taskMsgDAO")
public class MybatisTaskMsgDAO implements TaskMsgDAO{
	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public long selMsgByTid(int tId) {
		return sqlSessionTemplate.selectOne("taskMsgDAO.selMsgByTid",tId);
	}

	@Override
	public int updateByMid(long mId,Date updateAt) {
		Map<String, Object> map = new HashMap<>();
		map.put("updateAt", updateAt);
		map.put("mId", mId);
		return sqlSessionTemplate.update("taskMsgDAO.updateByMid",map);
	}

	@Override
	public int insertOne(TaskMsg tm) {
		return sqlSessionTemplate.insert("taskMsgDAO.insertSelective",tm);
	}

	@Override
	public int delByMid(long mId) {
		return sqlSessionTemplate.delete("taskMsgDAO.delByMid",mId);
	}

}
