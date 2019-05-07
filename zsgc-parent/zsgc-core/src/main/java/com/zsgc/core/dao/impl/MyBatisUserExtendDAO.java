package com.zsgc.core.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zsgc.core.dao.UserExtendDAO;
import com.zsgc.core.model.UserExtend;

@Repository("userExtendDAO")
public class MyBatisUserExtendDAO implements UserExtendDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<UserExtend> selAllByUid(int uid) {
		return sqlSessionTemplate.selectList("myBatisUserExtendDAO.selAllByUid", uid);
	}
	@Override
	public int updateByPrimaryKey(UserExtend ue) {
		return sqlSessionTemplate.update("myBatisUserExtendDAO.updateByPrimaryKey", ue);
	}

	@Override
	public int insertByScore(UserExtend ue) {
		return sqlSessionTemplate.insert("myBatisUserExtendDAO.insertByScore", ue);
	}
	@Override
	public UserExtend selByUidAndPid(UserExtend ue) {

		return sqlSessionTemplate.selectOne("myBatisUserExtendDAO.selByUidAndPid", ue);
	}
	@Override
	public int insertByCollection(UserExtend ue) {
		return sqlSessionTemplate.insert("myBatisUserExtendDAO.insertByCollection", ue);
	}
	
	@Override
	public UserExtend selByNowPId(UserExtend ue) {

		return sqlSessionTemplate.selectOne("myBatisUserExtendDAO.selByNowPId", ue);
	}
	@Override
	public int upDatePR(UserExtend ue) {
		
		return sqlSessionTemplate.update("myBatisUserExtendDAO.upDatePR", ue);
	}
	@Override
	public int upDateMarketPR(UserExtend ue) {
		return sqlSessionTemplate.update("myBatisUserExtendDAO.upDateMarketPR", ue);
	}
	@Override
	public List<UserExtend> selAllByPid(int pid) {
		return sqlSessionTemplate.selectList("myBatisUserExtendDAO.selAllByPid", pid);
	}
}
