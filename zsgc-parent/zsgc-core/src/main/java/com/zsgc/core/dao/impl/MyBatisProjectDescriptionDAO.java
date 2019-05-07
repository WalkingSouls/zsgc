package com.zsgc.core.dao.impl;

import java.util.Collections;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;

import com.zsgc.core.dao.ProjectDescriptionDAO;

import com.zsgc.core.model.ProjectDescription;

@Repository("projectDescriptionDAO")
public class MyBatisProjectDescriptionDAO extends AbstractDAO<ProjectDescription, java.lang.Integer> implements ProjectDescriptionDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	  
	@Override
	protected String getNamespace() {
		// TODO Auto-generated method stub
		return "projectDescriptionDAO";
	}

	@Override
	protected SqlSessionTemplate getSqlSessionTemplate() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate;
	}

	@Override
	public List<ProjectDescription> getList(Integer pId) {
		
		return getSqlSessionTemplate().selectList(getNamespace()+".getList",pId);
	}

	@Override
	public List<ProjectDescription> selByPid(ProjectDescription pd) {
		List<ProjectDescription> selectList = sqlSessionTemplate.selectList("projectDescriptionDAO.selectByPid",pd);
		//selectList = Collections.emptyList();
		return selectList;
	}

	@Override
	public int updateById(ProjectDescription pd) {
		return sqlSessionTemplate.update("projectDescriptionDAO.updateByPrimaryKeySelective", pd);
	}

	@Override
	public int insertPD(ProjectDescription pd) {
		return sqlSessionTemplate.insert("projectDescriptionDAO.insertSelective", pd);
	}

	@Override
	public ProjectDescription selOneByPid(ProjectDescription pd) {
		
		return sqlSessionTemplate.selectOne("projectDescriptionDAO.selOneByPid",pd);
	}
}