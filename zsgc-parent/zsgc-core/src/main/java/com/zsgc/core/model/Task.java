package com.zsgc.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 任务表
 * 
 * @author lyd
 *
 */
public class Task {
	private Integer taskId;

	private Integer uId;

	private Integer pId;

	private Integer warehouseType;

	private String taskName;

	private Integer taskState;

	private Integer delState;

//    private Integer taskTimeType;

	private Date taskTime;

	private Integer taskType;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	private Integer clock;

	private Integer shareType;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	private Integer updateState;

	private Integer associatedTask;

	private String updateName;

	private List<TaskDescription> tdList = new ArrayList<TaskDescription>();

//    private List<TaskTime> ttList = new ArrayList<TaskTime>();

//    public List<TaskTime> getTtList() {
//		return ttList;
//	}
//
//	public void setTtList(List<TaskTime> ttList) {
//		this.ttList = ttList;
//	}

	public String getUpdateName() {
		return updateName;
	}

	public Integer getShareType() {
		return shareType;
	}

	public void setShareType(Integer shareType) {
		this.shareType = shareType;
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getClock() {
		return clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

	public Date getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Integer getUpdateState() {
		return updateState;
	}

	public void setUpdateState(Integer updateState) {
		this.updateState = updateState;
	}

	public Integer getAssociatedTask() {
		return associatedTask;
	}

	public void setAssociatedTask(Integer associatedTask) {
		this.associatedTask = associatedTask;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public List<TaskDescription> getTdList() {
		return tdList;
	}

	public void setTdList(List<TaskDescription> tdList) {
		this.tdList = tdList;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

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

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskState() {
		return taskState;
	}

	public void setTaskState(Integer taskState) {
		this.taskState = taskState;
	}

	public Integer getDelState() {
		return delState;
	}

	public void setDelState(Integer delState) {
		this.delState = delState;
	}

//	public Integer getTaskTimeType() {
//		return taskTimeType;
//	}
//
//	public void setTaskTimeType(Integer taskTimeType) {
//		this.taskTimeType = taskTimeType;
//	}
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(Integer warehouseType) {
		this.warehouseType = warehouseType;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", uId=" + uId + ", pId=" + pId + ", warehouseType=" + warehouseType
				+ ", taskName=" + taskName + ", taskState=" + taskState + ", delState=" + delState + ", taskTime="
				+ taskTime + ", taskType=" + taskType + ", startTime=" + startTime + ", endTime=" + endTime + ", clock="
				+ clock + ", shareType=" + shareType + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", updateState=" + updateState + ", associatedTask=" + associatedTask + ", updateName=" + updateName
				+ ", tdList=" + tdList + "]";
	}

}