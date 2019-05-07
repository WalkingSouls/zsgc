package com.zsgc.core.dao;

import java.util.List;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.DesPicture;

public interface DesPictureDAO extends DAO<DesPicture, java.lang.Integer>{

	List<DesPicture> getList(DesPicture desPic);
//    int deleteByPrimaryKey(Integer picId);
//
//    int insert(DesPicture record);
//
//    int insertSelective(DesPicture record);
//
//    DesPicture selectByPrimaryKey(Integer picId);
//
//    int updateByPrimaryKeySelective(DesPicture record);
//
//    int updateByPrimaryKey(DesPicture record);
}