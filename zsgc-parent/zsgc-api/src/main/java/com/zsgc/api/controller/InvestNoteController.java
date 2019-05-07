package com.zsgc.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsgc.api.dto.ApiResult;
import com.zsgc.api.dto.InvestOverviewAccept;
import com.zsgc.core.bo.TaskBO;

@Controller
@RequestMapping("/note")
public class InvestNoteController {
	@Autowired
	private TaskBO taskBO;
	/**
	 * 数据看板
	 * @param invOveAcc
	 * @return
	 */
	@RequestMapping(value = "/getTasksOverview", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Map<String,Object>> getTasksOverview(@RequestBody InvestOverviewAccept invOveAcc){
		Map<String, Object> overview = null;
		try {
			overview = taskBO.getTasksOverview(invOveAcc.getuId(),invOveAcc.getWarehouseType(),invOveAcc.getTaskTimeType(),invOveAcc.getTaskTimes());
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult <Map<String,Object>>(1,"查询失败");
		}
		return new ApiResult <Map<String,Object>>(0,"查询成功",overview);
	}
}
