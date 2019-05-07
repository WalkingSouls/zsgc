package com.zsgc.api.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SendMobileMsg {
	private static final Logger LOGGER = LoggerFactory.getLogger(SendMobileMsg.class);
	

//    private static final String SMS_APP_KEY = "23289415";
//    private static final String SMS_APP_SECRET = "3c86bada920bdc138ee8bdda80bf4fd2";
//    private static final String SMS_SIGN_NAME = "注册验证";//短信签名
	/**
     * 发送短信
     * @param mobile 手机号码，多个号码用英文逗号分隔
     * @param smsTemplateCode 短信模板编号
     * @param smsTempleParams 短信模板参数集合
     * @return
     */
    public static JSONObject send(String mobile,String smsTemplateCode,JSONObject smsTempleParams){
    	try {
    		CloseableHttpResponse execute;
    	    CloseableHttpClient client;
    	    
    	    HttpPost post = new HttpPost("http://gw.api.taobao.com/router/rest");
    	    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    	    
    	    Map<String,String> params = Maps.newHashMap();
    	    params.put("method","alibaba.aliqin.fc.sms.num.send");
    	    params.put("app_key",ParamsSignCheck.SMS_APP_KEY);
    	    params.put("timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	    params.put("format","json");
    	    params.put("v","2.0");
    	    params.put("sms_type","normal");
    	    params.put("sms_free_sign_name",ParamsSignCheck.SMS_SIGN_NAME);//短信签名
    	    params.put("rec_num",mobile);//18600000000,13911111111,13322222222 
    	    params.put("sms_template_code",smsTemplateCode);//短信模板ID SMS_585014 
    	    params.put("sms_param",smsTempleParams.toString());//参数
    	    params.put("sign_method","md5");
    	    
    	    LOGGER.info(params.toString());
    	    
    	    List<String> keys = Lists.newArrayList();
    	    for(String key : params.keySet()){
    	    	keys.add(key);
    	    	nvps.add(new BasicNameValuePair(key,params.get(key)));
    	    }
    	    Collections.sort(keys);
    	    StringBuilder builder = new StringBuilder(ParamsSignCheck.SMS_APP_SECRET);
    	    for(String key : keys){
    	    	builder.append(key).append(params.get(key));
    	    }
    	    builder.append(ParamsSignCheck.SMS_APP_SECRET);
    	    String sign = DigestUtils.md5Hex(builder.toString()).toUpperCase();
    	    
    	    nvps.add(new BasicNameValuePair("sign",sign));
    	    
    	    post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
    	    client = HttpClients.custom()  
    	            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0")  
    	            .setDefaultRequestConfig(
    	                    RequestConfig.custom()
    		                    .setSocketTimeout(60000)
    		                    .setConnectTimeout(60000)
    		                    .setConnectionRequestTimeout(60000)
                                .build()).build();
            execute = client.execute(post);
            String outbuffer = EntityUtils.toString(execute.getEntity());
            execute.close();
            client.close();
            LOGGER.info(outbuffer);
            JSONObject json = JSONObject.parseObject(outbuffer);
            json = json.getJSONObject("alibaba_aliqin_fc_sms_num_send_response");
            json = json.getJSONObject("result");
            return json;
		} catch (Exception e) {
			LOGGER.error("",e);
			return null;
		}
    }
    

    /**
     * 阿里短信的通用配置
     * @throws ClientException 
     */
    public static IAcsClient aliSmsConfig() {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = ParamsSignCheck.SMS_ACCESSKEY_ID;//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = ParamsSignCheck.SMS_ACCESSKEY_SECRET;//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        return acsClient;
    }

    /**
     * 
     * @param templateCode      短信模板编号
     * @param telephone         手机号，可多个，以','隔开，最多1000
     * @param templateParam     变量内容
     * @return
     * @throws ServerException
     * @throws ClientException
     */
    public static String sendSms(String templateCode, String telephone, String templateParam){
        IAcsClient acsClient = aliSmsConfig();
         //组装请求对象
         SendSmsRequest request = new SendSmsRequest();
         //使用post提交
         request.setMethod(MethodType.POST);
         //待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
         request.setPhoneNumbers(telephone);
         //短信签名
         request.setSignName(ParamsSignCheck.SMS_SIGN_NAME);
         //必填:短信模板
         request.setTemplateCode(templateCode);
         //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
         if(!StringUtil.isEmpty(templateParam)){
             request.setTemplateParam(templateParam);
         }
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            return "fail";
        } catch (ClientException e) {
            e.printStackTrace();
            return "fail";
        }
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        	LOGGER.info("短息发送成功！手机号：" + telephone);
            return "success";
        } else {
        	LOGGER.error("短信发送失败！手机号：" + telephone + "|返回错误码：" + sendSmsResponse.getCode());
            return "fail";
        }
    }
    
}
