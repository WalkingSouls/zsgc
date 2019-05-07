package com.zsgc.admin.util;

import com.calanger.common.util.BlowfishUtils;
import com.google.common.base.Throwables;
import com.zsgc.admin.constant.Constants;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class UserIdentityUtils {
    private static final String DATE_PATTERN = "yyyyMMdd HH:mm:ss";

    public static String serialize(UserIdentity userIdentity) {
        StringBuilder sb = new StringBuilder();
        sb.append(userIdentity.getId()).append(",");
        sb.append(userIdentity.getUsername()).append(",");
        sb.append(userIdentity.getMobile()).append(",");
        sb.append(userIdentity.getAvatar()).append(",");
        sb.append(formatDate(userIdentity.getLastVisitTime()));

        return BlowfishUtils.encrypt(sb.toString(), Constants.SECRET_KEY);
    }

    public static UserIdentity unserialize(String value) {
        String text = BlowfishUtils.decrypt(value, Constants.SECRET_KEY);
        String[] ss = text.split(",");
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setId(Integer.parseInt(ss[0]));
        userIdentity.setUsername(ss[1]);
        userIdentity.setMobile(ss[2]);
        userIdentity.setAvatar(ss[3]);
        userIdentity.setLastVisitTime(parseDate(ss[4]));
        return userIdentity;
    }

    public static UserIdentity getUserIdentity(HttpServletRequest request) {
        return (UserIdentity) request.getAttribute(Constants.USER_IDENTITY_KEY);
    }

    private static String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        return df.format(date);
    }

    private static Date parseDate(String dateStr) {
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            throw Throwables.propagate(e);
        }
    }

    private UserIdentityUtils() {
    }
}
