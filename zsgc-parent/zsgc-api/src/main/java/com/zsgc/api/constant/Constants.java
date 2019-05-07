package com.zsgc.api.constant;

import com.calanger.common.util.Config;

public final class Constants {
    public static final String BASE_DOMAIN = Config.getConfig().get("base.domain");
    public static final String STATIC_DOMAIN = Config.getConfig().get("static.domain");
    public static final String HOME_DOMAIN = Config.getConfig().get("home.domain");
    public static final String SERVICE_DOMAIN = Config.getConfig().get("service.domain");
    public static final String MOBILE_BASEURL = Config.getConfig().get("mobile.baseUrl");
    public static final String API_BASEURL = Config.getConfig().get("api.baseUrl");

    public static final String USER_IDENTITY_KEY = Config.getConfig().get("user.identity.key");

    public static final String SIGN_KEY = "DJFHJ34556#@$ghg$%^&DFG561"; // MD5加密秘钥

    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final String UPYUN_BASE_URL = Config.getConfig().get("upyun.baseUrl");
    public static final String UPYUN_BUCKET_NAME = Config.getConfig().get("upyun.bucketName");
    public static final String UPYUN_USERNAME = Config.getConfig().get("upyun.username");
    public static final String UPYUN_PASSWORD = Config.getConfig().get("upyun.password");

    public static final String GEXINBAO_MERCHANT_NO = Config.getConfig().get("gexinbao.merchantNo");
    public static final String GEXINBAO_MERCHANT_KEY = Config.getConfig().get("gexinbao.merchantKey");
    public static final String GEXINBAO_IDENTITY_IDCHECK = Config.getConfig().get("gexinbao.identity.idCheck");
    public static final String GEXINBAO_IDENTITY_BANKCARD4CHECK = Config.getConfig().get("gexinbao.identity.bankCard4Check");
    public static final String GEXINBAO_IDENTITY_BANKCARD3CHECK = Config.getConfig().get("gexinbao.identity.bankCard3Check");


    public static final String JPUSH_APPKEY = Config.getConfig().get("jpush.appkey");
    public static final String JPUSH_MASTERSECRET = Config.getConfig().get("jpush.mastersecret");

    public static final String APP_DOWNLOAD_URL = Config.getConfig().get("app.download.url");

	public static final String COOKIE_NAME = Config.getConfig().get("cookie.name");
	public static final String COOKIE_DOMAIN = Config.getConfig().get("cookie.domain");
	public static final String COOKIE_PATH = Config.getConfig().get("cookie.path");
	

    private Constants() {
    }
}
