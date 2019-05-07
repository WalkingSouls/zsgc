package com.zsgc.core.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.ProjectMarketBO;
import com.zsgc.core.dao.FollowDAO;
import com.zsgc.core.dao.ProjectAttentionDAO;
import com.zsgc.core.dao.UcenterMemberDAO;
import com.zsgc.core.dao.UserExtendDAO;
import com.zsgc.core.dao.WarehouseProjectsDAO;
import com.zsgc.core.model.Follow;
import com.zsgc.core.model.ProjectAttention;
import com.zsgc.core.model.ProjectMarket;
import com.zsgc.core.model.ProjectShow;
import com.zsgc.core.model.UcenterMember;
import com.zsgc.core.model.UserExtend;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.PageBean;

@Service("projectMarketBO")
public class DefaultProjectMarketBO extends AbstractBO<WarehouseProjects, java.lang.Integer>
		implements ProjectMarketBO {
	@Autowired
	private WarehouseProjectsDAO warehouseProjectsDAO;

	@Autowired
	private UcenterMemberDAO ucenterMemberDAO;

	@Autowired
	private ProjectAttentionDAO projectAttentionDAO;

	@Autowired
	private UserExtendDAO userExtendDAO;
	
	@Autowired
	private FollowDAO followDAO;
	
	@Override
	protected DAO<WarehouseProjects, Integer> getDAO() {
		return warehouseProjectsDAO;
	}

	/**
	 * 查看项目并分页
	 */
	@SuppressWarnings("unused")
	@Override
	public PageBean<ProjectMarket> getProjects(Integer uId, ArrayList<Integer> pIdList, int pageNum, int pageSize) {
		PageBean<ProjectMarket> pageBean = new PageBean<ProjectMarket>(pageNum, pageSize);
		pageBean.setTotalRecord(pIdList.size());
		int pageStart = pageNum * pageSize;
		pageBean.totalPage();
		int endFlag = 0;
		if (pIdList != null) {
			ArrayList<ProjectMarket> proMarList = new ArrayList<ProjectMarket>();
			for (int i = pageStart; i < pIdList.size(); i++) {
				Integer pId = pIdList.get(i);
				endFlag++;

				ProjectMarket proMarTemp = new ProjectMarket();
				WarehouseProjects warehouse = warehouseProjectsDAO.get(pId);
				UserExtend ue = new UserExtend();
				ue.setuId(uId);
				ue.setpId(pId);
				UserExtend userExtend = userExtendDAO.selByUidAndPid(ue);
				if (userExtend != null) {
					proMarTemp.setIsCollection(1);
				} else {
					proMarTemp.setIsCollection(0);
				}
				if (warehouse.getWarehouseType() != 1) {
					continue;
				}
				proMarTemp.setpId(warehouse.getpId());
				proMarTemp.setProjectName(warehouse.getProjectName());
				proMarTemp.setFollowNum(warehouse.getFollowNum());
				proMarTemp.setScore(warehouse.getScore());
				proMarTemp.setuId(warehouse.getuId());

				UcenterMember ucenterMember = ucenterMemberDAO.get(warehouse.getuId());
				if (ucenterMember != null) {
					proMarTemp.setAvatarPath(ucenterMember.getAvatarPath());
					proMarTemp.setUserName(ucenterMember.getUsername());
					proMarTemp.setIntroduction(ucenterMember.getSignature());
				}
				Integer attenState = projectAttentionDAO.getAttentionState(uId, warehouse.getpId());
				if (attenState == null) {
					proMarTemp.setAttenState(0);
				} else {
					proMarTemp.setAttenState(attenState);
				}
				proMarList.add(proMarTemp);
				if (endFlag >= pageSize)
					break;
			}
			pageBean.setList(proMarList);
			return pageBean;
		} else {
			return null;
		}

	}

	/**
	 * 得到关注项目列表
	 */
	@Override
	public ArrayList<Integer> getAttenList(Integer uId) {
		ArrayList<Integer> pIdList = (ArrayList<Integer>) projectAttentionDAO.getpIdList(uId, 1);
		return pIdList;
	}

	/**
	 * 得到所有项目列表
	 */
	@Override
	public ArrayList<Integer> getAllPros() {
		ArrayList<Integer> ids = (ArrayList<Integer>) warehouseProjectsDAO.getAllpId(1, 1);
		return ids;
	}

	/**
	 * 得到热门项目列表
	 */
	@Override
	public ArrayList<Integer> getHotProjects() {
		ArrayList<Integer> ids = (ArrayList<Integer>) warehouseProjectsDAO.getHotpIds(1, 1);
		return ids;
	}

	/**
	 * ta的主页
	 * 
	 * @param myId
	 * @param uId
	 * @return
	 */
	@Override
	public ArrayList<Integer> getProByIndustry(String industry) {
		ArrayList<Integer> ids = (ArrayList<Integer>) warehouseProjectsDAO.getProByIndustry(1, 1, industry);
		return ids;
	}

	/**
	 * ta的主页
	 * 
	 * @param myId
	 * @param uId
	 * @return
	 */
	@Override
	public Map<String, Object> taProjects(Integer myId, Integer uId) {
		HashMap<String, Object> prosMap = new HashMap<String, Object>();
		List<WarehouseProjects> pros = warehouseProjectsDAO.getProsByuId(1, 1, uId);
		ArrayList<ProjectShow> proSho = new ArrayList<ProjectShow>();
		int proFollows = 0;
		if (pros != null) {
			for (WarehouseProjects proTemp : pros) {
				ProjectShow proShoTemp = new ProjectShow();
				proShoTemp.setProjectName(proTemp.getProjectName());
				proShoTemp.setFollowNum(proTemp.getFollowNum());
				proShoTemp.setScore(proTemp.getScore());
				UserExtend ue = new UserExtend();
				ue.setuId(myId);
				ue.setpId(proTemp.getpId());
				UserExtend userExtend = userExtendDAO.selByUidAndPid(ue);
				if (userExtend != null) {
					proShoTemp.setIsCollection(1);
				} else {
					proShoTemp.setIsCollection(0);
				}
				proFollows += proTemp.getFollowNum();
				proSho.add(proShoTemp);
			}
		}
		prosMap.put("proSho", proSho);
		prosMap.put("proFollows", proFollows);
		return prosMap;
	}

	/**
	 * 对ta关注/取消关注
	 * 
	 * @param uId
	 * @param pId
	 * @param attention
	 */
	@Override
	public void attenProject(Integer uId, Integer pId, Integer attention) {
		if (projectAttentionDAO.getAttentionState(uId, pId) == null) {
			ProjectAttention proAtt = createProAtt(uId, pId, attention);
			proAtt.setAttention(1);
			projectAttentionDAO.insert(proAtt);
			warehouseProjectsDAO.updateFolNum(pId, 1);
		} else if (attention == 0) {
			ProjectAttention proAtt = createProAtt(uId, pId, attention);
			proAtt.setAttention(1);
			projectAttentionDAO.updateProAtt(proAtt);
			warehouseProjectsDAO.updateFolNum(pId, 1);
		} else if (attention == 1) {
			ProjectAttention proAtt = createProAtt(uId, pId, attention);
			proAtt.setAttention(0);
			projectAttentionDAO.updateProAtt(proAtt);
			warehouseProjectsDAO.updateFolNum(pId, -1);
		}
	}

	private ProjectAttention createProAtt(Integer uId, Integer pId, Integer attention) {
		ProjectAttention proAtt = new ProjectAttention();
		proAtt.setuId(uId);
		proAtt.setpId(pId);
		proAtt.setCreateDate(new Date());
		proAtt.setUpdateDate(new Date());
		return proAtt;
	}

	/**
	 * 根据id获得用户对象
	 * 
	 * @param uId
	 * @return
	 */
	@Override
	public UcenterMember getUM(Integer uId) {
		return ucenterMemberDAO.get(uId);
	}
	
	/**
	 * 根据用户id查看粉丝数
	 * @param uId
	 * @return
	 */
	@Override
	public Integer getFollowNum(Integer uId) {
		return followDAO.getFollowNum(uId);
	}
	
	/**
	 * 根据用户id查看对另一个用户的关注状态
	 * @param myId
	 * @param uId
	 * @return
	 */
	@Override
	public Integer getFollowType(Integer myId, Integer uId) {
		return followDAO.getFollowType(myId, uId);
	}
	
	/**
	 * 插入关注
	 * @param follow
	 */
	@Override
	public void insertFollow(Follow follow) {
		followDAO.insert(follow);
	}

	/**
	 * 更改关注状态
	 * @param uId
	 * @param myId
	 * @param i
	 * @param update
	 */
	@Override
	public void changeAtten(Integer uId, Integer myId, Integer followType, Date update) {
		followDAO.changeAtten(uId, myId, followType, update);
	}

	/**
	 * 取消关注删除数据
	 * @param foll
	 */
	@Override
	public void removeFollow(Follow foll) {
		followDAO.remove(foll);
	}

}
