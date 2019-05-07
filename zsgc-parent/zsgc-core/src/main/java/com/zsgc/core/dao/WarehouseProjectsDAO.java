package com.zsgc.core.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.WarehouseProjects;

public interface WarehouseProjectsDAO  extends DAO<WarehouseProjects, java.lang.Integer> {

	List<Integer> getAllpId(Integer delState, Integer warehouseType);

	List<Integer> getHotpIds(Integer delState, Integer warehouseType);

	List<Integer> getProByIndustry(Integer delState, Integer warehouseType,@Param("industry")String industry);

	List<WarehouseProjects> getProsByuId(Integer delState, Integer warehouseType, Integer uId2);

	Integer updateFolNum(int pId,int folNumChange);

	Integer getSuccessCount(Integer uId, Integer warehouseType, Date date);

	Integer getSuccessCountBegEnd(Integer uId, Integer warehouseType, Date beginTime, Date endTime);
	
}
