package com.zsgc.core.dao;

import java.util.List;

import com.zsgc.core.model.Notice;

public interface NoticeDAO {
	int insertMore(List<Notice> notice_list);
	int insertOne(Notice notice);
}
