package com.zsgc.admin.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.zsgc.admin.constant.Constants;

public final class APIs {
    private static final Logger LOGGER = LoggerFactory.getLogger(APIs.class);

    public static JSONObject payInterest(Integer financeId, Integer isPrepayment) {
        String url = Constants.SERVICE_DOMAIN +"/rest/payInterest";

        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom().disableAutomaticRetries()
                    .setMaxConnTotal(16)
                    .setMaxConnPerRoute(8).build();
            
            HttpPost httpPost = new HttpPost(url);

            StringBuilder params = new StringBuilder();
            params.append("financeId=").append(financeId.toString());
            params.append("isPrepayment=").append(isPrepayment.toString());
            params.append("&key=").append(Constants.SIGN_KEY);

            String sign_new = DigestUtils.md5Hex(params.toString());

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("financeId", financeId.toString()));
            nvps.add(new BasicNameValuePair("isPrepayment", isPrepayment.toString()));
            nvps.add(new BasicNameValuePair("sign", sign_new));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    String content = EntityUtils.toString(entity, Charsets.UTF_8);
                    return JSONObject.parseObject(content);
                } else {
                    throw new RuntimeException(response.toString());
                }
            } catch (Exception e) {
                throw Throwables.propagate(e);
            } finally {
                HttpClientUtils.closeQuietly(httpClient);
            }
        } catch (Throwable t) {
            throw Throwables.propagate(t);
        }
    }

    public static void tryTransfer(String orderNo) {
        String url = Constants.SERVICE_DOMAIN + "/try-transfer";

        CloseableHttpClient httpClient = null;
        try {
            // 创建 HttpClient 的实例
            httpClient = HttpClients.custom().disableAutomaticRetries()
                    .setMaxConnTotal(16)
                    .setMaxConnPerRoute(8).build();

            StringBuilder sb = new StringBuilder();
            sb.append(orderNo).append(Constants.TRY_TRANSFER_KEY);
            String sign = DigestUtils.sha1Hex(sb.toString());

            sb = new StringBuilder();
            sb.append(url).append("?orderNo=").append(orderNo).append("&sign=").append(sign);

            HttpGet httpGet = new HttpGet(sb.toString()); // GET请求

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpGet); // 执行请求
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    String s = EntityUtils.toString(entity, Charsets.UTF_8); // 接口返回字符串s
                    LOGGER.info(s);
                } else {
                    throw new RuntimeException(response.toString());
                }
            } catch (Exception e) {
                throw Throwables.propagate(e);
            } finally {
                HttpClientUtils.closeQuietly(httpClient); // 释放连接
            }
        } catch (Throwable t) {
            throw Throwables.propagate(t);
        }
    }

    public static JSONObject sendMobileMsg(String mobile,String smsTemplateCode,String smsTempleParams) {
        String url = Constants.SERVICE_DOMAIN +"/rest/sendMobileMsg";
        
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom().disableAutomaticRetries()
                    .setMaxConnTotal(16)
                    .setMaxConnPerRoute(8).build();
            
            HttpPost httpPost = new HttpPost(url);
            
            StringBuilder params = new StringBuilder();
            params.append("mobile=").append(mobile);
            params.append("&smsTemplateCode=").append(smsTemplateCode);
            params.append("&smsTempleParams=").append(smsTempleParams);
            params.append("&key=").append(Constants.SIGN_KEY);
            
            String sign_new = DigestUtils.md5Hex(params.toString());
            
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("mobile", mobile));
            nvps.add(new BasicNameValuePair("smsTemplateCode", smsTemplateCode));
            nvps.add(new BasicNameValuePair("smsTempleParams", smsTempleParams));
            nvps.add(new BasicNameValuePair("sign", sign_new));
            
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    String content = EntityUtils.toString(entity, Charsets.UTF_8);
                    return JSONObject.parseObject(content);
                } else {
                    throw new RuntimeException(response.toString());
                }
            } catch (Exception e) {
                throw Throwables.propagate(e);
            } finally {
                HttpClientUtils.closeQuietly(httpClient);
            }
        } catch (Throwable t) {
            throw Throwables.propagate(t);
        }
    }

    /**
     * @Title: APIs.java
     * @Prject: p2p-admin
     * @Package: com.calanger.p2p.admin.utils
     * @Description: 添加系统日志
     * @author: 龚国君
     * @date: 2016年1月7日 上午11:22:25
     * @version: V1.0 
     * @param type
     * @param uri
     * @param msg
     * @param adminId
     * @param ip
     */
    public static void addSysLog(Integer type, String uri, String msg, Integer adminId, String ip) {
        String url = Constants.SERVICE_DOMAIN +"/rest/sysLog";
        
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom().disableAutomaticRetries()
                    .setMaxConnTotal(16)
                    .setMaxConnPerRoute(8).build();
            
            HttpPost httpPost = new HttpPost(url);
            
            StringBuilder params = new StringBuilder();
            params.append("type=").append(type);
            params.append("&uri=").append(uri);
            params.append("&msg=").append(msg);
            params.append("&adminId=").append(adminId);
            params.append("&ip=").append(ip);
            params.append("&key=").append(Constants.SIGN_KEY);
            
            String sign_new = DigestUtils.md5Hex(params.toString());
            
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("type", type.toString()));
            nvps.add(new BasicNameValuePair("uri", uri));
            nvps.add(new BasicNameValuePair("msg", msg));
            nvps.add(new BasicNameValuePair("adminId", adminId.toString()));
            nvps.add(new BasicNameValuePair("ip", ip));
            nvps.add(new BasicNameValuePair("sign", sign_new));
            
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    EntityUtils.toString(entity, Charsets.UTF_8);
                } else {
                    LOGGER.error(response.toString());
                }
            } catch (Exception e) {
                LOGGER.error("",e);
            } finally {
                HttpClientUtils.closeQuietly(httpClient);
            }
        } catch (Throwable t) {
            LOGGER.error("",t);
        }
    }
}
