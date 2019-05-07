package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class SpreadUser extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer uid;
    private java.lang.Integer referrerId;
    private java.lang.Integer investedStatus;
    private java.math.BigDecimal investedMoney;
    private java.math.BigDecimal couponCheck;
    private java.math.BigDecimal couponInvest;
    private java.math.BigDecimal wageIncome;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
    private java.util.Date registedAt;

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

    public java.lang.Integer getInvestedStatus() {
        return investedStatus;
    }

    public void setInvestedStatus(java.lang.Integer investedStatus) {
        this.investedStatus = investedStatus;
    }

    public void setInvestedStatus(java.lang.Integer investedStatus, boolean forceUpdate) {
        setInvestedStatus(investedStatus);

        if (forceUpdate) {
          addForceUpdateProperty("investedStatus");
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

    public java.math.BigDecimal getCouponCheck() {
        return couponCheck;
    }

    public void setCouponCheck(java.math.BigDecimal couponCheck) {
        this.couponCheck = couponCheck;
    }

    public void setCouponCheck(java.math.BigDecimal couponCheck, boolean forceUpdate) {
        setCouponCheck(couponCheck);

        if (forceUpdate) {
          addForceUpdateProperty("couponCheck");
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

    public java.math.BigDecimal getWageIncome() {
        return wageIncome;
    }

    public void setWageIncome(java.math.BigDecimal wageIncome) {
        this.wageIncome = wageIncome;
    }

    public void setWageIncome(java.math.BigDecimal wageIncome, boolean forceUpdate) {
        setWageIncome(wageIncome);

        if (forceUpdate) {
          addForceUpdateProperty("wageIncome");
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

    public java.util.Date getRegistedAt() {
        return registedAt;
    }

    public void setRegistedAt(java.util.Date registedAt) {
        this.registedAt = registedAt;
    }

    public void setRegistedAt(java.util.Date registedAt, boolean forceUpdate) {
        setRegistedAt(registedAt);

        if (forceUpdate) {
          addForceUpdateProperty("registedAt");
        }
    }
}
