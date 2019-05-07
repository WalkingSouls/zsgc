package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zsgc.core.dao.BaseInfoMsgDAO;

@Repository("baseInfoMsgDAO")
public class MyBatisBaseInfoMsgDAO implements BaseInfoMsgDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public String selByMsgKey(String msgKey) {
		return sqlSessionTemplate.selectOne("baseInfoMsgDAO.selByMsgKey",msgKey);
	}

}
