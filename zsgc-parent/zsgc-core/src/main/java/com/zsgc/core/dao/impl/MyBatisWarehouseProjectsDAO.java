
package com.zsgc.core.dao.impl;

import com.calanger.common.dao.AbstractDAO; 
import com.zsgc.core.dao.WarehouseProjectsDAO; 
import com.zsgc.core.model.WarehouseProjects;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository;

@Repository("warehouseProjectsDAO") 
public class MyBatisWarehouseProjectsDAO extends AbstractDAO<WarehouseProjects,java.lang.Integer> implements
WarehouseProjectsDAO{

	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;

	@Override 
	protected SqlSessionTemplate getSqlSessionTemplate() { 
		return sqlSessionTemplate; 
	}

	@Override 
	protected String getNamespace() { 
		return "warehouseProjectsDAO"; 
	}

	@Override
	public List<Integer> getAllpId(Integer delState, Integer warehouseType) {
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("delState", delState);
		params.put("warehouseType",warehouseType);
		return getSqlSessionTemplate().selectList(getNamespace()+".getAllpId",params);
	}

	@Override
	public List<Integer> getHotpIds(Integer delState, Integer warehouseType) {
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("delState", delState);
		params.put("warehouseType",warehouseType);
		return getSqlSessionTemplate().selectList(getNamespace()+".getHotpIds",params);
	}

	@Override
	public List<Integer> getProByIndustry(Integer delState, Integer warehouseType,String industry) {
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("delState", delState);
		params.put("warehouseType",warehouseType);
		params.put("industry",industry);
		return getSqlSessionTemplate().selectList(getNamespace()+".getProByIndustry",params);
	}

	@Override
	public List<WarehouseProjects> getProsByuId(Integer delState, Integer warehouseType,Integer uId) {
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("delState", delState);
		params.put("warehouseType",warehouseType);
		params.put("uId",uId);
		return getSqlSessionTemplate().selectList(getNamespace()+".getProsByuId",params);
	}

	@Override
	public Integer updateFolNum(int pId,int folNumChange) {
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("pId",pId);
		params.put("folNumChange",folNumChange);
		return getSqlSessionTemplate().update(getNamespace()+".updateFolNum",params);
	}

	@Override
	public Integer getSuccessCount(Integer uId, Integer warehouseType,Date date) {
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("uId",uId);
		params.put("warehouseType",warehouseType);
		params.put("delState", 1);
		params.put("date", date);
		return getSqlSessionTemplate().selectOne(getNamespace()+".getSuccessCount",params);
	}

	@Override
	public Integer getSuccessCountBegEnd(Integer uId, Integer warehouseType, Date beginTime, Date endTime) {
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("uId",uId);
		params.put("warehouseType",warehouseType);
		params.put("delState", 1);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		return getSqlSessionTemplate().selectOne(getNamespace()+".getSuccessCountBegEnd",params);
	}

}
