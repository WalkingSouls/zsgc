package com.zsgc.core.model;

import java.util.Date;
/**
 * 任务时间表
 * @author lyd
 *
 */
public class TaskTime {
    private Integer timeId;

    private Integer taskId;

    private Date taskDate;

    private Date startTime;

    private Date endTime;

    private Integer clock;

    private Integer delState;

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

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

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

	@Override
	public String toString() {
		return "TaskTime [timeId=" + timeId + ", taskId=" + taskId + ", taskDate=" + taskDate + ", startTime="
				+ startTime + ", endTime=" + endTime + ", clock=" + clock + ", delState=" + delState + "]";
	}
    
    
}