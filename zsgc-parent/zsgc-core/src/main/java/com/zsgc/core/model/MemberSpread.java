package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class MemberSpread extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer userNumber;
    private java.lang.Integer viewNumber;
    private java.lang.Integer ipNumber;
    private java.math.BigDecimal totleCommission;
    private java.math.BigDecimal usefullCommission;
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

    public java.lang.Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(java.lang.Integer userNumber) {
        this.userNumber = userNumber;
    }

    public void setUserNumber(java.lang.Integer userNumber, boolean forceUpdate) {
        setUserNumber(userNumber);

        if (forceUpdate) {
          addForceUpdateProperty("userNumber");
        }
    }

    public java.lang.Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(java.lang.Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public void setViewNumber(java.lang.Integer viewNumber, boolean forceUpdate) {
        setViewNumber(viewNumber);

        if (forceUpdate) {
          addForceUpdateProperty("viewNumber");
        }
    }

    public java.lang.Integer getIpNumber() {
        return ipNumber;
    }

    public void setIpNumber(java.lang.Integer ipNumber) {
        this.ipNumber = ipNumber;
    }

    public void setIpNumber(java.lang.Integer ipNumber, boolean forceUpdate) {
        setIpNumber(ipNumber);

        if (forceUpdate) {
          addForceUpdateProperty("ipNumber");
        }
    }

    public java.math.BigDecimal getTotleCommission() {
        return totleCommission;
    }

    public void setTotleCommission(java.math.BigDecimal totleCommission) {
        this.totleCommission = totleCommission;
    }

    public void setTotleCommission(java.math.BigDecimal totleCommission, boolean forceUpdate) {
        setTotleCommission(totleCommission);

        if (forceUpdate) {
          addForceUpdateProperty("totleCommission");
        }
    }

    public java.math.BigDecimal getUsefullCommission() {
        return usefullCommission;
    }

    public void setUsefullCommission(java.math.BigDecimal usefullCommission) {
        this.usefullCommission = usefullCommission;
    }

    public void setUsefullCommission(java.math.BigDecimal usefullCommission, boolean forceUpdate) {
        setUsefullCommission(usefullCommission);

        if (forceUpdate) {
          addForceUpdateProperty("usefullCommission");
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
