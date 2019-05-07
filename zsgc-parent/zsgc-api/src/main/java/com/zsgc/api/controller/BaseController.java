package com.zsgc.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.calanger.common.util.Config;
import com.google.common.base.Strings;
import main.java.com.UpYun;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public abstract class BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    private static String UPYUN_BASEURL = Config.getConfig().get("upyun.baseUrl");
    private static String UPYUN_DOMAIN = Config.getConfig().get("upyun.domain");
    private static String BUCKET_NAME = Config.getConfig().get("upyun.bucketName");
    private static String USERNAME = Config.getConfig().get("upyun.username");
    private static String PASSWORD = Config.getConfig().get("upyun.password");

    @ModelAttribute("upyunBaseUrl")
    public String getUpyunBaseUrl() {
        return Config.getConfig().get("upyun.baseUrl");
    }


    /**
     * 上传文件到又拍云
     * @param upfile
     * @param fold
     * @return
     */
    public JSONObject uploadFileToUpYun(MultipartFile upfile, String fold, String fileName, boolean isAbsolute) {
        JSONObject resultJson = new JSONObject();
        if (upfile == null || upfile.getSize() < 1) {
            resultJson.put("msg", "未包含文件上传域");
            return resultJson;
        }
        String fName = upfile.getOriginalFilename();
        if (Strings.isNullOrEmpty(fold)) {
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            fold = "umedit/" + format.format(new Date()) + "/";
        }
        if (!fold.endsWith("/")) {
            fold += "/";
        }
        if (Strings.isNullOrEmpty(fileName)) {
            fileName = DigestUtils.md5Hex(UUID.randomUUID().toString());
            fileName += fName.substring(fName.lastIndexOf("."));
        }
        String url = fold + fileName;
        try {
            boolean a = new UpYun(BUCKET_NAME, USERNAME, PASSWORD).writeFile(url, upfile.getBytes(), true);
            if (!a) {
                resultJson.put("msg", "上传到upyun失败");
                return resultJson;
            }
        } catch (Exception e) {
            LOGGER.error("", e);
            resultJson.put("msg", e.getMessage());
            return resultJson;
        }
        resultJson.put("state", "SUCCESS");
        resultJson.put("size", String.valueOf(upfile.getSize()));
        resultJson.put("originalName", fName);
        resultJson.put("contentType", upfile.getContentType());
        resultJson.put("name", url);
        if (isAbsolute) {
            resultJson.put("url", UPYUN_BASEURL +"/" + url);
        } else {
            resultJson.put("url", url);
        }
        resultJson.put("type", fName.substring(fName.lastIndexOf(".")));
        return resultJson;
    }
}
