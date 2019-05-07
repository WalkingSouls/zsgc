package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class Money extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer uid;
    private java.math.BigDecimal balance;
    private java.math.BigDecimal freeze;
    private java.math.BigDecimal totleRechargeMoney;
    private java.math.BigDecimal totleWithdrawMoney;
    private java.math.BigDecimal totleInvestMoney;
    private java.math.BigDecimal receivableInvestMoney;
    private java.math.BigDecimal totleInterestMoney;
    private java.math.BigDecimal receivableInterestMoney;
    private java.lang.Integer version;
    private java.math.BigDecimal totleWageMoney;

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

    public java.math.BigDecimal getTotleRechargeMoney() {
        return totleRechargeMoney;
    }

    public void setTotleRechargeMoney(java.math.BigDecimal totleRechargeMoney) {
        this.totleRechargeMoney = totleRechargeMoney;
    }

    public void setTotleRechargeMoney(java.math.BigDecimal totleRechargeMoney, boolean forceUpdate) {
        setTotleRechargeMoney(totleRechargeMoney);

        if (forceUpdate) {
          addForceUpdateProperty("totleRechargeMoney");
        }
    }

    public java.math.BigDecimal getTotleWithdrawMoney() {
        return totleWithdrawMoney;
    }

    public void setTotleWithdrawMoney(java.math.BigDecimal totleWithdrawMoney) {
        this.totleWithdrawMoney = totleWithdrawMoney;
    }

    public void setTotleWithdrawMoney(java.math.BigDecimal totleWithdrawMoney, boolean forceUpdate) {
        setTotleWithdrawMoney(totleWithdrawMoney);

        if (forceUpdate) {
          addForceUpdateProperty("totleWithdrawMoney");
        }
    }

    public java.math.BigDecimal getTotleInvestMoney() {
        return totleInvestMoney;
    }

    public void setTotleInvestMoney(java.math.BigDecimal totleInvestMoney) {
        this.totleInvestMoney = totleInvestMoney;
    }

    public void setTotleInvestMoney(java.math.BigDecimal totleInvestMoney, boolean forceUpdate) {
        setTotleInvestMoney(totleInvestMoney);

        if (forceUpdate) {
          addForceUpdateProperty("totleInvestMoney");
        }
    }

    public java.math.BigDecimal getReceivableInvestMoney() {
        return receivableInvestMoney;
    }

    public void setReceivableInvestMoney(java.math.BigDecimal receivableInvestMoney) {
        this.receivableInvestMoney = receivableInvestMoney;
    }

    public void setReceivableInvestMoney(java.math.BigDecimal receivableInvestMoney, boolean forceUpdate) {
        setReceivableInvestMoney(receivableInvestMoney);

        if (forceUpdate) {
          addForceUpdateProperty("receivableInvestMoney");
        }
    }

    public java.math.BigDecimal getTotleInterestMoney() {
        return totleInterestMoney;
    }

    public void setTotleInterestMoney(java.math.BigDecimal totleInterestMoney) {
        this.totleInterestMoney = totleInterestMoney;
    }

    public void setTotleInterestMoney(java.math.BigDecimal totleInterestMoney, boolean forceUpdate) {
        setTotleInterestMoney(totleInterestMoney);

        if (forceUpdate) {
          addForceUpdateProperty("totleInterestMoney");
        }
    }

    public java.math.BigDecimal getReceivableInterestMoney() {
        return receivableInterestMoney;
    }

    public void setReceivableInterestMoney(java.math.BigDecimal receivableInterestMoney) {
        this.receivableInterestMoney = receivableInterestMoney;
    }

    public void setReceivableInterestMoney(java.math.BigDecimal receivableInterestMoney, boolean forceUpdate) {
        setReceivableInterestMoney(receivableInterestMoney);

        if (forceUpdate) {
          addForceUpdateProperty("receivableInterestMoney");
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

    public java.math.BigDecimal getTotleWageMoney() {
        return totleWageMoney;
    }

    public void setTotleWageMoney(java.math.BigDecimal totleWageMoney) {
        this.totleWageMoney = totleWageMoney;
    }

    public void setTotleWageMoney(java.math.BigDecimal totleWageMoney, boolean forceUpdate) {
        setTotleWageMoney(totleWageMoney);

        if (forceUpdate) {
          addForceUpdateProperty("totleWageMoney");
        }
    }
}
