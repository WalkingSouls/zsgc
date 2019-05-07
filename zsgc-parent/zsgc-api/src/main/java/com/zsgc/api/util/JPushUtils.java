package com.zsgc.api.util;

import java.util.Map;

import com.zsgc.api.constant.Constants;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtils {
    // 推送：所有平台，所有设备，内容为 ALERT 的通知。
    public static PushResult sendAll(String ALERT) {
        return sendAll(ALERT, null);
    }

    public static PushResult sendAll(String ALERT, Map<String, String> extra) {
        IosNotification.Builder iosBuilder = IosNotification.newBuilder();
        iosBuilder.setAlert(ALERT);
        iosBuilder.setSound("default");
        if (extra != null) {
            iosBuilder.addExtras(extra);
        }

        AndroidNotification.Builder androidBuilder = AndroidNotification.newBuilder();
        androidBuilder.setAlert(ALERT);
        androidBuilder.setTitle(ALERT);
        if (extra != null) {
            androidBuilder.addExtras(extra);
        }

        try {
            PushResult result = new JPushClient(Constants.JPUSH_MASTERSECRET, Constants.JPUSH_APPKEY, null, ClientConfig.getInstance()).sendPush(
                    PushPayload.newBuilder()
                    .setPlatform(Platform.all())
                    .setAudience(Audience.all())
                    .setNotification(Notification.newBuilder()
                            .addPlatformNotification(iosBuilder.build())
                            .addPlatformNotification(androidBuilder.build())
                            .build())
                    .setOptions(Options.newBuilder()
                            .setApnsProduction(true)
                            .build())
                    .build()
            );
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 推送
     * @param alias1 推送目标的别名
     * @param ALERT 通知内容
     * @return
     */
    public static PushResult sendAlias(String alias1, String ALERT) {
        return sendAlias(alias1, ALERT, null);
    }

    public static PushResult sendAlias(String alias1, String ALERT, Map<String, String> extra) {
        IosNotification.Builder iosBuilder = IosNotification.newBuilder();
        iosBuilder.setAlert(ALERT);
        iosBuilder.setSound("default");
        if (extra != null) {
            iosBuilder.addExtras(extra);
        }

        AndroidNotification.Builder androidBuilder = AndroidNotification.newBuilder();
        androidBuilder.setAlert(ALERT);
        androidBuilder.setTitle(ALERT);
        if (extra != null) {
            androidBuilder.addExtras(extra);
        }

        try {
            PushResult result = new JPushClient(Constants.JPUSH_MASTERSECRET, Constants.JPUSH_APPKEY, null, ClientConfig.getInstance()).sendPush(
                    PushPayload.newBuilder()
                    .setPlatform(Platform.android_ios())
                    .setAudience(Audience.alias(alias1))
                    .setNotification(Notification.newBuilder()
                            .addPlatformNotification(iosBuilder.build())
                            .addPlatformNotification(androidBuilder.build())
                            .build())
                    .setOptions(Options.newBuilder()
                            .setApnsProduction(true)
                            .build())
                    .build()
            );
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
