package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class MemberCoupon extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer uid;
    private java.lang.Integer type;
    private java.lang.String name;
    private java.math.BigDecimal money;
    private java.math.BigDecimal rate;
    private java.math.BigDecimal minInvestMoney;
    private java.math.BigDecimal maxInvestMoney;
    private java.lang.Integer minDeadline;
    private java.lang.Integer maxDeadline;
    private java.lang.String remark;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
    private java.util.Date usedAt;
    private java.util.Date endAt;
    private java.lang.Integer getStatus;
    private java.lang.String fromWhere;

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

    public java.math.BigDecimal getMoney() {
        return money;
    }

    public void setMoney(java.math.BigDecimal money) {
        this.money = money;
    }

    public void setMoney(java.math.BigDecimal money, boolean forceUpdate) {
        setMoney(money);

        if (forceUpdate) {
          addForceUpdateProperty("money");
        }
    }

    public java.math.BigDecimal getRate() {
        return rate;
    }

    public void setRate(java.math.BigDecimal rate) {
        this.rate = rate;
    }

    public void setRate(java.math.BigDecimal rate, boolean forceUpdate) {
        setRate(rate);

        if (forceUpdate) {
          addForceUpdateProperty("rate");
        }
    }

    public java.math.BigDecimal getMinInvestMoney() {
        return minInvestMoney;
    }

    public void setMinInvestMoney(java.math.BigDecimal minInvestMoney) {
        this.minInvestMoney = minInvestMoney;
    }

    public void setMinInvestMoney(java.math.BigDecimal minInvestMoney, boolean forceUpdate) {
        setMinInvestMoney(minInvestMoney);

        if (forceUpdate) {
          addForceUpdateProperty("minInvestMoney");
        }
    }

    public java.math.BigDecimal getMaxInvestMoney() {
        return maxInvestMoney;
    }

    public void setMaxInvestMoney(java.math.BigDecimal maxInvestMoney) {
        this.maxInvestMoney = maxInvestMoney;
    }

    public void setMaxInvestMoney(java.math.BigDecimal maxInvestMoney, boolean forceUpdate) {
        setMaxInvestMoney(maxInvestMoney);

        if (forceUpdate) {
          addForceUpdateProperty("maxInvestMoney");
        }
    }

    public java.lang.Integer getMinDeadline() {
        return minDeadline;
    }

    public void setMinDeadline(java.lang.Integer minDeadline) {
        this.minDeadline = minDeadline;
    }

    public void setMinDeadline(java.lang.Integer minDeadline, boolean forceUpdate) {
        setMinDeadline(minDeadline);

        if (forceUpdate) {
          addForceUpdateProperty("minDeadline");
        }
    }

    public java.lang.Integer getMaxDeadline() {
        return maxDeadline;
    }

    public void setMaxDeadline(java.lang.Integer maxDeadline) {
        this.maxDeadline = maxDeadline;
    }

    public void setMaxDeadline(java.lang.Integer maxDeadline, boolean forceUpdate) {
        setMaxDeadline(maxDeadline);

        if (forceUpdate) {
          addForceUpdateProperty("maxDeadline");
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

    public java.util.Date getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(java.util.Date usedAt) {
        this.usedAt = usedAt;
    }

    public void setUsedAt(java.util.Date usedAt, boolean forceUpdate) {
        setUsedAt(usedAt);

        if (forceUpdate) {
          addForceUpdateProperty("usedAt");
        }
    }

    public java.util.Date getEndAt() {
        return endAt;
    }

    public void setEndAt(java.util.Date endAt) {
        this.endAt = endAt;
    }

    public void setEndAt(java.util.Date endAt, boolean forceUpdate) {
        setEndAt(endAt);

        if (forceUpdate) {
          addForceUpdateProperty("endAt");
        }
    }

    public java.lang.Integer getGetStatus() {
        return getStatus;
    }

    public void setGetStatus(java.lang.Integer getStatus) {
        this.getStatus = getStatus;
    }

    public void setGetStatus(java.lang.Integer getStatus, boolean forceUpdate) {
        setGetStatus(getStatus);

        if (forceUpdate) {
          addForceUpdateProperty("getStatus");
        }
    }

    public java.lang.String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(java.lang.String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public void setFromWhere(java.lang.String fromWhere, boolean forceUpdate) {
        setFromWhere(fromWhere);

        if (forceUpdate) {
          addForceUpdateProperty("fromWhere");
        }
    }
}
