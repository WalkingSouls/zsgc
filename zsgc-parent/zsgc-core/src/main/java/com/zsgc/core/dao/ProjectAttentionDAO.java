package com.zsgc.core.dao;


import java.util.List;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.ProjectAttention;

public interface ProjectAttentionDAO  extends DAO<ProjectAttention, java.lang.Integer> {

	Integer getAttentionState(Integer getuId, Integer getpId);

	List<Integer> getpIdList(Integer uId, Integer attention);

	Integer updateProAtt(ProjectAttention proAtt);

	
}
