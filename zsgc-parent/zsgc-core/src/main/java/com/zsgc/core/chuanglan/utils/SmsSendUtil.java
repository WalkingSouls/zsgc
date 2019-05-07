package com.zsgc.core.chuanglan.utils;

import com.alibaba.fastjson.JSON;
import com.calanger.common.util.Config;
/**
 * 
 * @author ouyangb 
 * @Description:普通短信发送
 */
public class SmsSendUtil {

    private static final String charset = "utf-8";
	// 用户平台API账号(非登录账号,示例:N1234567)
	private static final String account = Config.getConfig().get("chuanglan.sms.account");
	// 用户平台API密码(非登录密码)
	private static final String pswd = Config.getConfig().get("chuanglan.sms.pswd");
	//请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
	private static final String smsSingleRequestServerUrl = Config.getConfig().get("chuanglan.sms.url");

	/**
	 * 
	 * @param msg      短信内容
	 * @param mobile   手机号码
	 * @return
	 */
	public static String send(String msg, String mobile) {
	    return send(msg, mobile, "true");
	}

	public static String send(String msg, String mobile, String report) {
	    SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, mobile, report);
        String requestJson = JSON.toJSONString(smsSingleRequest);
        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
	    return response;
	}
}
