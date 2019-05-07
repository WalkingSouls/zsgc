package com.zsgc.api.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.zsgc.api.dto.ApiResult;

@Controller
@RequestMapping("/upload")
public class UploadFileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileController.class);
	
	//文件上传
	@RequestMapping(value = "/file")
	@ResponseBody
	public ApiResult<Map<String, Object>> uploadFile(MultipartFile file, @RequestParam(required = false) String fileName) {
		if (file == null) {
			return new ApiResult<Map<String, Object>>(102, "请上传文件");
		}
		String url = null;
		try {
			JSONObject fileJson = uploadFileToUpYun(file, null, fileName, true);
			url = fileJson.getString("url");
			LOGGER.info("==========url" + url + "============");

		} catch (Exception e) {
			LOGGER.error("updateError", e.getMessage());
			return new ApiResult<Map<String, Object>>(103, "上传失败");
		}
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		if (Strings.isNullOrEmpty(url)) {
			return new ApiResult<Map<String, Object>>(103, "上传失败");
		}
		data.put("url", url);
		return new ApiResult<Map<String, Object>>(0, "success", data);
	}
}
