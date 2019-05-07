package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class LoginLog extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer uid;
    private java.util.Date loginTime;
    private java.lang.String ip;

    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    public void setId(java.lang.Integer id, boolean forceUpdate) {
        setId(id);

        if (forceUpdate) {
          addForceUpdateProperty("id");
        }
    }

    public java.lang.Integer getUid() {
        return uid;
    }

    public void setUid(java.lang.Integer uid) {
        this.uid = uid;
    }

    public void setUid(java.lang.Integer uid, boolean forceUpdate) {
        setUid(uid);

        if (forceUpdate) {
          addForceUpdateProperty("uid");
        }
    }

    public java.util.Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(java.util.Date loginTime) {
        this.loginTime = loginTime;
    }

    public void setLoginTime(java.util.Date loginTime, boolean forceUpdate) {
        setLoginTime(loginTime);

        if (forceUpdate) {
          addForceUpdateProperty("loginTime");
        }
    }

    public java.lang.String getIp() {
        return ip;
    }

    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }

    public void setIp(java.lang.String ip, boolean forceUpdate) {
        setIp(ip);

        if (forceUpdate) {
          addForceUpdateProperty("ip");
        }
    }
}
