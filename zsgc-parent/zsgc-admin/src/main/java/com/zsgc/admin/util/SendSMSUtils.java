package com.zsgc.admin.util;

import com.alibaba.fastjson.JSONObject;

public final class SendSMSUtils {
    /**
     * @Title: SendSMSUtils.java
     * @Description: 提现失败
     * @version: V1.0
     */
    public static void withDrawFail(String mobile, String money) {
        JSONObject object = new JSONObject();
        object.put("withdrawal", money);
        APIs.sendMobileMsg(mobile, "SMS_4020516", object.toString());
    }
    /**
     * @Title: SendSMSUtils.java
     * @Description: 重置密码短信提醒
     * @version: V1.0
     */
    public static void resetPwd(String mobile,String password) {
        JSONObject object = new JSONObject();
        object.put("password", password);
        APIs.sendMobileMsg(mobile, "SMS_4020780", object.toString());
    }
}
