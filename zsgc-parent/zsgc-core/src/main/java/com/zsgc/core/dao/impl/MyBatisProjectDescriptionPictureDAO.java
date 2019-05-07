package com.zsgc.core.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.ProjectDescriptionPictureDAO;
import com.zsgc.core.model.ProjectDescriptionPicture;
@Repository("projectDescriptionPictureDAO")
public class MyBatisProjectDescriptionPictureDAO extends AbstractDAO<ProjectDescriptionPicture, java.lang.Integer> implements ProjectDescriptionPictureDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	protected String getNamespace() {
		// TODO Auto-generated method stub
		return "projectDescriptionPictureDAO";
	}

	@Override
	protected SqlSessionTemplate getSqlSessionTemplate() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate;
	}

	@Override
	public List<ProjectDescriptionPicture> getList(Integer desId) {
		return getSqlSessionTemplate().selectList(getNamespace()+".getList",desId);
	}


	@Override
	public List<ProjectDescriptionPicture> selByDesId(ProjectDescriptionPicture pdp) {
		return sqlSessionTemplate.selectList("projectDescriptionPictureDAO.selectByDesId",pdp);
	}

	@Override
	public int updateById(ProjectDescriptionPicture pdp) {	
		return sqlSessionTemplate.update("projectDescriptionPictureDAO.updateByPrimaryKeySelective", pdp);
	}
	
	@Override
	public int insertPDP(ProjectDescriptionPicture pdp) {
		
		return sqlSessionTemplate.insert("projectDescriptionPictureDAO.insertSelective", pdp);
	}

	@Override
	public int insertMore(List<ProjectDescriptionPicture> list) {
		return sqlSessionTemplate.insert("projectDescriptionPictureDAO.insertMore", list);
	}

}
