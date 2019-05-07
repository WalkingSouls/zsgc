package com.zsgc.api.interceptor;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.calanger.common.util.Config;
import com.calanger.common.web.util.ResponseUtils;
import com.zsgc.api.constant.Constants;
import com.zsgc.api.dto.UserIdentity;
import com.zsgc.core.bo.AppTokenBO;
import com.zsgc.core.model.AppToken;

public class PassportInterceptor extends HandlerInterceptorAdapter {
    private static boolean loginRequiredDefault = true;
    private static List<Pattern> loginRequiredUrlPatterns = new LinkedList<Pattern>();
    private static List<Pattern> loginIgnoredUrlPatterns = new LinkedList<Pattern>();
    static {
        loadConfig();
    }

    @Autowired
    private AppTokenBO appTokenBO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean loginRequired = isLoginRequired(request);
        boolean login = isLogin(request);

        if (loginRequired) {

            if (!login) {
                redirectToLogin(request, response);
                return false;
            }
        }


        return true;
    }

    private boolean isLoginRequired(HttpServletRequest request) {
    	String[] uur =request.getRequestURI().split("api");
        String uri = uur[1];

        for (Pattern pattern : loginRequiredUrlPatterns) {
            if (pattern.matcher(uri).matches()) {
                return true;
            }
        }

        for (Pattern pattern : loginIgnoredUrlPatterns) {
            if (pattern.matcher(uri).matches()) {
                return false;
            }
        }

        return loginRequiredDefault;
    }

    private boolean isLogin(HttpServletRequest request) {
        String token = request.getHeader("Token");

        request.setAttribute("osversion", request.getHeader("osversion"));
        request.setAttribute("osname", request.getHeader("osname"));
        request.setAttribute("package", request.getHeader("package"));

        if (StringUtils.isEmpty(token)) {
            return false;
        }

        AppToken appToken = appTokenBO.getByToken(token);
        if (appToken == null) {
            return false;
        }

        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setId(appToken.getUserId());
        userIdentity.setUsername(appToken.getMobile());
        userIdentity.setMobile(appToken.getMobile());

        request.setAttribute(Constants.USER_IDENTITY_KEY, userIdentity);

        return true;
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) {
        ResponseUtils.write(response, "application/json; charset=UTF-8", "{\"code\":403,\"msg\":\"403 Forbidden\",\"data\":\"403 Forbidden\"}");
    }

    @SuppressWarnings("unused")
	private void redirectToStatue(HttpServletRequest request, HttpServletResponse response) {
        ResponseUtils.write(response, "application/json; charset=UTF-8", "{\"code\":202,\"msg\":\"该账号已禁用,请联系客服!\",\"data\":\"该账号已禁用,请联系客服!\"}");
    }


    private static void loadConfig() {
        loginRequiredDefault = !"false".equals(Config.getConfig().get("login.required.default"));

        String loginRequiredUrlRegex = Config.getConfig().get("login.required.url.regex");
        if (StringUtils.isNotBlank(loginRequiredUrlRegex)) {
            String[] regexes = loginRequiredUrlRegex.split(",");
            for (String regex : regexes) {
                loginRequiredUrlPatterns.add(Pattern.compile(regex.trim()));
            }
        }

        String loginIgnoredUrlRegex = Config.getConfig().get("login.ignored.url.regex");
        if (StringUtils.isNotBlank(loginIgnoredUrlRegex)) {
            String[] regexes = loginIgnoredUrlRegex.split(",");
            for (String regex : regexes) {
                loginIgnoredUrlPatterns.add(Pattern.compile(regex.trim()));
            }
        }
    }
}
