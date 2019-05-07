package com.zsgc.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsgc.api.dto.ApiResult;
import com.zsgc.core.bo.DictCacheBO;
import com.zsgc.core.bo.ProjectMarketBO;
import com.zsgc.core.model.Follow;
import com.zsgc.core.model.ProjectMarket;
import com.zsgc.core.model.ProjectShow;
import com.zsgc.core.model.UcenterMember;
import com.zsgc.core.utils.PageBean;

/**
 * 项目集市
 * 
 * @author lyd
 *
 */
@Controller
@RequestMapping("/market")
public class ProjectMarketController {

	@Autowired
	private ProjectMarketBO projectMarketBO;

	@Autowired
	private DictCacheBO dictCacheBO;

	/**
	 * 查看项目并分页
	 * 
	 * @param uId
	 * @param pId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageBean<ProjectMarket> getProjects(Integer uId, ArrayList<Integer> pId, Integer pageNum, Integer pageSize) {

		PageBean<ProjectMarket> projects = projectMarketBO.getProjects(uId, pId, pageNum, pageSize);
		return projects;
	}

	/**
	 * 我关注的项目
	 * 
	 * @param uId      用户id
	 * @param pageNum  分页页码
	 * @param pageSize 分页大小
	 * @return
	 */
	public ApiResult<Map<String, Object>> attentionProjects(Integer uId, Integer pageNum, Integer pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		UcenterMember ucenterMember = projectMarketBO.getUM(uId);
		if (ucenterMember == null || ucenterMember.getStatus() != 1) {
			return new ApiResult<Map<String, Object>>(1, "用户不存在或状态异常");
		} else {
			ArrayList<Integer> pIdList = projectMarketBO.getAttenList(uId);
			PageBean<ProjectMarket> projects = getProjects(uId, pIdList, pageNum, pageSize);
			int size = pIdList.size();
			params.put("projects", projects);
			if (size == 0) {
				return new ApiResult<Map<String, Object>>(2, "暂时没有关注任何项目");
			}
			return new ApiResult<Map<String, Object>>(0, "查询成功", params);
		}
	}

	/**
	 * 
	 * @param type     all 全部 hot 热门 my 我关注的
	 * @param uId      用户id
	 * @param pageNum  分页当前页码
	 * @param pageSize 分页大小
	 * @param industry 行业，-1 默认加载所有
	 * @return
	 */
	@RequestMapping(value = "/projects/{type}/{uId}/{pageNum}/{pageSize}/{industry}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, Object>> projects(@PathVariable String type, @PathVariable Integer uId,
			@PathVariable Integer pageNum, @PathVariable Integer pageSize, @PathVariable Integer industry) {
		ApiResult<Map<String, Object>> data = new ApiResult<Map<String, Object>>();
		if (industry.equals(-1)) {
			switch (type) {
			case "all":
				data = allProjects(uId, pageNum, pageSize);
				break;
			case "hot":
				data = hotProjects(uId, pageNum, pageSize);
				break;
			case "my":
				data = attentionProjects(uId, pageNum, pageSize);
				break;
			default:
				break;
			}
		} else {
			String industryTemp = dictCacheBO.getValue("industry", industry.toString());
			data = industryProjects(uId, industryTemp, pageNum, pageSize);
		}
		int code = data.getCode();
		String msg = data.getMsg();
		Map<String, Object> projects = data.getData();
		return new ApiResult<Map<String, Object>>(code, msg, projects);
	}

