package com.zsgc.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务描述表
 * @author lyd
 *
 */
public class TaskDescription {
    private Integer desId;

    private Integer taskId;

    private String taskDescription;

    private Integer delState;
    
    private List<DesPicture> dpList = new ArrayList<DesPicture>();

    public List<DesPicture> getDpList() {
		return dpList;
	}

	public void setDpList(List<DesPicture> dpList) {
		this.dpList = dpList;
	}

	public Integer getDesId() {
        return desId;
    }

    public void setDesId(Integer desId) {
        this.desId = desId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }
}