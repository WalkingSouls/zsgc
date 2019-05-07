package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.TestDAO;
import com.zsgc.core.model.Test;
@Repository("testDAO")
public class MybatisTestDAO extends AbstractDAO<Test,java.lang.Integer> implements TestDAO{
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	@Override
	protected String getNamespace() {
		return "testDAO";
	}

	@Override
	protected SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

}
