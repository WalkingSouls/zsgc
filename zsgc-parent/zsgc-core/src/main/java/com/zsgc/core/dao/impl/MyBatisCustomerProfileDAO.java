package com.zsgc.core.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.CustomerProfileDAO;
import com.zsgc.core.model.CustomerProfile;

@Repository("customerProfileDAO")
public class MyBatisCustomerProfileDAO extends AbstractDAO<CustomerProfile, java.lang.Integer> implements CustomerProfileDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	  
	@Override
	protected String getNamespace() {
		return "customerProfileDAO";
	}

	@Override
	protected SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Override
	public List<CustomerProfile> getList(Integer pId) {
		return getSqlSessionTemplate().selectList(getNamespace()+".getList",pId);
	}

	@Override
	public List<CustomerProfile> findParentId(Integer pId,Integer parentId) {
		HashMap<String, Integer> params = new HashMap<String,Integer>();
		params.put("pId", pId);
		params.put("parentId", parentId);
		return getSqlSessionTemplate().selectList(getNamespace()+".findParentId",params);
	}

	@Override
	public List<Integer> getIdsList(Integer pId) {
		return getSqlSessionTemplate().selectList(getNamespace()+".getIdsList",pId);
	}

	@Override
	public CustomerProfile getByCusId(Integer bdid) {
		return getSqlSessionTemplate().selectOne(getNamespace()+".getByCusId",bdid);
	}
	
	@Override
	public List<CustomerProfile> SelByALLP(CustomerProfile cp) {
		return sqlSessionTemplate.selectList("customerProfileDAO.selectByALLP",cp);
	}

	@Override
	public List<CustomerProfile> SelByAllC(CustomerProfile cp) {
		return sqlSessionTemplate.selectList("customerProfileDAO.selectByAllC", cp);
	}

	@Override
	public int updateById(CustomerProfile cp) {
		return sqlSessionTemplate.update("customerProfileDAO.updateByPrimaryKeySelective", cp);
	}

	@Override
	public List<CustomerProfile> SelByPidAll(CustomerProfile cp) {
		return sqlSessionTemplate.selectList("customerProfileDAO.SelByPidAll",cp);
	}

	@Override
	public int insertCP(CustomerProfile cp) {
		return sqlSessionTemplate.insert("customerProfileDAO.insertSelective", cp);
	}

/*	@Override
	public String SelByAllO(int customerId) {
		CustomerProfile customerProfile = sqlSessionTemplate.selectOne("customerProfileDao.selectByPrimaryKey", customerId);
		return customerProfile.getOther();
	}*/
}