package com.zsgc.core.dao;

import java.util.List;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.ProjectDescriptionPicture;

public interface ProjectDescriptionPictureDAO  extends DAO<ProjectDescriptionPicture,java.lang.Integer> {

	List<ProjectDescriptionPicture> getList(Integer desId);
	
	List<ProjectDescriptionPicture> selByDesId(ProjectDescriptionPicture pdp);
	
	int updateById(ProjectDescriptionPicture pdp);

	int insertPDP(ProjectDescriptionPicture pdp);
	
	int insertMore(List<ProjectDescriptionPicture> msg_list);
}
