package com.zsgc.core.dao;

import java.util.List;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.CustomerProfile;

public interface CustomerProfileDAO extends DAO<CustomerProfile,java.lang.Integer>{
	

	List<CustomerProfile> getList(Integer pId);

	List<CustomerProfile> findParentId(Integer pId, Integer parentId);

	List<Integer> getIdsList(Integer pId);

	CustomerProfile getByCusId(Integer bdid);

	//根据项目pid 获取所有父节点 
	List<CustomerProfile> SelByALLP(CustomerProfile cp);
	
	//根据客户customerId 获取所有子客户
	List<CustomerProfile> SelByAllC(CustomerProfile cp);
	
	/*//根据 customerId 获取所有其他客户
	String SelByAllO(int customerId);*/
	
	int updateById(CustomerProfile cp);
	
	List<CustomerProfile> SelByPidAll(CustomerProfile cp);
	
	int insertCP(CustomerProfile cp);
	
	
}