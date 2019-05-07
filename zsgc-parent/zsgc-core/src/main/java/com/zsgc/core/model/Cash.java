package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class Cash extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.String number;
    private java.lang.String orderId;
    private java.lang.Integer uid;
    private java.math.BigDecimal cash;
    private java.lang.Integer type;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
    private java.math.BigDecimal balance;
    private java.math.BigDecimal freeze;
    private java.math.BigDecimal total;

    /**
     * 备注
     */
    private String remark;

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

    public java.lang.String getNumber() {
        return number;
    }

    public void setNumber(java.lang.String number) {
        this.number = number;
    }

    public void setNumber(java.lang.String number, boolean forceUpdate) {
        setNumber(number);

        if (forceUpdate) {
          addForceUpdateProperty("number");
        }
    }

    public java.lang.String getOrderId() {
        return orderId;
    }

    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(java.lang.String orderId, boolean forceUpdate) {
        setOrderId(orderId);

        if (forceUpdate) {
          addForceUpdateProperty("orderId");
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

    public java.math.BigDecimal getCash() {
        return cash;
    }

    public void setCash(java.math.BigDecimal cash) {
        this.cash = cash;
    }

    public void setCash(java.math.BigDecimal cash, boolean forceUpdate) {
        setCash(cash);

        if (forceUpdate) {
          addForceUpdateProperty("cash");
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

    public java.math.BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(java.math.BigDecimal balance) {
        this.balance = balance;
    }

    public void setBalance(java.math.BigDecimal balance, boolean forceUpdate) {
        setBalance(balance);

        if (forceUpdate) {
          addForceUpdateProperty("balance");
        }
    }

    public java.math.BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(java.math.BigDecimal freeze) {
        this.freeze = freeze;
    }

    public void setFreeze(java.math.BigDecimal freeze, boolean forceUpdate) {
        setFreeze(freeze);

        if (forceUpdate) {
          addForceUpdateProperty("freeze");
        }
    }

    public java.math.BigDecimal getTotal() {
        return total;
    }

    public void setTotal(java.math.BigDecimal total) {
        this.total = total;
    }

    public void setTotal(java.math.BigDecimal total, boolean forceUpdate) {
        setTotal(total);

        if (forceUpdate) {
          addForceUpdateProperty("total");
        }
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setRemark(String remark, boolean forceUpdate) {
        setRemark(remark);

        if (forceUpdate) {
            addForceUpdateProperty("remark");
        }
    }
}
