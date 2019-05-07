package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class ActivityInvitation extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer registerNumber;
    private java.lang.Integer investedNumber;
    private java.math.BigDecimal investedMoney;
    private java.math.BigDecimal undistributedMoney;
    private java.math.BigDecimal couponInvest;
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

    public java.lang.Integer getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(java.lang.Integer registerNumber) {
        this.registerNumber = registerNumber;
    }

    public void setRegisterNumber(java.lang.Integer registerNumber, boolean forceUpdate) {
        setRegisterNumber(registerNumber);

        if (forceUpdate) {
          addForceUpdateProperty("registerNumber");
        }
    }

    public java.lang.Integer getInvestedNumber() {
        return investedNumber;
    }

    public void setInvestedNumber(java.lang.Integer investedNumber) {
        this.investedNumber = investedNumber;
    }

    public void setInvestedNumber(java.lang.Integer investedNumber, boolean forceUpdate) {
        setInvestedNumber(investedNumber);

        if (forceUpdate) {
          addForceUpdateProperty("investedNumber");
        }
    }

    public java.math.BigDecimal getInvestedMoney() {
        return investedMoney;
    }

    public void setInvestedMoney(java.math.BigDecimal investedMoney) {
        this.investedMoney = investedMoney;
    }

    public void setInvestedMoney(java.math.BigDecimal investedMoney, boolean forceUpdate) {
        setInvestedMoney(investedMoney);

        if (forceUpdate) {
          addForceUpdateProperty("investedMoney");
        }
    }

    public java.math.BigDecimal getUndistributedMoney() {
        return undistributedMoney;
    }

    public void setUndistributedMoney(java.math.BigDecimal undistributedMoney) {
        this.undistributedMoney = undistributedMoney;
    }

    public void setUndistributedMoney(java.math.BigDecimal undistributedMoney, boolean forceUpdate) {
        setUndistributedMoney(undistributedMoney);

        if (forceUpdate) {
          addForceUpdateProperty("undistributedMoney");
        }
    }

    public java.math.BigDecimal getCouponInvest() {
        return couponInvest;
    }

    public void setCouponInvest(java.math.BigDecimal couponInvest) {
        this.couponInvest = couponInvest;
    }

    public void setCouponInvest(java.math.BigDecimal couponInvest, boolean forceUpdate) {
        setCouponInvest(couponInvest);

        if (forceUpdate) {
          addForceUpdateProperty("couponInvest");
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
