package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class AppVersion extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer version;
    private java.lang.Integer versionIos;
    private java.lang.String des;
    private java.lang.Integer isForce;
    private java.lang.Integer isCodePush;
    private java.lang.Integer warnTime;
    private java.lang.Integer disabled;
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

    public java.lang.Integer getVersion() {
        return version;
    }

    public void setVersion(java.lang.Integer version) {
        this.version = version;
    }

    public void setVersion(java.lang.Integer version, boolean forceUpdate) {
        setVersion(version);

        if (forceUpdate) {
          addForceUpdateProperty("version");
        }
    }

    public java.lang.Integer getVersionIos() {
        return versionIos;
    }

    public void setVersionIos(java.lang.Integer versionIos) {
        this.versionIos = versionIos;
    }

    public void setVersionIos(java.lang.Integer versionIos, boolean forceUpdate) {
        setVersionIos(versionIos);

        if (forceUpdate) {
          addForceUpdateProperty("versionIos");
        }
    }

    public java.lang.String getDes() {
        return des;
    }

    public void setDes(java.lang.String des) {
        this.des = des;
    }

    public void setDes(java.lang.String des, boolean forceUpdate) {
        setDes(des);

        if (forceUpdate) {
          addForceUpdateProperty("des");
        }
    }

    public java.lang.Integer getIsForce() {
        return isForce;
    }

    public void setIsForce(java.lang.Integer isForce) {
        this.isForce = isForce;
    }

    public void setIsForce(java.lang.Integer isForce, boolean forceUpdate) {
        setIsForce(isForce);

        if (forceUpdate) {
          addForceUpdateProperty("isForce");
        }
    }

    public java.lang.Integer getIsCodePush() {
        return isCodePush;
    }

    public void setIsCodePush(java.lang.Integer isCodePush) {
        this.isCodePush = isCodePush;
    }

    public void setIsCodePush(java.lang.Integer isCodePush, boolean forceUpdate) {
        setIsCodePush(isCodePush);

        if (forceUpdate) {
          addForceUpdateProperty("isCodePush");
        }
    }

    public java.lang.Integer getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(java.lang.Integer warnTime) {
        this.warnTime = warnTime;
    }

    public void setWarnTime(java.lang.Integer warnTime, boolean forceUpdate) {
        setWarnTime(warnTime);

        if (forceUpdate) {
          addForceUpdateProperty("warnTime");
        }
    }

    public java.lang.Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(java.lang.Integer disabled) {
        this.disabled = disabled;
    }

    public void setDisabled(java.lang.Integer disabled, boolean forceUpdate) {
        setDisabled(disabled);

        if (forceUpdate) {
          addForceUpdateProperty("disabled");
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
