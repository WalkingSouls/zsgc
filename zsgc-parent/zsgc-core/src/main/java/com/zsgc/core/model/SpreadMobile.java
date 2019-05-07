package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class SpreadMobile extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer referrerId;
    private java.lang.String mobile;
    private java.lang.Integer uid;
    private java.lang.String registerId;
    private java.lang.String material;
    private java.lang.Integer lx;
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

    public java.lang.Integer getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(java.lang.Integer referrerId) {
        this.referrerId = referrerId;
    }

    public void setReferrerId(java.lang.Integer referrerId, boolean forceUpdate) {
        setReferrerId(referrerId);

        if (forceUpdate) {
          addForceUpdateProperty("referrerId");
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

    public java.lang.String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(java.lang.String registerId) {
        this.registerId = registerId;
    }

    public void setRegisterId(java.lang.String registerId, boolean forceUpdate) {
        setRegisterId(registerId);

        if (forceUpdate) {
          addForceUpdateProperty("registerId");
        }
    }

    public java.lang.String getMaterial() {
        return material;
    }

    public void setMaterial(java.lang.String material) {
        this.material = material;
    }

    public void setMaterial(java.lang.String material, boolean forceUpdate) {
        setMaterial(material);

        if (forceUpdate) {
          addForceUpdateProperty("material");
        }
    }

    public java.lang.Integer getLx() {
        return lx;
    }

    public void setLx(java.lang.Integer lx) {
        this.lx = lx;
    }

    public void setLx(java.lang.Integer lx, boolean forceUpdate) {
        setLx(lx);

        if (forceUpdate) {
          addForceUpdateProperty("lx");
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
