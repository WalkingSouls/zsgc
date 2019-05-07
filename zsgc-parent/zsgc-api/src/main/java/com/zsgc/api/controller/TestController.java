package com.zsgc.api.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsgc.api.dto.ApiResult;
import com.zsgc.core.bo.TestBO;
import com.zsgc.core.model.Test;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	@Autowired
	private TestBO testBO;

	@RequestMapping(value = "/one", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Map<String, Object>> test(@RequestBody Test test) {
		testBO.insert(test.getA());
		Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("userId", 1);
        data.put("mobile", "15924167326");
        data.put("token", "B32C7D89J54HF");


        return new ApiResult<Map<String, Object>>(0, "success", data);
	}
}
