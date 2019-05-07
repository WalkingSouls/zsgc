package com.zsgc.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.zsgc.api.dto.ApiResult;
import com.zsgc.api.dto.WarehouseQueryAccept;
import com.zsgc.core.bo.WarehouseProjectBO;
import com.zsgc.core.model.CustomerProfile;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.ProjectsPageBean;

@Controller
@RequestMapping("/warehouse")
public class WarehouseProjectsContriller {
	@Autowired
	private WarehouseProjectBO warehouseProjectBO;
	//获取仓库项目
	@RequestMapping(value = "/getProjects", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<ProjectsPageBean<WarehouseProjects>> getWarehouse(@RequestBody WarehouseQueryAccept query) {
		String userId = query.getUserId();
		String type = query.getType();
		String del_state = query.getDel_state();
		String pageNum = query.getPageNum();
		String pageSize = query.getPageSize();
		String index = query.getIndex();
		String industry = query.getIndustry();
		String state = query.getState();
		if ( Strings.isNullOrEmpty(userId)|| Strings.isNullOrEmpty(type) || Strings.isNullOrEmpty(del_state)|| Strings.isNullOrEmpty(pageNum)|| Strings.isNullOrEmpty(pageSize)|| Strings.isNullOrEmpty(index)
				||Integer.parseInt(pageNum) < 1 ) {
			return new ApiResult<ProjectsPageBean<WarehouseProjects>>(2, "参数错误");
		}
		ProjectsPageBean<WarehouseProjects> page = new ProjectsPageBean<WarehouseProjects>(Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		WarehouseProjects wp = new WarehouseProjects();
		wp.setWarehouseType(Integer.parseInt(type));
		wp.setuId(Integer.parseInt(userId));
		wp.setDelState(Integer.parseInt(del_state));
		if (industry!=null&&!industry.equals("")) {
			wp.setIndustry(industry);
		}
		if (state!=null&&!state.equals("")) {
			wp.setState(Integer.parseInt(state));
		}
		ProjectsPageBean<WarehouseProjects> data =  warehouseProjectBO.getPage(wp, Integer.parseInt(index), page);
		if (data.getList() == null || data.getList().size() == 0) {
			return new ApiResult<ProjectsPageBean<WarehouseProjects>>(1, "数据为null");
		}
		return new ApiResult<ProjectsPageBean<WarehouseProjects>>(0, "success", data);
	}
	//编辑项目删除
	@RequestMapping(value = "/upDateDelStateProjects", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<String> upDateStateProjects(@RequestBody WarehouseProjects wp) {
		
		int changProfile = warehouseProjectBO.changProfile(wp);
		if (changProfile == 1) {
			return new ApiResult<String>(0, "success");
		}
		return new ApiResult<String>(1, "更新失败");
	}
	//编辑项目仓库类型
	@RequestMapping(value = "/changeWarehouse/{pId}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<String> changeWarehouse(@PathVariable String pId, @PathVariable String type) {
		if (pId.equals("") || pId == null || type.equals("") || type == null) {
			return new ApiResult<String>(2, "参数错误");
		}
		WarehouseProjects wp = new WarehouseProjects();
		wp.setWarehouseType(Integer.parseInt(type));
		wp.setpId(Integer.parseInt(pId));
		wp.setUpdateP(new Date());
		int index = warehouseProjectBO.changeWarehouse(wp);
		if (index == 1) {
			return new ApiResult<String>(0, "success");
		}
		return new ApiResult<String>(1, "更新失败");
	}
	//获取项目客户图谱
	@RequestMapping(value = "/getCustomerProfile/{pId}/{delState}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<CustomerProfile>> getCustomerProfile(@PathVariable String pId,
			@PathVariable String delState) {
		if (pId.equals("") || pId == null || delState.equals("") || delState == null) {
			return new ApiResult<List<CustomerProfile>>(2, "参数错误");
		}
		WarehouseProjects wp = new WarehouseProjects();
		wp.setpId(Integer.parseInt(pId));
		wp.setDelState(Integer.parseInt(delState));
		List<CustomerProfile> data = warehouseProjectBO.getCustomerProfile(wp);
		if (data == null || data.size() == 0) {
			return new ApiResult<List<CustomerProfile>>(1, "数据为null");
		}
		return new ApiResult<List<CustomerProfile>>(0, "success", data);
	}
	//编辑项目置顶状态
	@RequestMapping(value = "/upDateProjectsTop/{pId}/{top}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<String> upDateProjectsTop(@PathVariable String pId, @PathVariable String top) {
		if (pId.equals("") || pId == null || top.equals("") || top == null) {
			return new ApiResult<String>(2, "参数错误");
		}
		WarehouseProjects wp = new WarehouseProjects();
		wp.setpId(Integer.parseInt(pId));
		wp.setTop(Integer.parseInt(top));
		wp.setTopTime(new Date());
		int index = warehouseProjectBO.changeWarehouse(wp);
		if (index == 1) {
			return new ApiResult<String>(0, "success");
		}
		return new ApiResult<String>(1, "更新失败");
	}
	//编辑项目状态 
	@RequestMapping(value = "/upDateProjectState/{pId}/{state}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<String> upDateProjectState(@PathVariable String pId, @PathVariable String state) {
		if (pId.equals("") || pId == null || state.equals("") || state == null) {
			return new ApiResult<String>(2, "参数错误");
		}
		WarehouseProjects wp = new WarehouseProjects();
		wp.setpId(Integer.parseInt(pId));
		wp.setState(Integer.parseInt(state));
		int index = warehouseProjectBO.changeWarehouse(wp);
		if (index == 1) {
			return new ApiResult<String>(0, "success");
		}
		return new ApiResult<String>(1, "更新失败");
	}
	
	//编辑项目笔记本共享状态
	@RequestMapping(value = "/upDateSharedBook/{pId}/{shareType}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<String> upDateSharedBook(@PathVariable String pId ,@PathVariable String shareType) {
		if (Strings.isNullOrEmpty(shareType)) {
			return new ApiResult<String>(2, "参数错误");
		}
		WarehouseProjects wp = new WarehouseProjects();
		wp.setpId(Integer.parseInt(pId));
		wp.setSharedBook(Byte.valueOf(shareType));
		int index = warehouseProjectBO.changeWarehouse(wp);
		if (index == 1) {
			return new ApiResult<String>(0, "success");
		}
		return new ApiResult<String>(1, "更新失败");
	}
	
	
}
