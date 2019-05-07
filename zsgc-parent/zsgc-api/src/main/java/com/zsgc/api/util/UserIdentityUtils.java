package com.zsgc.api.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zsgc.api.constant.Constants;
import com.zsgc.api.dto.UserIdentity;

public final class UserIdentityUtils {
    public static UserIdentity getUserIdentity() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return getUserIdentity(request);
    }

    public static UserIdentity getUserIdentity(HttpServletRequest request) {
        return (UserIdentity) request.getAttribute(Constants.USER_IDENTITY_KEY);
    }

    private UserIdentityUtils() {
    }
}
