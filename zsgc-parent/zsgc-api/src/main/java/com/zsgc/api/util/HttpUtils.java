package com.zsgc.api.util;

import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public final class HttpUtils {
    private static final int MAX_CONN_TOTAL = 4;
    private static final int MAX_CONN_PER_ROUTE = 2;

    private static final int CONNECTION_REQUEST_TIMEOUT = 6000;
    private static final int CONNECT_TIMEOUT = 30000;
    private static final int SOCKET_TIMEOUT = 30000;

    public static String doGet(String url) {
        return doGet(url, Collections.<String, String> emptyMap());
    }

    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, Collections.<String, String> emptyMap(), params);
    }

    public static String doGet(String url, Map<String, String> headers, Map<String, String> params) {
        HttpClient httpClient = getHttpClient(url);

        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?").append(QueryParamUtils.serialize(params, true));

        HttpGet httpGet = new HttpGet(sb.toString());
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        httpGet.setConfig(getRequestConfig());

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            } else {
                throw new RuntimeException(response.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
        }
    }

    public static byte[] doGetByteArray(String url) {
        return doGetByteArray(url, Collections.<String, String> emptyMap());
    }

    public static byte[] doGetByteArray(String url, Map<String, String> params) {
        return doGetByteArray(url, Collections.<String, String> emptyMap(), params);
    }

    public static byte[] doGetByteArray(String url, Map<String, String> headers, Map<String, String> params) {
        HttpClient httpClient = getHttpClient(url);

        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?").append(QueryParamUtils.serialize(params, true));

        HttpGet httpGet = new HttpGet(sb.toString());
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        httpGet.setConfig(getRequestConfig());

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toByteArray(response.getEntity());
            } else {
                throw new RuntimeException(response.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
        }
    }

    public static String doPost(String url) {
        return doPost(url, Collections.<String, String> emptyMap());
    }

    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, Collections.<String, String> emptyMap(), params);
    }

    public static String doPost(String url, Map<String, String> headers, Map<String, String> params) {
        HttpClient httpClient = getHttpClient(url);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        httpPost.setConfig(getRequestConfig());
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            } else {
                throw new RuntimeException(response.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
        }
    }

    public static String doPost(String url, String body) {
        return doPost(url, Collections.<String, String> emptyMap(), body);
    }

    public static String doPost(String url, Map<String, String> headers, String body) {
        HttpClient httpClient = getHttpClient(url);

        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        httpPost.setConfig(getRequestConfig());
        if (body != null && !body.isEmpty()) {
            httpPost.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
        }

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            } else {
                throw new RuntimeException(response.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
        }
    }

    private static HttpClient getHttpClient(String url) {
        HttpClientBuilder builder = HttpClients.custom()
                .disableAutomaticRetries()
                .setMaxConnTotal(MAX_CONN_TOTAL)
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE);

        if (url.startsWith("https://")) {
            builder.setSSLSocketFactory(getSSLConnectionSocketFactory());
        }

        return builder.build();
    }

    private static RequestConfig getRequestConfig() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();
    }

    private static SSLConnectionSocketFactory getSSLConnectionSocketFactory() {
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        };

        SSLContext ctx;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[] { tm }, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
    }

    private HttpUtils() {
    }
}
