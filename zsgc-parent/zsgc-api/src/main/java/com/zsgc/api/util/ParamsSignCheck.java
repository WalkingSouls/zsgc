package com.zsgc.api.util;

import com.calanger.common.util.Config;

public class ParamsSignCheck {
    public static final String SIGN_KEY = "UOQNUkie7O3baHishQzOWKVOCoLy43"; // MD5加密秘钥
    public static final String SMS_APP_KEY = Config.getConfig().get("api.sms.alidayu.appkey");
    public static final String SMS_APP_SECRET = Config.getConfig().get("api.sms.alidayu.appsecret");
    public static final String SMS_SIGN_NAME = "云泽信息";
    public static final String SMS_ACCESSKEY_ID=Config.getConfig().get("api.sms.alidayu.AccessKeyID");
    public static final String SMS_ACCESSKEY_SECRET=Config.getConfig().get("api.sms.alidayu.AccessKeysecret");		
}
