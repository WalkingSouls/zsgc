package com.zsgc.core.model;

import java.util.ArrayList;
import java.util.List;

public class HomePageWarehouseProjects {
    private Integer pId;

    private String projectName;
    
    private String state;
    
	private Byte firstTop;
	
	private Integer uId;
	
	private List<Task> list = new ArrayList<>();

	
	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public List<Task> getList() {
		return list;
	}

	public void setList(List<Task> list) {
		this.list = list;
	}

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

	public Byte getFirstTop() {
		return firstTop;
	}

	public void setFirstTop(Byte firstTop) {
		this.firstTop = firstTop;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
    
   
    