package com.zsgc.core.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zsgc.core.dao.NoticeDAO;
import com.zsgc.core.model.Notice;

@Repository("noticeDAO")
public class MyBatisNoticeDAO implements NoticeDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insertMore(List<Notice> list) {
		return sqlSessionTemplate.insert("noticeDAO.insertMore", list);
	}

	@Override
	public int insertOne(Notice notice) {
		return sqlSessionTemplate.insert("noticeDAO.insertSelective", notice);
	}

}
