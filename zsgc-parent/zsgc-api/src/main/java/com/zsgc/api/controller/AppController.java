package com.zsgc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsgc.api.dto.ApiResult;
import com.zsgc.core.bo.AppVersionBO;
import com.zsgc.core.model.AppVersion;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/app")
public class AppController {

    @Autowired
    private AppVersionBO appVersionBO;

    /**
     * 获取当前版本信息
     * @return 
     */
    @RequestMapping(value = "/version/info", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Map<String, Object>> versionInfo() {
        AppVersion appVersionVO = new AppVersion();
        appVersionVO.setDisabled(0);
        AppVersion appVersion = appVersionBO.get(appVersionVO);

        if (appVersion == null) {
            return new ApiResult<Map<String, Object>>(101, "无数据");
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("version", appVersion.getVersion()); // android版本号
        data.put("versionIos", appVersion.getVersionIos()); // IOS版本号
        data.put("isForce", appVersion.getIsForce()); // 是否强制更新
        data.put("des", appVersion.getDes()); // 更新描述
        data.put("warnTime", appVersion.getWarnTime()); // 提示时间（单位：秒）
		data.put("isCodePush", appVersion.getIsCodePush()); // 是否热更新
        return new ApiResult<Map<String, Object>>(0, "success", data);
    }
}
