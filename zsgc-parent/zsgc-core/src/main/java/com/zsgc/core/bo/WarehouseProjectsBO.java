package com.zsgc.core.bo;

import java.util.ArrayList;

import com.calanger.common.bo.BO;
import com.zsgc.core.model.CustomerProfile;
import com.zsgc.core.model.WarehouseProjects;

public interface WarehouseProjectsBO extends BO<WarehouseProjects, java.lang.Integer> {
	/**
	 * 发布新项目
	 */
	String createNewPro(WarehouseProjects warehouse);

	/**
	 * 根据项目id查看客户图谱
	 */
	WarehouseProjects getProByProId(Integer pId);

	/**
	 * 更新项目信息
	 */
	Integer updateProByProId(WarehouseProjects warehouse);

	/**
	 * 查看项目是否重名
	 */
	WarehouseProjects getByProName(WarehouseProjects warehouse);

	/**
	 * 查看所有客户图谱
	 */
	ArrayList<CustomerProfile> getAllCusPro(Integer uId, Integer pId);

	/**
	 * 新建客户
	 */
	Integer createNewCP(CustomerProfile cusPro, Integer relatedId, Integer relationType);

	/**
	 * 更新客户图谱
	 */
	Integer updateCusPro(CustomerProfile cusPro, Integer relatedId, Integer relationType);

	/**
	 * 删除客户图谱
	 */
	Integer deleteCusPro(Integer pId, Integer customerId);

	/**
	 * 查看客户谱图是否重名
	 */
	Integer getByCusName(Integer getpId, String string);

	/**
	 * 查看单个客户图谱
	 */
	CustomerProfile getByCusId(Integer pId, Integer customerId);

}
