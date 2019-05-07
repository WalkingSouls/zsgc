package com.zsgc.api.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsgc.api.dto.ApiResult;
import com.zsgc.core.bo.DictCacheBO;


@Controller
@RequestMapping("/dict")
public class DictController {
	@Autowired 
	private DictCacheBO dictCacheBO;
	//获取字典
	@RequestMapping(value = "/{typeKey}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, String>> getIndustry(@PathVariable String typeKey) {
		Map<String, String> allMapValue = dictCacheBO.getALLMapValue(typeKey);
		 if (allMapValue.size()>0) {
			 return new ApiResult<Map<String, String>>(0, "success",allMapValue);
		}	
		return new ApiResult<Map<String, String>>(1, "数据为空");
	}
}
