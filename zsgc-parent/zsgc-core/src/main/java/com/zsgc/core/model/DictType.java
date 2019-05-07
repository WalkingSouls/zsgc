package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class DictType extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.String typeKey;
    private java.lang.String typeValue;
    private java.lang.Integer version;
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

    public java.lang.String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(java.lang.String typeKey) {
        this.typeKey = typeKey;
    }

    public void setTypeKey(java.lang.String typeKey, boolean forceUpdate) {
        setTypeKey(typeKey);

        if (forceUpdate) {
          addForceUpdateProperty("typeKey");
        }
    }

    public java.lang.String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(java.lang.String typeValue) {
        this.typeValue = typeValue;
    }

    public void setTypeValue(java.lang.String typeValue, boolean forceUpdate) {
        setTypeValue(typeValue);

        if (forceUpdate) {
          addForceUpdateProperty("typeValue");
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
