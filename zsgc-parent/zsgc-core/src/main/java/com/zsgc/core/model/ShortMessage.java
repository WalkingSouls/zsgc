package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class ShortMessage extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.String code;
    private java.lang.Integer type;
    private java.lang.Integer validTime;
    private java.lang.String mobile;
    private java.lang.Integer userId;
    private java.lang.Integer requestTimes;
    private java.lang.Integer status;
    private java.util.Date createdAt;

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

    public java.lang.String getCode() {
        return code;
    }

    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public void setCode(java.lang.String code, boolean forceUpdate) {
        setCode(code);

        if (forceUpdate) {
          addForceUpdateProperty("code");
        }
    }

    public java.lang.Integer getType() {
        return type;
    }

    public void setType(java.lang.Integer type) {
        this.type = type;
    }

    public void setType(java.lang.Integer type, boolean forceUpdate) {
        setType(type);

        if (forceUpdate) {
          addForceUpdateProperty("type");
        }
    }

    public java.lang.Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(java.lang.Integer validTime) {
        this.validTime = validTime;
    }

    public void setValidTime(java.lang.Integer validTime, boolean forceUpdate) {
        setValidTime(validTime);

        if (forceUpdate) {
          addForceUpdateProperty("validTime");
        }
    }

    public java.lang.String getMobile() {
        return mobile;
    }

    public void setMobile(java.lang.String mobile) {
        this.mobile = mobile;
    }

    public void setMobile(java.lang.String mobile, boolean forceUpdate) {
        setMobile(mobile);

        if (forceUpdate) {
          addForceUpdateProperty("mobile");
        }
    }

    public java.lang.Integer getUserId() {
        return userId;
    }

    public void setUserId(java.lang.Integer userId) {
        this.userId = userId;
    }

    public void setUserId(java.lang.Integer userId, boolean forceUpdate) {
        setUserId(userId);

        if (forceUpdate) {
          addForceUpdateProperty("userId");
        }
    }

    public java.lang.Integer getRequestTimes() {
        return requestTimes;
    }

    public void setRequestTimes(java.lang.Integer requestTimes) {
        this.requestTimes = requestTimes;
    }

    public void setRequestTimes(java.lang.Integer requestTimes, boolean forceUpdate) {
        setRequestTimes(requestTimes);

        if (forceUpdate) {
          addForceUpdateProperty("requestTimes");
        }
    }

    public java.lang.Integer getStatus() {
        return status;
    }

    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    public void setStatus(java.lang.Integer status, boolean forceUpdate) {
        setStatus(status);

        if (forceUpdate) {
          addForceUpdateProperty("status");
        }
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt, boolean forceUpdate) {
        setCreatedAt(createdAt);

        if (forceUpdate) {
          addForceUpdateProperty("createdAt");
        }
    }
}
