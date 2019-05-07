package com.zsgc.core.model;

import java.math.BigDecimal;
import java.util.Date;

import com.calanger.common.dao.AbstractPO;

public class WarehouseProject extends AbstractPO {
    private static final long serialVersionUID = 1L;
    private Integer pId;

    private String projectName;

    private String intentionalCity;

    private String investmentAmount;

    private Boolean warehouseType;

    private String city;

    private String industry;

    private Boolean state;

    private Boolean delState;

    private Date createP;

    private Date updateP;

    private Integer uId;

    private Boolean delCause;

    private BigDecimal score;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getIntentionalCity() {
        return intentionalCity;
    }

    public void setIntentionalCity(String intentionalCity) {
        this.intentionalCity = intentionalCity;
    }

    public String getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(String investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public Boolean getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(Boolean warehouseType) {
        this.warehouseType = warehouseType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getDelState() {
        return delState;
    }

    public void setDelState(Boolean delState) {
        this.delState = delState;
    }

    public Date getCreateP() {
        return createP;
    }

    public void setCreateP(Date createP) {
        this.createP = createP;
    }

    public Date getUpdateP() {
        return updateP;
    }

    public void setUpdateP(Date updateP) {
        this.updateP = updateP;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Boolean getDelCause() {
        return delCause;
    }

    public void setDelCause(Boolean delCause) {
        this.delCause = delCause;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}