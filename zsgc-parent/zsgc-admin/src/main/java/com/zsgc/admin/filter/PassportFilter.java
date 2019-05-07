package com.zsgc.admin.filter;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;

import com.calanger.common.util.Config;
import com.calanger.common.util.UrlUtils;
import com.calanger.common.web.util.CookieUtils;
import com.google.common.base.Strings;
import com.zsgc.admin.constant.Constants;
import com.zsgc.admin.util.UserIdentity;
import com.zsgc.admin.util.UserIdentityUtils;

public class PassportFilter implements Filter {
    private boolean loginRequiredDefault = true;
    private List<Pattern> loginRequiredUrlPatterns = new LinkedList<Pattern>();
    private List<Pattern> loginIgnoredUrlPatterns = new LinkedList<Pattern>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loadConfig();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        boolean loginRequired = isLoginRequired(request);
        boolean login = isLogin(request);

        if (loginRequired) {
            if (login) {
                if (isSessionExpired(request)) {
                    redirectToLogin(request, response);
                    return;
                } else {
                    updateLastVisitTime(request, response);
                }
            } else {
                redirectToLogin(request, response);
                return;
            }
        } else {
            if (login) {
                if (isSessionExpired(request)) {
                    removeCookie(request, response);
                } else {
                    updateLastVisitTime(request, response);
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private void loadConfig() {
        loginRequiredDefault = !"false".equals(Config.getConfig().get("login.required.default"));

        String loginRequiredUrlRegex = Config.getConfig().get("login.required.url.regex");
        if (!Strings.isNullOrEmpty(loginRequiredUrlRegex)) {
            String[] regexes = loginRequiredUrlRegex.split(",");
            for (String regex : regexes) {
                loginRequiredUrlPatterns.add(Pattern.compile(regex.trim()));
            }
        }

        String loginIgnoredUrlRegex = Config.getConfig().get("login.ignored.url.regex");
        if (!Strings.isNullOrEmpty(loginIgnoredUrlRegex)) {
            String[] regexes = loginIgnoredUrlRegex.split(",");
            for (String regex : regexes) {
                loginIgnoredUrlPatterns.add(Pattern.compile(regex.trim()));
            }
        }
    }

    private boolean isLoginRequired(HttpServletRequest request) {
        String uri = request.getRequestURI();

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
        Cookie cookie = CookieUtils.getCookie(request, Constants.COOKIE_NAME);
        if (cookie == null) {
            return false;
        }

        request.setAttribute(Constants.USER_IDENTITY_KEY, UserIdentityUtils.unserialize(cookie.getValue()));

        return true;
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder url = new StringBuilder();
        url.append(request.getRequestURI());
        String queryString = request.getQueryString();
        if (queryString != null) {
            url.append("?");
            url.append(queryString);
        }

        String loginUrl = Config.getConfig().get("login.url");

        StringBuilder redirectUrl = new StringBuilder();
        redirectUrl.append(loginUrl);
        redirectUrl.append("?returnUrl=");
        redirectUrl.append(UrlUtils.encode(url.toString()));

        try {
            response.sendRedirect(redirectUrl.toString());
        } catch (IOException e) {
            // ignore
        }
    }

    private boolean isSessionExpired(HttpServletRequest request) {
        UserIdentity userIdentity = (UserIdentity) request.getAttribute(Constants.USER_IDENTITY_KEY);
        Date expiryTime = DateUtils.addMinutes(userIdentity.getLastVisitTime(), Constants.SESSION_TIMEOUT);
        return expiryTime.before(new Date());
    }

    private void updateLastVisitTime(HttpServletRequest request, HttpServletResponse response) {
        UserIdentity userIdentity = (UserIdentity) request.getAttribute(Constants.USER_IDENTITY_KEY);
        userIdentity.setLastVisitTime(new Date());
        CookieUtils.addCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, Constants.COOKIE_NAME, UserIdentityUtils.serialize(userIdentity));
    }

    private void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(Constants.USER_IDENTITY_KEY, null);
        CookieUtils.removeCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, Constants.COOKIE_NAME);
    }
}
