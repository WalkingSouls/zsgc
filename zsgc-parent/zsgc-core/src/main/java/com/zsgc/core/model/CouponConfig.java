package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class CouponConfig extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.String code;
    private java.lang.String name;
    private java.lang.Integer isUse;
    private java.lang.Integer type;
    private java.math.BigDecimal sendThreshold;
    private java.lang.String financeType;
    private java.lang.String deadlineType;
    private java.lang.Integer deadline;
    private java.math.BigDecimal moneyMin;
    private java.math.BigDecimal moneyMax;
    private java.math.BigDecimal minInvestMoney;
    private java.lang.Integer validityDays;
    private java.lang.String remark;
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

    public java.lang.Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(java.lang.Integer isUse) {
        this.isUse = isUse;
    }

    public void setIsUse(java.lang.Integer isUse, boolean forceUpdate) {
        setIsUse(isUse);

        if (forceUpdate) {
          addForceUpdateProperty("isUse");
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

    public java.math.BigDecimal getSendThreshold() {
        return sendThreshold;
    }

    public void setSendThreshold(java.math.BigDecimal sendThreshold) {
        this.sendThreshold = sendThreshold;
    }

    public void setSendThreshold(java.math.BigDecimal sendThreshold, boolean forceUpdate) {
        setSendThreshold(sendThreshold);

        if (forceUpdate) {
          addForceUpdateProperty("sendThreshold");
        }
    }

    public java.lang.String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(java.lang.String financeType) {
        this.financeType = financeType;
    }

    public void setFinanceType(java.lang.String financeType, boolean forceUpdate) {
        setFinanceType(financeType);

        if (forceUpdate) {
          addForceUpdateProperty("financeType");
        }
    }

    public java.lang.String getDeadlineType() {
        return deadlineType;
    }

    public void setDeadlineType(java.lang.String deadlineType) {
        this.deadlineType = deadlineType;
    }

    public void setDeadlineType(java.lang.String deadlineType, boolean forceUpdate) {
        setDeadlineType(deadlineType);

        if (forceUpdate) {
          addForceUpdateProperty("deadlineType");
        }
    }

    public java.lang.Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(java.lang.Integer deadline) {
        this.deadline = deadline;
    }

    public void setDeadline(java.lang.Integer deadline, boolean forceUpdate) {
        setDeadline(deadline);

        if (forceUpdate) {
          addForceUpdateProperty("deadline");
        }
    }

    public java.math.BigDecimal getMoneyMin() {
        return moneyMin;
    }

    public void setMoneyMin(java.math.BigDecimal moneyMin) {
        this.moneyMin = moneyMin;
    }

    public void setMoneyMin(java.math.BigDecimal moneyMin, boolean forceUpdate) {
        setMoneyMin(moneyMin);

        if (forceUpdate) {
          addForceUpdateProperty("moneyMin");
        }
    }

    public java.math.BigDecimal getMoneyMax() {
        return moneyMax;
    }

    public void setMoneyMax(java.math.BigDecimal moneyMax) {
        this.moneyMax = moneyMax;
    }

    public void setMoneyMax(java.math.BigDecimal moneyMax, boolean forceUpdate) {
        setMoneyMax(moneyMax);

        if (forceUpdate) {
          addForceUpdateProperty("moneyMax");
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

    public java.lang.Integer getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(java.lang.Integer validityDays) {
        this.validityDays = validityDays;
    }

    public void setValidityDays(java.lang.Integer validityDays, boolean forceUpdate) {
        setValidityDays(validityDays);

        if (forceUpdate) {
          addForceUpdateProperty("validityDays");
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
}
