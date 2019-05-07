package com.zsgc.api.dto;

import java.util.ArrayList;
import java.util.Date;
/**
 * 招商笔记时间筛选参数接收
 * @author lyd
 *
 */
public class InvestOverviewAccept {
	private Integer uId;
	
	/*所属仓*/
	private Integer warehouseType;
	
	/*筛选类型，0 单日 1 多日 2 起止*/
	private Integer taskTimeType;
	
	/*筛选时间集合，taskTimeType为2时，集合size为2，起止时间*/
	private ArrayList<Date> taskTimes = new ArrayList<Date>();

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(Integer warehouseType) {
		this.warehouseType = warehouseType;
	}

	public Integer getTaskTimeType() {
		return taskTimeType;
	}

	public void setTaskTimeType(Integer taskTimeType) {
		this.taskTimeType = taskTimeType;
	}

	public ArrayList<Date> getTaskTimes() {
		return taskTimes;
	}

	public void setTaskTimes(ArrayList<Date> taskTimes) {
		this.taskTimes = taskTimes;
	}

	@Override
	public String toString() {
		return "InvestOverviewAccept [uId=" + uId + ", warehouseType=" + warehouseType + ", taskTimeType="
				+ taskTimeType + ", taskTimes=" + taskTimes + "]";
	}
	
	
}
