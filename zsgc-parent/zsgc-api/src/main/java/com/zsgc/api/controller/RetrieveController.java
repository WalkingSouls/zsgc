package com.zsgc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsgc.api.dto.ApiResult;
import com.zsgc.core.bo.RetrieveBO;
import com.zsgc.core.model.TaskRecovery;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.PageBean;

@Controller
@RequestMapping("/retrieve")
public class RetrieveController {
	@Autowired
	private RetrieveBO retrieveBO;
	
	//获取回收站项目
	@RequestMapping(value = {"/getProjectsPage/{uId}/{pageNum}/{pageSize}/{delCauseNum}","/getProjectsPage/{uId}/{pageNum}/{pageSize}"})
	@ResponseBody
	public ApiResult<PageBean<WarehouseProjects>> getProjectsPage(@PathVariable int uId , @PathVariable int pageNum,@PathVariable int pageSize,@PathVariable(required = false) Byte delCauseNum) {
		PageBean<WarehouseProjects> data = retrieveBO.ProjectsPage(uId,pageNum, pageSize, delCauseNum);
		if (data.getList()==null || data.getList().size()==0) {
			return new ApiResult<PageBean<WarehouseProjects>>(1, "数据为null");
		}
		return new ApiResult<PageBean<WarehouseProjects>>(0, "success", data);
	}
	//获取回收站任务
	@RequestMapping(value = "/getTaskPage/{uId}/{pageNum}/{pageSize}")
	@ResponseBody
	public ApiResult<PageBean<TaskRecovery>> getTaskPage(@PathVariable int uId , @PathVariable int pageNum,@PathVariable int pageSize) {
		PageBean<TaskRecovery> data = retrieveBO.TasksPage(uId, pageNum, pageSize);
		if (data.getList()==null || data.getList().size()==0) {
			return new ApiResult<PageBean<TaskRecovery>>(1, "数据为null");
		}
		return new ApiResult<PageBean<TaskRecovery>>(0, "success", data);
	}
	
}
