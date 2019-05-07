package com.zsgc.core.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.TestBO;
import com.zsgc.core.dao.TestDAO;
import com.zsgc.core.model.Test;

@Service("testBO")
public class DefaultTestBO extends AbstractBO<Test,java.lang.Integer> implements TestBO{
	@Autowired
	private TestDAO testDAO;

	@Override
	protected DAO<Test, Integer> getDAO() {
		return testDAO;
	}

	@Override
	public void insert(int i) {
		Test test = new Test();
		test.setA(i);
		testDAO.insert(test);
	}
	

}
