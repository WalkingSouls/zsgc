package com.zsgc.core.model;

import java.math.BigDecimal;

public class ProjectMarket {
	private Integer uId;
	
	private Integer pId;
	
	private String projectName;
	
	private Integer followNum;
	
	private BigDecimal score;
	
	private String avatarPath;
	
	private String userName;
	
	private String introduction;
	
	private Integer attenState;
	
	private Integer isCollection;
	
	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(Integer isCollection) {
		this.isCollection = isCollection;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getFollowNum() {
		return followNum;
	}

	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getAttenState() {
		return attenState;
	}

	public void setAttenState(Integer attenState) {
		this.attenState = attenState;
	}

	@Override
	public String toString() {
		return "ProjectMarket [pId=" + pId + ", projectName=" + projectName + ", followNum=" + followNum + ", score="
				+ score + ", avatarPath=" + avatarPath + ", userName=" + userName + ", introduction=" + introduction
				+ ", attenState=" + attenState + ", isCollection=" + isCollection + "]";
	}

	
}
