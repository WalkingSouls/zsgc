package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class Config extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Long id;
    private java.lang.String name;
    private java.lang.Integer type;
    private java.lang.String title;
    private java.lang.Integer configGroup;
    private java.lang.String extra;
    private java.lang.String remark;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
    private java.lang.Integer status;
    private java.lang.String configValue;
    private java.lang.Integer sortOrder;

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public void setId(java.lang.Long id, boolean forceUpdate) {
        setId(id);

        if (forceUpdate) {
          addForceUpdateProperty("id");
        }
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public void setName(java.lang.String name, boolean forceUpdate) {
        setName(name);

        if (forceUpdate) {
          addForceUpdateProperty("name");
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

    public java.lang.String getTitle() {
        return title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public void setTitle(java.lang.String title, boolean forceUpdate) {
        setTitle(title);

        if (forceUpdate) {
          addForceUpdateProperty("title");
        }
    }

    public java.lang.Integer getConfigGroup() {
        return configGroup;
    }

    public void setConfigGroup(java.lang.Integer configGroup) {
        this.configGroup = configGroup;
    }

    public void setConfigGroup(java.lang.Integer configGroup, boolean forceUpdate) {
        setConfigGroup(configGroup);

        if (forceUpdate) {
          addForceUpdateProperty("configGroup");
        }
    }

    public java.lang.String getExtra() {
        return extra;
    }

    public void setExtra(java.lang.String extra) {
        this.extra = extra;
    }

    public void setExtra(java.lang.String extra, boolean forceUpdate) {
        setExtra(extra);

        if (forceUpdate) {
          addForceUpdateProperty("extra");
        }
    }

    public java.lang.String getRemark() {
        return remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public void setRemark(java.lang.String remark, boolean forceUpdate) {
        setRemark(remark);

        if (forceUpdate) {
          addForceUpdateProperty("remark");
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

    public java.lang.String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(java.lang.String configValue) {
        this.configValue = configValue;
    }

    public void setConfigValue(java.lang.String configValue, boolean forceUpdate) {
        setConfigValue(configValue);

        if (forceUpdate) {
          addForceUpdateProperty("configValue");
        }
    }

    public java.lang.Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(java.lang.Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setSortOrder(java.lang.Integer sortOrder, boolean forceUpdate) {
        setSortOrder(sortOrder);

        if (forceUpdate) {
          addForceUpdateProperty("sortOrder");
        }
    }
}
