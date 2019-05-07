package com.zsgc.api.dto;

import com.zsgc.core.model.CustomerProfile;
/**
 * 更新客户图谱接收类
 * @author lyd
 *
 */
public class UpdateCusProAccept {
	/**
	 * 关联客户id
	 */
	private Integer relatedId;
	/**
	 * 关系类型，0 同级 1下级（新建客户是关联id的下级） 2上级（新建客户是关联id的上级） 
	 */
	private Integer relationType;
	/**
	 * 新建或更新客户信息
	 */
	private CustomerProfile cusPro;

	public Integer getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Integer relatedId) {
		this.relatedId = relatedId;
	}

	public Integer getRelationType() {
		return relationType;
	}

	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}

	public CustomerProfile getCusPro() {
		return cusPro;
	}

	public void setCusPro(CustomerProfile cusPro) {
		this.cusPro = cusPro;
	}

	@Override
	public String toString() {
		return "NewCusProAccept [relatedId=" + relatedId + ", relationType=" + relationType + ", cusPro=" + cusPro
				+ "]";
	}
	
	
}
