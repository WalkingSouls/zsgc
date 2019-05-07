package com.zsgc.core.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zsgc.core.dao.CustUserMsgDAO;
import com.zsgc.core.model.CustUserMsg;
import com.zsgc.core.utils.PageBean;
@Repository("custUserMsgDAO")
public class MyBatisCustUserMsgDAO implements CustUserMsgDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insertMore(List<CustUserMsg> list) {
		return sqlSessionTemplate.insert("custUserMsgDAO.insertMore", list);
	}

	@Override
	public int insertOne(CustUserMsg msg) {
		return sqlSessionTemplate.insert("custUserMsgDAO.insertSelective", msg);
	}
	
	@Override
	public PageBean<CustUserMsg> selAllMsgByOrderC(CustUserMsg custUserMsg,PageBean<CustUserMsg> page) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bean", custUserMsg);
		map.put("count", (page.getPageNum()-1)*page.getPageSize());
		map.put("PageSize",page.getPageSize());
		page.setList(sqlSessionTemplate.selectList("custUserMsgDAO.selAllMsgByOrderC", map));
		return page;
	}

	@Override
	public int updateAllMsg(CustUserMsg custUserMsg) {
		return sqlSessionTemplate.update("custUserMsgDAO.updateAllMsg", custUserMsg);
	}

	@Override
	public int updateOneMsg(CustUserMsg custUserMsg) {
		return sqlSessionTemplate.update("custUserMsgDAO.updateByPrimaryKeySelective", custUserMsg);
	}

	@Override
	public PageBean<CustUserMsg> selAllMsgByOrderR(CustUserMsg custUserMsg,PageBean<CustUserMsg> page) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bean", custUserMsg);
		map.put("count", (page.getPageNum()-1)*page.getPageSize());
		map.put("PageSize",page.getPageSize());
		List<Object> selectList = sqlSessionTemplate.selectList("custUserMsgDAO.selAllMsgByOrderR", map);
		System.out.println(selectList.size());
		page.setList(sqlSessionTemplate.selectList("custUserMsgDAO.selAllMsgByOrderR", map));
		return page;
	}

	@Override
	public int selAllCount(CustUserMsg custUserMsg) {
		return sqlSessionTemplate.selectOne("custUserMsgDAO.selAllCount",custUserMsg);
	}

	@Override
	public int updateMsgPush(long id ,Date updatedAt) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("updatedAt", updatedAt);
		return sqlSessionTemplate.update("custUserMsgDAO.updateMsgPush",id);
	}

	@Override
	public List<CustUserMsg> selAllMsgPush(int status) {
		return sqlSessionTemplate.selectList("custUserMsgDAO.selAllMsgPush",status);
	}

	@Override
	public int delByid(long id) {
		return sqlSessionTemplate.delete("custUserMsgDAO.delByid",id);
	}

	@Override
	public CustUserMsg selByid(long id) {
		return sqlSessionTemplate.selectOne("custUserMsgDAO.selectByPrimaryKey",id);
	}
	
}
