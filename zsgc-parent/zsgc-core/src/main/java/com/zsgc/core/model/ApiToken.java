package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class ApiToken extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.String deviceId;
    private java.lang.String deviceName;
    private java.lang.String token;
    private java.lang.Integer uid;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

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

    public java.lang.String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(java.lang.String deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceId(java.lang.String deviceId, boolean forceUpdate) {
        setDeviceId(deviceId);

        if (forceUpdate) {
          addForceUpdateProperty("deviceId");
        }
    }

    public java.lang.String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(java.lang.String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeviceName(java.lang.String deviceName, boolean forceUpdate) {
        setDeviceName(deviceName);

        if (forceUpdate) {
          addForceUpdateProperty("deviceName");
        }
    }

    public java.lang.String getToken() {
        return token;
    }

    public void setToken(java.lang.String token) {
        this.token = token;
    }

    public void setToken(java.lang.String token, boolean forceUpdate) {
        setToken(token);

        if (forceUpdate) {
          addForceUpdateProperty("token");
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

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt, boolean forceUpdate) {
        setUpdatedAt(updatedAt);

        if (forceUpdate) {
          addForceUpdateProperty("updatedAt");
        }
    }
}
