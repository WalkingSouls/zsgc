package com.zsgc.core.model;

import java.math.BigDecimal;

public class ProjectShow {
	
	private String projectName;

	private Integer followNum;

	private BigDecimal score;
	
	private Integer isCollection;
	

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

	@Override
	public String toString() {
		return "ProjectShow [projectName=" + projectName + ", followNum=" + followNum + ", score=" + score + "]";
	}
	
	
}
