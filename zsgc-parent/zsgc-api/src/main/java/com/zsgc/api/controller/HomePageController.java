package com.zsgc.api.controller;

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
import com.zsgc.core.bo.HomePageBO;
import com.zsgc.core.model.HomePageWarehouseProjects;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.ProjectsPageBean;

@Controller
@RequestMapping("/homePage")
public class HomePageController {
	@Autowired
	private HomePageBO HomePageBO;
	//获取首页信息
	@RequestMapping(value = "/getProject", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<ProjectsPageBean<HomePageWarehouseProjects>> getWarehouse(@RequestBody WarehouseQueryAccept query) {	
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
			return new ApiResult<ProjectsPageBean<HomePageWarehouseProjects>>(2, "参数错误");
		}
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
		ProjectsPageBean<HomePageWarehouseProjects> data =  HomePageBO.getPage(wp,Integer.parseInt(index),Integer.parseInt(pageNum),Integer.parseInt(pageSize),query.getTimeStart(),query.getTimeEnd());
		if (data.getList() == null || data.getList().size() == 0) {
			return new ApiResult<ProjectsPageBean<HomePageWarehouseProjects>>(1, "数据为null");
		}
		return new ApiResult<ProjectsPageBean<HomePageWarehouseProjects>>(0, "success", data);
	}
	
	//设置首页置顶
	@RequestMapping(value = "/updateFirstTop/{pId}/{firstTop}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<String> updateFirstTop(@PathVariable int pId ,@PathVariable byte firstTop) {
		int index = HomePageBO.updateFirstTop(pId, firstTop);
		if (index == 1) {
			return new ApiResult<String>(0, "success");
		}
		return new ApiResult<String>(1, "更新失败");	
	}
}
