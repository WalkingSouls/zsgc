package com.zsgc.core.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.calanger.common.bo.BO;
import com.zsgc.core.model.Follow;
import com.zsgc.core.model.ProjectMarket;
import com.zsgc.core.model.UcenterMember;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.PageBean;

public interface ProjectMarketBO extends BO<WarehouseProjects, java.lang.Integer> {
	/**
	 * 查看项目并分页
	 */
	PageBean<ProjectMarket> getProjects(Integer uId, ArrayList<Integer> pId, int pageNum, int pageSize);

	/**
	 * 得到关注项目列表
	 */
	ArrayList<Integer> getAttenList(Integer uId);

	/**
	 * 得到所有项目列表
	 */
	ArrayList<Integer> getAllPros();

	/**
	 * 得到热门项目列表
	 */
	ArrayList<Integer> getHotProjects();

	/**
	 * 根据行业得到项目列表
	 */
	ArrayList<Integer> getProByIndustry(String industry);

	/**
	 * ta的主页
	 * 
	 * @param myId
	 * @param uId
	 * @return
	 */
	Map<String, Object> taProjects(Integer myId, Integer uId);

	/**
	 * 对ta关注/取消关注
	 * 
	 * @param uId
	 * @param pId
	 * @param attention
	 */
	void attenProject(Integer uId, Integer pId, Integer attention);

	/**
	 * 根据id获得用户对象
	 * 
	 * @param uId
	 * @return
	 */
	UcenterMember getUM(Integer uId);
	
	/**
	 * 根据用户id查看粉丝数
	 * @param uId
	 * @return
	 */
	Integer getFollowNum(Integer uId);

	/**
	 * 根据用户id查看对另一个用户的关注状态
	 * @param myId
	 * @param uId
	 * @return
	 */
	Integer getFollowType(Integer myId, Integer uId);
	
	/**
	 * 插入关注
	 * @param follow
	 */
	void insertFollow(Follow follow);
	
	/**
	 * 更改关注状态
	 * @param uId
	 * @param myId
	 * @param i
	 * @param update
	 */
	void changeAtten(Integer uId, Integer myId, Integer followType, Date update);

	/**
	 * 取消关注删除数据
	 * @param foll
	 */
	void removeFollow(Follow foll);

}