	/**
	 * 查看所有项目
	 * 
	 * @param uId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ApiResult<Map<String, Object>> allProjects(Integer uId, Integer pageNum, Integer pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		PageBean<ProjectMarket> projects = null;
		UcenterMember ucenterMember = projectMarketBO.getUM(uId);
		if (ucenterMember == null || ucenterMember.getStatus() != 1) {
			return new ApiResult<Map<String, Object>>(1, "用户不存在或状态异常");
		}
		try {
			ArrayList<Integer> ids = projectMarketBO.getAllPros();
			projects = getProjects(uId, ids, pageNum, pageSize);
			params.put("projects", projects);
		} catch (Exception e) {
			return new ApiResult<Map<String, Object>>(2, "查询失败");
		}

		return new ApiResult<Map<String, Object>>(0, "查询成功", params);
	}

	/**
	 * 查看热门项目
	 * 
	 * @param uId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ApiResult<Map<String, Object>> hotProjects(Integer uId, Integer pageNum, Integer pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		PageBean<ProjectMarket> projects = null;
		UcenterMember ucenterMember = projectMarketBO.getUM(uId);
		if (ucenterMember == null || ucenterMember.getStatus() != 1) {
			return new ApiResult<Map<String, Object>>(1, "用户不存在或状态异常");
		}
		try {
			ArrayList<Integer> ids = projectMarketBO.getHotProjects();
			projects = getProjects(uId, ids, pageNum, pageSize);
			params.put("projects", projects);
		} catch (Exception e) {
			return new ApiResult<Map<String, Object>>(2, "查询失败");
		}
		return new ApiResult<Map<String, Object>>(0, "查询成功", params);
	}

	/**
	 * 根据行业查看项目
	 * 
	 * @param uId
	 * @param industry
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ApiResult<Map<String, Object>> industryProjects(Integer uId, String industry, Integer pageNum,
			Integer pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		ArrayList<Integer> ids = projectMarketBO.getProByIndustry(industry);
		PageBean<ProjectMarket> projects = getProjects(uId, ids, pageNum, pageSize);
		int size = ids.size();
		params.put("projects", projects);
		if (size == 0) {
			return new ApiResult<Map<String, Object>>(1, "暂无数据，抢个板凳发布项目吧~");
		} else {
			return new ApiResult<Map<String, Object>>(0, "查询成功", params);
		}
	}

	/**
	 * 关注项目
	 * 
	 * @param uId
	 * @param pId
	 * @param attention
	 * @return
	 */
	@RequestMapping(value = "/attenProject/{uId}/{pId}/{attention}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, Object>> attenProject(@PathVariable Integer uId, @PathVariable Integer pId,
			@PathVariable Integer attention) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			projectMarketBO.attenProject(uId, pId, attention);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult<Map<String, Object>>(1, "用户或项目状态异常");
		}
		if (attention == 0) {
			params.put("state", "关注成功");
			return new ApiResult<Map<String, Object>>(0, "关注成功", params);
		} else {
			params.put("state", "已取消关注");
			return new ApiResult<Map<String, Object>>(0, "已取消关注");
		}
	}

	/**
	 * ta的主页
	 * 
	 * @param myId
	 * @param uId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/taPage/{myId}/{uId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, Object>> taPage(@PathVariable Integer myId, @PathVariable Integer uId) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			Map<String, Object> prosMap = projectMarketBO.taProjects(myId, uId);
			if (prosMap.get("proSho") != null) {
				int proFollows = (int) prosMap.get("proFollows");
				ArrayList<ProjectShow> projects = (ArrayList<ProjectShow>) prosMap.get("proSho");
				params.put("projects", projects);
				params.put("proFollows", proFollows);
			}
			UcenterMember ucenterMember = projectMarketBO.getUM(uId);
			if (ucenterMember == null || ucenterMember.getStatus() != 1) {
				return new ApiResult<Map<String, Object>>(1, "对方账号状态异常");
			} else {
				String avatarPath = ucenterMember.getAvatarPath();
				String userName = ucenterMember.getUsername();
				params.put("userName", userName);
				String signature = ucenterMember.getSignature();
				params.put("avatarPath", avatarPath);
				params.put("signature", signature);
			}
			Integer taFans = projectMarketBO.getFollowNum(uId);
			if (taFans == null) {
				params.put("taFans", 0);
			} else {
				params.put("taFans", taFans);
			}
			Integer followType = projectMarketBO.getFollowType(myId, uId);
			if (followType == null) {
				params.put("followType", 0);
			} else {
				params.put("followType", followType);
			}
		} catch (Exception e) {
			return new ApiResult<Map<String, Object>>(2, "查询失败");
		}
		return new ApiResult<Map<String, Object>>(0, "查询成功", params);
	}

	/**
	 * 关注某人
	 * 
	 * @param myId
	 * @param uId
	 * @return
	 */
	@RequestMapping(value = "/attention/{myId}/{uId}/{followType}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, Object>> attention(@PathVariable Integer myId, @PathVariable Integer uId,
			@PathVariable Integer followType) {
		Map<String, Object> params = new HashMap<String, Object>();
		UcenterMember ucenterMemberMy = projectMarketBO.getUM(myId);
		if (ucenterMemberMy == null || ucenterMemberMy.getStatus() != 1) {
			return new ApiResult<Map<String, Object>>(1, "您的账号状态异常");
		}
		UcenterMember ucenterMember = projectMarketBO.getUM(uId);
		if (ucenterMember == null || ucenterMember.getStatus() != 1) {
			return new ApiResult<Map<String, Object>>(2, "对方账号不存在或状态异常");
		}
		Integer anotherFolType = projectMarketBO.getFollowType(uId, myId);
		if (followType == 1) {
			if (anotherFolType == null || anotherFolType == 0) {
				Follow follow = new Follow();
				follow.setUserId(myId);
				follow.setFollowUserId(uId);
				follow.setFollowUserType(1);
				follow.setCreatedAt(new Date());
				follow.setUpdatedAt(new Date());
				projectMarketBO.insertFollow(follow);
				params.put("code", 01);
				params.put("state", "关注成功");
				return new ApiResult<Map<String, Object>>(0, "关注成功", params);
			} else {
				Follow follow = new Follow();
				follow.setUserId(myId);
				follow.setFollowUserId(uId);
				follow.setFollowUserType(2);
				follow.setCreatedAt(new Date());
				follow.setUpdatedAt(new Date());
				projectMarketBO.insertFollow(follow);
				Date update = new Date();
				projectMarketBO.changeAtten(uId, myId, 2, update);
				params.put("code", 02);
				params.put("state", "相互关注成功");
				return new ApiResult<Map<String, Object>>(0, "相互关注成功", params);
			}
		} else {
			if (anotherFolType == null || anotherFolType == 0) {
				Follow foll = new Follow();
				foll.setUserId(myId);
				foll.setFollowUserId(uId);
				projectMarketBO.removeFollow(foll);
				params.put("code", 03);
				params.put("state", "已取消关注");
				return new ApiResult<Map<String, Object>>(0, "已取消关注", params);
			} else {
				Date update = new Date();
				Follow foll = new Follow();
				foll.setUserId(myId);
				foll.setFollowUserId(uId);
				projectMarketBO.removeFollow(foll);
				projectMarketBO.changeAtten(uId, myId, 1, update);
				params.put("code", 04);
				params.put("state", "已取消关注");
				return new ApiResult<Map<String, Object>>(0, "已取消关注", params);
			}
		}
	}
}
