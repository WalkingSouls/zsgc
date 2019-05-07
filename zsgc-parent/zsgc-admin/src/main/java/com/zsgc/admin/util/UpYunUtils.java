package com.zsgc.admin.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.calanger.common.util.Config;
import com.calanger.common.util.RandomUtils;

import main.java.com.UpYun;

public final class UpYunUtils {
    private static final String DOMAIN = Config.getConfig().get("upyun.base.domain");
    private static final String BASEURL = Config.getConfig().get("upyun.baseUrl");
    private static final String BUCKET_NAME = Config.getConfig().get("upyun.public.bucketName");
    private static final String USERNAME = Config.getConfig().get("upyun.public.userName");
    private static final String PASSWORD = Config.getConfig().get("upyun.public.password");

    private static final String DATE_PATTERN = "yyyy/MM/dd";

    public static Map<String, String> upload(MultipartFile mFile) {
        return upload(mFile, null);
    }

    public static Map<String, String> upload(MultipartFile mFile, String baseDir) {
        Map<String, String> result = new LinkedHashMap<String, String>();

        if (mFile == null || mFile.isEmpty()) {
            result.put("code", "101");
            result.put("error", "文件不存在或文件内容为空");
            return result;
        }

        String fileType = StringUtils.lowerCase(FilenameUtils.getExtension(mFile.getOriginalFilename()));

        StringBuilder pathname = new StringBuilder();
        if (StringUtils.isNotEmpty(baseDir)) {
            pathname.append(baseDir);
        }
        pathname.append("/");
        pathname.append(new SimpleDateFormat(DATE_PATTERN).format(new Date()));
        pathname.append("/");
        pathname.append(RandomUtils.getRandomUUID(false));
        pathname.append(".");
        pathname.append(fileType);

        UpYun upYun = new UpYun(BUCKET_NAME, USERNAME, PASSWORD);
        upYun.setApiDomain(UpYun.ED_AUTO);
        upYun.setDebug(true);

        try {
            boolean b = upYun.writeFile(pathname.toString(), mFile.getBytes(), true);
            if (!b) {
                result.put("code", "201");
                result.put("error", "上传到又拍云失败");
                return result;
            }
        } catch (IOException e) {
            result.put("code", "999");
            result.put("error", "系统错误");
            return result;
        }

        result.put("code", "000");
        result.put("error", "success");
        result.put("pathname", pathname.toString());
        // result.put("url", String.format("//%s%s", DOMAIN, pathname.toString()));
        result.put("url", String.format("%s%s", BASEURL, pathname.toString()));
        result.put("originalFilename", mFile.getOriginalFilename());
        result.put("fileSize", String.valueOf(mFile.getSize()));
        result.put("fileType", fileType);
        result.put("picWidth", upYun.getPicWidth());
        result.put("picHeight", upYun.getPicHeight());
        result.put("picFrames", upYun.getPicFrames());
        result.put("picType", StringUtils.lowerCase(upYun.getPicType()));
        return result;
    }

    private UpYunUtils() {
    }

    public static void main(String[] args){
        try {
            File mFile = new File("/Users/xihu/Desktop/images/logo/yxc.png");
            String baseUrl="/logo/";
            UpYun upYun = new UpYun(BUCKET_NAME, USERNAME, PASSWORD);
            upYun.setApiDomain(UpYun.ED_AUTO);
            upYun.setDebug(true);
            boolean b = upYun.writeFile(baseUrl+mFile.getName(), mFile, true);
            System.out.print(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
