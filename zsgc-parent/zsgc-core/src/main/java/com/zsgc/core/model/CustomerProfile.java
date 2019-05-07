package com.zsgc.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerProfile {
    private Integer customerId;

    private Integer pId;

    private String customerName;

    private String customerJob;

    private String telephone;

    private String remark;

    private Integer delState;

    private Date createDate;

    private Date updateDate;

    private String other;

    private Integer parentId;
    
    private List<CustomerProfile> list = new ArrayList<>();
	public List<CustomerProfile> getList() {
		return list;
	}

	public void setList(List<CustomerProfile> list) {
		this.list = list;
	}

	public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerJob() {
        return customerJob;
    }

    public void setCustomerJob(String customerJob) {
        this.customerJob = customerJob;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

	@Override
	public String toString() {
		return "CustomerProfile [customerId=" + customerId + ", pId=" + pId + ", customerName=" + customerName
				+ ", customerJob=" + customerJob + ", telephone=" + telephone + ", remark=" + remark + ", delState="
				+ delState + ", createDate=" + createDate + ", updateDate=" + updateDate + ", other=" + other
				+ ", parentId=" + parentId + ", list=" + list + "]";
	}
    
    
}