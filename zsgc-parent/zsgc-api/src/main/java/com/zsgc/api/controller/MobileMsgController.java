package com.zsgc.api.controller;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.zsgc.api.dto.ApiResult;
import com.zsgc.api.util.ParamsSignCheck;
import com.zsgc.api.util.SendMobileMsg;
import com.zsgc.core.bo.ShortMessageBO;
import com.zsgc.core.model.ShortMessage;

/**
 * alidayu短信
 * @author ouyangb
 *
 */
@Controller
public class MobileMsgController {
	@Autowired
    private ShortMessageBO shortMessageBO;
    /**
     * 老版阿里大于发送短信
     * @param financeId
     * @param sign
     * @return
     */
    @RequestMapping(value = "/rest/sendMobileMsg", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> sendMobileMsg(String mobile,String smsTemplateCode,String smsTempleParams
            , String sign) {
        if (Strings.isNullOrEmpty(mobile)) {
            return new ApiResult<Map<String, Object>>(1001, "缺少参数:mobile");
        }
        if (Strings.isNullOrEmpty(smsTemplateCode)) {
            return new ApiResult<Map<String, Object>>(1001, "缺少参数:smsTemplateCode");
        }
        if (Strings.isNullOrEmpty(smsTempleParams)) {
            return new ApiResult<Map<String, Object>>(1001, "缺少参数:smsTempleParams");
        }
        if(Strings.isNullOrEmpty(sign)){
            return new ApiResult<Map<String, Object>>(1001, "缺少参数:sign");
        }
        StringBuilder params = new StringBuilder();
        params.append("mobile=" + mobile);
        params.append("&smsTemplateCode=" + smsTemplateCode);
        params.append("&smsTempleParams=" + smsTempleParams);
        params.append("&key=" + ParamsSignCheck.SIGN_KEY);
        String sign_new = DigestUtils.md5Hex(params.toString());
        if (!sign_new.equals(sign)) {
            new ApiResult<Map<String, Object>>(1002, "接口校验失败");
        }
        JSONObject json = SendMobileMsg.send(mobile, smsTemplateCode, JSONObject.parseObject(smsTempleParams));
        if(json == null){
            return new ApiResult<Map<String, Object>>(-1, "发送失败");
        }
        return new ApiResult<Map<String, Object>>(0, "发送成功",json);
    }
    
    
    
    /**
     * 新版阿里大鱼发送短信
     * @param financeId
     * @param sign
     * @return
     */
    @RequestMapping(value = "/rest/sendSmsMsg", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> sendSms(String mobile,Integer type,String smsTemplateCode,String smsTempleParams
            , String sign) {
        if (Strings.isNullOrEmpty(mobile)) {
            return new ApiResult<Map<String, Object>>(1001, "缺少参数:mobile");
        }
        if (Strings.isNullOrEmpty(smsTemplateCode)) {
            return new ApiResult<Map<String, Object>>(1001, "缺少参数:smsTemplateCode");
        }
        if (Strings.isNullOrEmpty(smsTempleParams)) {
            return new ApiResult<Map<String, Object>>(1001, "缺少参数:smsTempleParams");
        }
        if(Strings.isNullOrEmpty(sign)){
            return new ApiResult<Map<String, Object>>(1001, "缺少参数:sign");
        }
        StringBuilder params = new StringBuilder();
        params.append("mobile=" + mobile);
        params.append("&smsTemplateCode=" + smsTemplateCode);
        params.append("&smsTempleParams=" + smsTempleParams);
        params.append("&key=" + ParamsSignCheck.SIGN_KEY);
        String sign_new = DigestUtils.md5Hex(params.toString());
        if (!sign_new.equals(sign)) {
            return new ApiResult<Map<String, Object>>(1002, "接口校验失败");
        }
        String json = SendMobileMsg.sendSms(smsTemplateCode, mobile, smsTempleParams);
        
        if(json == null || "".equals(json) || !"success".equals(json)){
            return new ApiResult<Map<String, Object>>(-1, "发送失败");
        }
        JSONObject code =JSONObject.parseObject(smsTempleParams);
        ShortMessage shortMessage = new ShortMessage();
        shortMessage.setMobile(mobile);
        shortMessage.setCode(code.get("code").toString());
        shortMessage.setStatus(0);
        shortMessage.setType(type);
        shortMessage.setValidTime(300);
        shortMessage.setRequestTimes(0);
        shortMessageBO.add(shortMessage);
        JSONObject obj =new JSONObject();
        obj.put("status", json);
        return new ApiResult<Map<String, Object>>(0, "发送成功",obj);
    }
    
    /**
     * 验证短信验证码
     * @param mobile 手机号码
     * @param code 短信验证码
     * @param type 1注册  2忘记密码 ...
     * @return
     */
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Map<String, Object>> verify(String mobile, String code, int type) {
        int errorCode = shortMessageBO.isValidCode(mobile, code, type);
        if (errorCode != 0) {
            String errorMsg = "";
            switch (errorCode) {
            case 1000:
                errorMsg = "请重新获取验证码";
                break;
            case 1001:
                errorMsg = "验证码错误";
                break;
            case 1002:
                errorMsg = "验证码失效";
                break;
            }
            return new ApiResult<Map<String, Object>>(errorCode, errorMsg);
        }

        return new ApiResult<Map<String, Object>>(0, "success");
    }
}