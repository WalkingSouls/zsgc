package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

import java.util.Date;

public class ActivityAddress extends AbstractPO {

    private static final long serialVersionUID = 1L;

    private Integer id;
/*
    *//**
     * 收货地址
     *//*
    private String shippingAddress;*/

    /**
     * 收件人
     */
    private String recipients;

    /**
     * 联系方式
     */
    private String contactNumber;

    /**
     * 详细地址
     */
    private String addressDetail;

    private Integer userId;

    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(Integer id,boolean forceUpdate) {
        setId(id);
        if(forceUpdate){
            addForceUpdateProperty("id");
        }
    }


   /* public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public void setShippingAddress(String shippingAddress, boolean forceUpdate ) {
        setShippingAddress(shippingAddress);
        if(forceUpdate){
            addForceUpdateProperty("shippingAddress");
        }
    }*/


    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }
    public void setRecipients(String recipients,boolean forceUpdate) {
        setRecipients(recipients);
        if(forceUpdate){
            addForceUpdateProperty("recipients");
        }
    }



    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setContactNumber(String contactNumber,boolean forceUpdate) {
        setContactNumber(contactNumber);
        if(forceUpdate){
            addForceUpdateProperty("contactNumber");
        }
    }


    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
    public void setAddressDetail(String addressDetail,boolean forceUpdate) {
        setAddressDetail(addressDetail);
        if (forceUpdate){
            addForceUpdateProperty("addressDetail");
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserId(Integer userId,boolean forceUpdate) {
        setUserId(userId);
        if(forceUpdate){
            addForceUpdateProperty("userId");
        }
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(Date createdAt,boolean forceUpdate) {
        setCreatedAt(createdAt);

        if(forceUpdate){
            addForceUpdateProperty("createdAt");
        }
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUpdatedAt(Date updatedAt,boolean forceUpdate) {
        setUpdatedAt(updatedAt);

        if(forceUpdate){
            addForceUpdateProperty("updatedAt");
        }
    }
}
