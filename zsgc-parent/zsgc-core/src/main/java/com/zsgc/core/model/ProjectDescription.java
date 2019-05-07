package com.zsgc.core.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectDescription {
    private Integer desId;

    private String desWords;

    private Integer pId;

    private Integer desState;
    
    private List<ProjectDescriptionPicture> list = new ArrayList<>();
    
    public Integer getDesId() {
        return desId;
    }

    public void setDesId(Integer desId) {
        this.desId = desId;
    }

    public String getDesWords() {
        return desWords;
    }

    public void setDesWords(String desWords) {
        this.desWords = desWords;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getDesState() {
        return desState;
    }

    public void setDesState(Integer desState) {
        this.desState = desState;
    }

	public List<ProjectDescriptionPicture> getList() {
		return list;
	}

	public void setList(List<ProjectDescriptionPicture> list) {
		this.list = list;
	}

}