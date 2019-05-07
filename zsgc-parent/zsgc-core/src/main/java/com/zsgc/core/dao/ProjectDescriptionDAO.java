package com.zsgc.core.dao;

import java.util.List;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.ProjectDescription;

public interface ProjectDescriptionDAO  extends DAO<ProjectDescription,java.lang.Integer> {

	List<ProjectDescription> getList(Integer pId);

	List<ProjectDescription> selByPid(ProjectDescription pd);
	
	int updateById(ProjectDescription pd);
	
	int insertPD(ProjectDescription pd);
	
	ProjectDescription selOneByPid(ProjectDescription pd);
}
