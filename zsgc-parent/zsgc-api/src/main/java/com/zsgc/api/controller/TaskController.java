package com.zsgc.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsgc.api.dto.ApiResult;
import com.zsgc.core.bo.TaskBO;
import com.zsgc.core.bo.WarehouseProjectsBO;
import com.zsgc.core.dao.TaskDAO;
import com.zsgc.core.model.CustomerProfile;
import com.zsgc.core.model.Task;
import com.zsgc.core.model.WarehouseProjects;

@Controller
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskBO taskBO;
	@Autowired
	private TaskDAO taskDAO;
	@Autowired
	private WarehouseProjectsBO warehouseProjectsBO;
	/**
	 * 新增/更新任务
	 * @param task
	 * @return
	 */
	@RequestMapping(value = "/changeTask", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Map<String, Object>> changeTask(@RequestBody Task task) {
		if (task.getTaskId() != null) {
			ApiResult<Map<String, Object>> updateTask = updateTask(task);
			return new ApiResult<Map<String, Object>>(updateTask.getCode(), updateTask.getMsg());
		} else {
			ApiResult<Map<String, Object>> createNewTask = createNewTask(task);
			return new ApiResult<Map<String, Object>>(createNewTask.getCode(), createNewTask.getMsg(),
					createNewTask.getData());
		}
	}
	/**
	 * 新增任务
	 * @param task
	 * @return
	 */
	public ApiResult<Map<String, Object>> createNewTask(Task task) {
		try {
			Task taskDB = taskDAO.getTodayTask(task.getuId(), task.getpId(), new Date());
			if (taskDB != null) {
				ApiResult<Map<String, Object>> getTask = getTaskById(task.getuId(), task.getpId(), taskDB.getTaskId(),
						2);
				return new ApiResult<Map<String, Object>>(0, "该项目您今天已经创建过任务了", getTask.getData());
			} else {
				taskBO.createNewTask(task);
			}
		} catch (Exception e) {
			return new ApiResult<Map<String, Object>>(1, "任务创建失败");
		}
		return new ApiResult<Map<String, Object>>(0, "任务创建成功");
	}
	/**
	 * 更新任务
	 * @param task
	 * @return
	 */
	public ApiResult<Map<String, Object>> updateTask(Task task) {
		try {
			taskBO.updateTasks(task);
		} catch (Exception e) {
			return new ApiResult<Map<String, Object>>(1, "修改失败");
		}
		return new ApiResult<Map<String, Object>>(0, "修改成功");
	}
	/**
	 * 根据id查任务
	 * @param uId
	 * @param pId
	 * @param associatedTask
	 * @param taskType
	 * @return
	 */
	@RequestMapping(value = "/getTaskById/{uId}/{pId}/{associatedTask}/{taskType}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, Object>> getTaskById(@PathVariable Integer uId, @PathVariable Integer pId,
			@PathVariable Integer associatedTask, @PathVariable Integer taskType) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (taskType == 4) {
			CustomerProfile task = warehouseProjectsBO.getByCusId(pId, associatedTask);
			if (task == null) {
				return new ApiResult<Map<String, Object>>(3, "该联系人已不存在");
			}
			params.put("task", task);
			return new ApiResult<Map<String, Object>>(0, "查询成功", params);
		} else if (taskType != 2 && taskType != 3) {
			return new ApiResult<Map<String, Object>>(2, "信息出错，该条任务已不存在");
		} else {
			Task task = null;
			try {
				task = taskBO.getTaskById(associatedTask);
				System.out.println(task.toString());
			} catch (Exception e) {
				return new ApiResult<Map<String, Object>>(1, "查询失败");
			}
			params.put("task", task);
			return new ApiResult<Map<String, Object>>(0, "查询成功", params);
		}
	}
	/**
	 * 删除任务
	 * @param uId
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/deleteTask/{uId}/{taskId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, Object>> deleteTask(@PathVariable Integer uId, @PathVariable Integer taskId) {
		try {
			Integer rows = taskBO.deleteTask(uId, taskId);
			if (rows == -1) {
				return new ApiResult<Map<String, Object>>(2, "您没有权限删除此任务");
			}
		} catch (Exception e) {
			return new ApiResult<Map<String, Object>>(1, "删除失败");
		}
		return new ApiResult<Map<String, Object>>(0, "删除成功");
	}
	/**
	 * 查看我的笔记本
	 * @param uId
	 * @param pId
	 * @return
	 */
	@RequestMapping(value = "/getMyNote/{uId}/{pId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, Object>> getMyNote(@PathVariable Integer uId, @PathVariable Integer pId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		List<Task> taskList = taskBO.getMyNote(uId, pId);
		if (taskList == null) {
			return new ApiResult<Map<String, Object>>(1, "暂时没有任务数据");
		}
		LinkedHashMap<String, List<Task>> datesMap = new LinkedHashMap<String, List<Task>>();
		for (Task task : taskList) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
			String dateTemp = sdf.format(task.getCreateTime());
			if(datesMap.get(dateTemp) != null) {
				datesMap.get(dateTemp).add(task);
			}else {
				ArrayList<Task> tasksTemp = new ArrayList<Task>();
				tasksTemp.add(task);
				datesMap.put(dateTemp, tasksTemp);
			}
		}
		params.put("tasks", datesMap);
		return new ApiResult<Map<String, Object>>(0, "查询成功", params);
	}
	/**
	 * 更改招商笔记分享状态
	 * @param uId
	 * @param pId
	 * @param shareType 0 私有仓 1 公有仓
	 * @return
	 */
	@RequestMapping(value = "/changeShareType/{uId}/{pId}/{shareType}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, Object>> changeShareType(@PathVariable Integer uId,
			@PathVariable Integer pId, @PathVariable Integer shareType){
		HashMap<String, Object> params = new HashMap<String, Object>();
		WarehouseProjects warehouse = warehouseProjectsBO.get(pId);
		if(warehouse.getuId().equals(uId)&&
				(warehouse.getWarehouseType().equals(1)||warehouse.getWarehouseType().equals(0))) {
			taskBO.changeShareType(uId,pId,shareType);
		}else {
			return new ApiResult<Map<String, Object>>(0, "您没有此权限", params);
		}
		return new ApiResult<Map<String, Object>>(0, "共享状态变更成功", params);
	}
}
