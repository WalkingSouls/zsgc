package com.zsgc.core.model;

import java.io.Serializable;

public class BankInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 993289438338416057L;

	private Integer mybankId;
	private Integer uid;
	private String bankName;
	private String number;
	private Integer status;
	private String userName;
	private String code;
	
	public BankInfo() {
	}
	public Integer getMybankId() {
		return mybankId;
	}
	public void setMybankId(Integer mybankId) {
		this.mybankId = mybankId;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
