package com.zsgc.core.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.calanger.common.bo.BO;
import com.zsgc.core.model.CustomerProfile;
import com.zsgc.core.model.Task;
import com.zsgc.core.model.WarehouseProjects;

public interface TaskBO extends BO<Task, java.lang.Integer> {
	/**
	 * 创建新任务
	 */
	String createNewTask(Task task);

	/**
	 * 根据任务表id获取任务具体信息
	 */
	Task getTaskById(Integer taskId);

	/**
	 * 更新任务本身以及创建更新任务日志，追加创建任务时的日志任务完成状态和更新时间
	 */
	Integer updateTasks(Task task);

	/**
	 * 根据用户id和任务id删除任务
	 */
	Integer deleteTask(Integer uId, Integer taskId);

	/**
	 * 根据用户id和项目id查看我的笔记本
	 */
	List<Task> getMyNote(Integer uId, Integer pId);

	/**
	 * 招商笔记任务概况，数据看板
	 */
	Map<String, Object> getTasksOverview(Integer uId, Integer warehouseType, Integer taskTimeType,
			ArrayList<Date> taskTimes);

	/**
	 * 更改共享笔记状态
	 */
	Integer changeShareType(Integer uId, Integer pId, Integer shareType);

	/**
	 * 建立项目生成日志
	 * 
	 * @param warehouse
	 */
	void createNewProLog(WarehouseProjects warehouse);

	/**
	 * 删除项目生成日志
	 * 
	 * @param warehouse
	 */
	void deleteProLog(WarehouseProjects warehouse);

	/**
	 * 恢复项目生成日志
	 * 
	 * @param warehouse
	 */
	void regainProLog(WarehouseProjects warehouse);

	/**
	 * 更改项目状态生成日志
	 * 
	 * @param warehouse
	 */
	void changeProStateLog(WarehouseProjects warehouse);

	/**
	 * 更改项目仓库生成日志
	 * 
	 * @param warehouse
	 */
	void changeWarehouseLog(WarehouseProjects warehouse);

	/**
	 * 新建客户图谱生成日志
	 * 
	 * @param uId
	 * @param cusPro
	 */
	void createNewCusLog(Integer uId, CustomerProfile cusPro);

	/**
	 * 修改客户生成日志
	 * 
	 * @param uId
	 * @param cusPro
	 */
	void updateCusLog(Integer uId, CustomerProfile cusPro);

	/**
	 * 删除客户生成日志
	 * 
	 * @param uId
	 * @param cusPro
	 */
	void deleteCusLog(Integer uId, CustomerProfile cusPro);

	/**
	 * 项目列表，任务数据展现
	 */
	List<Task> getTasksByPidTimes(Integer uId, Integer pId, Integer warehouseType, ArrayList<Date> dates);

	/**
	 * 查看共享笔记
	 */
	List<Task> getShareType(Integer uId, Integer pId);
}
