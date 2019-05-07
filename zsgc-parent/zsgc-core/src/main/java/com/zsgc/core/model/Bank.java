package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class Bank extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.String name;
    private java.lang.String code;
    private java.lang.String icon;
    private java.lang.String pcIcon;
    private java.lang.String backgroundImage;
    private java.lang.String pcBackgroundImage;
    private java.lang.Integer status;
    private java.lang.String singleLimit;
    private java.lang.String dailyAccumulativeLimit;
    private java.lang.String monthlyAccumulativeLimit;
    private java.lang.String conditionsOfUsage;
    private java.lang.String llpayCode;
    private java.lang.String bankNo;
    private java.lang.String issInsCd;

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

    public java.lang.String getIcon() {
        return icon;
    }

    public void setIcon(java.lang.String icon) {
        this.icon = icon;
    }

    public void setIcon(java.lang.String icon, boolean forceUpdate) {
        setIcon(icon);

        if (forceUpdate) {
          addForceUpdateProperty("icon");
        }
    }

    public java.lang.String getPcIcon() {
        return pcIcon;
    }

    public void setPcIcon(java.lang.String pcIcon) {
        this.pcIcon = pcIcon;
    }

    public void setPcIcon(java.lang.String pcIcon, boolean forceUpdate) {
        setPcIcon(pcIcon);

        if (forceUpdate) {
          addForceUpdateProperty("pcIcon");
        }
    }

    public java.lang.String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(java.lang.String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setBackgroundImage(java.lang.String backgroundImage, boolean forceUpdate) {
        setBackgroundImage(backgroundImage);

        if (forceUpdate) {
          addForceUpdateProperty("backgroundImage");
        }
    }

    public java.lang.String getPcBackgroundImage() {
        return pcBackgroundImage;
    }

    public void setPcBackgroundImage(java.lang.String pcBackgroundImage) {
        this.pcBackgroundImage = pcBackgroundImage;
    }

    public void setPcBackgroundImage(java.lang.String pcBackgroundImage, boolean forceUpdate) {
        setPcBackgroundImage(pcBackgroundImage);

        if (forceUpdate) {
          addForceUpdateProperty("pcBackgroundImage");
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

    public java.lang.String getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(java.lang.String singleLimit) {
        this.singleLimit = singleLimit;
    }

    public void setSingleLimit(java.lang.String singleLimit, boolean forceUpdate) {
        setSingleLimit(singleLimit);

        if (forceUpdate) {
          addForceUpdateProperty("singleLimit");
        }
    }

    public java.lang.String getDailyAccumulativeLimit() {
        return dailyAccumulativeLimit;
    }

    public void setDailyAccumulativeLimit(java.lang.String dailyAccumulativeLimit) {
        this.dailyAccumulativeLimit = dailyAccumulativeLimit;
    }

    public void setDailyAccumulativeLimit(java.lang.String dailyAccumulativeLimit, boolean forceUpdate) {
        setDailyAccumulativeLimit(dailyAccumulativeLimit);

        if (forceUpdate) {
          addForceUpdateProperty("dailyAccumulativeLimit");
        }
    }

    public java.lang.String getMonthlyAccumulativeLimit() {
        return monthlyAccumulativeLimit;
    }

    public void setMonthlyAccumulativeLimit(java.lang.String monthlyAccumulativeLimit) {
        this.monthlyAccumulativeLimit = monthlyAccumulativeLimit;
    }

    public void setMonthlyAccumulativeLimit(java.lang.String monthlyAccumulativeLimit, boolean forceUpdate) {
        setMonthlyAccumulativeLimit(monthlyAccumulativeLimit);

        if (forceUpdate) {
          addForceUpdateProperty("monthlyAccumulativeLimit");
        }
    }

    public java.lang.String getConditionsOfUsage() {
        return conditionsOfUsage;
    }

    public void setConditionsOfUsage(java.lang.String conditionsOfUsage) {
        this.conditionsOfUsage = conditionsOfUsage;
    }

    public void setConditionsOfUsage(java.lang.String conditionsOfUsage, boolean forceUpdate) {
        setConditionsOfUsage(conditionsOfUsage);

        if (forceUpdate) {
          addForceUpdateProperty("conditionsOfUsage");
        }
    }

    public java.lang.String getLlpayCode() {
        return llpayCode;
    }

    public void setLlpayCode(java.lang.String llpayCode) {
        this.llpayCode = llpayCode;
    }

    public void setLlpayCode(java.lang.String llpayCode, boolean forceUpdate) {
        setLlpayCode(llpayCode);

        if (forceUpdate) {
          addForceUpdateProperty("llpayCode");
        }
    }

    public java.lang.String getBankNo() {
        return bankNo;
    }

    public void setBankNo(java.lang.String bankNo) {
        this.bankNo = bankNo;
    }

    public void setBankNo(java.lang.String bankNo, boolean forceUpdate) {
        setBankNo(bankNo);

        if (forceUpdate) {
          addForceUpdateProperty("bankNo");
        }
    }

    public java.lang.String getIssInsCd() {
        return issInsCd;
    }

    public void setIssInsCd(java.lang.String issInsCd) {
        this.issInsCd = issInsCd;
    }

    public void setIssInsCd(java.lang.String issInsCd, boolean forceUpdate) {
        setIssInsCd(issInsCd);

        if (forceUpdate) {
          addForceUpdateProperty("issInsCd");
        }
    }
}
