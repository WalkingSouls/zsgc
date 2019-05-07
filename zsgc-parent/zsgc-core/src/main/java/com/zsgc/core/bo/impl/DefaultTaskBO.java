package com.zsgc.core.bo.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.DictCacheBO;
import com.zsgc.core.bo.JPushBO;
import com.zsgc.core.bo.TaskBO;
import com.zsgc.core.dao.DesPictureDAO;
import com.zsgc.core.dao.TaskDAO;
import com.zsgc.core.dao.TaskDescriptionDAO;
import com.zsgc.core.dao.UcenterMemberTempDAO;
import com.zsgc.core.dao.WarehouseProjectsDAO;
import com.zsgc.core.model.CustUserMsg;
import com.zsgc.core.model.CustomerProfile;
import com.zsgc.core.model.DesPicture;
import com.zsgc.core.model.Task;
import com.zsgc.core.model.TaskDescription;
import com.zsgc.core.model.UcenterMember;
import com.zsgc.core.model.WarehouseProjects;

@Service("taskBO")
public class DefaultTaskBO extends AbstractBO<Task, java.lang.Integer> implements TaskBO {
	@Autowired
	private TaskDAO taskDAO;

	@Autowired
	private TaskDescriptionDAO taskDescriptionDAO;

	@Autowired
	private DesPictureDAO desPictureDAO;

//	@Autowired
//	private TaskTimeDAO taskTimeDAO;

	@Autowired
	private WarehouseProjectsDAO warehouseProjectsDAO;

	@Autowired
	private JPushBO jPushBO;

	@Autowired
	private DictCacheBO dictCacheBO;

	@Autowired
	private UcenterMemberTempDAO ucenterMemberTempDAO;

	@Override
	protected DAO<Task, Integer> getDAO() {
		return taskDAO;
	}

	/*
	 * 创建新任务
	 */
	@Override
	public String createNewTask(Task task) {
		String msg = null;
		List<TaskDescription> tdList = task.getTdList();
		task.setTaskId(generateId(task.getTaskId()));
		task.setCreateTime(new Date());
		task.setDelState(1);
		task.setTaskType(1);
		taskDAO.insert(task);
		if (tdList != null) {
			for (TaskDescription tdTemp : tdList) {
				tdTemp.setDelState(task.getDelState());
				tdTemp.setDesId(generateId(tdTemp.getDesId()));
				tdTemp.setTaskId(task.getTaskId());
				taskDescriptionDAO.insert(tdTemp);
				List<DesPicture> dpList = tdTemp.getDpList();
				if (dpList != null) {
					for (DesPicture dpTemp : dpList) {
						dpTemp.setPicId(generateId(dpTemp.getPicId()));
						dpTemp.setDesId(tdTemp.getDesId());
						dpTemp.setDelState(task.getDelState());
						desPictureDAO.insert(dpTemp);
					}
				}
			}
		}
//		createNewTaskTimes(task);
		createClock(task);
		createNewTaskLog(task);
//		UcenterMember ucenterMember = new UcenterMember();
//		ucenterMember.setId(task.getuId());
//		ucenterMember.setUpdatedAt(new Date());
//		Integer tas = ucenterMemberTempDAO.updatetask(ucenterMember);
//		if (tas == null || tas == 0) {
//			msg = "发布任务获得经验已到今日上限";
//		} else {
//			msg = "经验+5";
//		}
		return msg;
	}
	/**
	 * 得到对应任务提醒减少时间
	 * @param clock
	 * @return
	 */
	private Integer getClock(Integer clock) {
		switch (clock) {
		case 0:
			clock = 0;
			break;
		case 1:
			clock = -5;
			break;
		case 2:
			clock = -15;
			break;
		case 3:
			clock = -30;
			break;
		case 4:
			clock = -45;
			break;
		case 5:
			clock = -60;
			break;

		default:
			clock = 0;
			break;
		}
		return clock;
	}

	/**
	 * 调用推送，定时发送
	 * 
	 * @param task
	 */
	private void createClock(Task task) {
		CustUserMsg msg = createMsg(task);
		msg.setCreatedAt(new Date());
		msg.setUpdatedAt(msg.getCreatedAt());
		jPushBO.insMysqlAndCache(msg, task.getTaskId());
	}
	/**
	 * 创建推送消息
	 * @param task
	 * @return
	 */
	private CustUserMsg createMsg(Task task) {
		CustUserMsg msg = new CustUserMsg();
		Integer clock = getClock(task.getClock());
		Calendar cal = Calendar.getInstance();
		cal.setTime(task.getStartTime());
		cal.add(Calendar.MINUTE, clock);
		Date pushTime = cal.getTime();
		msg.setUserId(task.getuId());
		msg.setMsgType(Byte.parseByte("6"));
		msg.setMsgContent(task.getTaskName() + " 设置时间到了");
		msg.setMsgTitle(task.getTaskName() + " 设置时间到了");
		msg.setMsgClient("all");
		msg.setMsgStatus(Byte.parseByte("1"));
		msg.setMsgPushStatus(Byte.parseByte("1"));
		msg.setMsgPushStartAt(pushTime);
		msg.setMsgPushEndAt(msg.getMsgPushStartAt());
		return msg;
	}
	/**
	 * 更改推送，更新
	 * 
	 * @param task
	 */
	private void updateClock(Task task) {
		Task taskdb = taskDAO.get(task.getTaskId());
		if (task.getStartTime().equals(taskdb.getStartTime()) && task.getClock().equals(taskdb.getClock())) {
			
		} else {
			CustUserMsg msg = createMsg(task);
			msg.setCreatedAt(new Date());
			msg.setUpdatedAt(new Date());
			jPushBO.updateMysqlAndCache(msg, task.getTaskId());
		}
	}

	/**
	 * 取消推送
	 * 
	 * @param task
	 */
	private void deleteClock(Task task) {
		jPushBO.delMsg(task.getTaskId());;
	}
	/**
	 * 创建任务时判断并创建任务时间类型，写入表
	 * 
	 * @param task
	 */
//	public void createNewTaskTimes(Task task) {
//		List<TaskTime> ttList = task.getTtList();
//		if (ttList != null) {
//			if (task.getTaskTimeType() == 0 || task.getTaskTimeType() == 1) {
//				for (TaskTime taskTime : ttList) {
//					taskTime.setTimeId(generateId(taskTime.getTimeId()));
//					taskTime.setTaskId(task.getTaskId());
//					taskTime.setDelState(1);
//					taskTimeDAO.insert(taskTime);
//				}
//			} else if (task.getTaskTimeType() == 2) {
//				Date[] ttTemp = new Date[2];
//				Date start = null;
//				Date end = null;
//				Integer clock = null;
//				for (int i = 0; i < ttTemp.length; i++) {
//					for (int j = 0; j < ttList.size(); j++) {
//						ttTemp[j] = ttList.get(j).getTaskDate();
//						start = ttList.get(0).getStartTime();
//						end = ttList.get(0).getEndTime();
//						clock = ttList.get(0).getClock();
//					}
//				}
//				Date beginTime = ttTemp[0].compareTo(ttTemp[1]) < 0 ? ttTemp[0] : ttTemp[1];
//				Date endTime = ttTemp[0].compareTo(ttTemp[1]) > 0 ? ttTemp[0] : ttTemp[1];
//				List<Date> Dates = new ArrayList<Date>();
//				Dates.add(beginTime);
//				Calendar calBegin = Calendar.getInstance();
//				calBegin.setTime(beginTime);
//				Calendar calEnd = Calendar.getInstance();
//				calEnd.setTime(endTime);
//				while (endTime.after(calBegin.getTime())) {
//					calBegin.add(Calendar.DAY_OF_MONTH, 1);
//					Dates.add(calBegin.getTime());
//				}
//				for (Date date : Dates) {
//					TaskTime taskTemp = new TaskTime();
//					taskTemp.setTimeId(generateId(taskTemp.getTimeId()));
//					taskTemp.setTaskId(task.getTaskId());
//					taskTemp.setTaskDate(date);
//					taskTemp.setClock(clock);
//					taskTemp.setDelState(1);
//					taskTemp.setStartTime(start);
//					taskTemp.setEndTime(end);
//					taskTimeDAO.insert(taskTemp);
//				}
//			}
//		}
//	}

	/**
	 * 根据任务表id获取任务具体信息
	 */
	@Override
	public Task getTaskById(Integer taskId) {
		Task task = taskDAO.get(taskId);
		if (task != null) {
			TaskDescription tdt = new TaskDescription();
			tdt.setTaskId(taskId);
			tdt.setDelState(task.getDelState());
			ArrayList<TaskDescription> taskDescription = (ArrayList<TaskDescription>) taskDescriptionDAO.getList(tdt);
			if (taskDescription != null) {
				for (TaskDescription tdTemp : taskDescription) {
					DesPicture desPic = new DesPicture();
					desPic.setDesId(tdTemp.getDesId());
					desPic.setDelState(tdTemp.getDelState());
					ArrayList<DesPicture> dpt = (ArrayList<DesPicture>) desPictureDAO.getList(desPic);
					tdTemp.setDpList(dpt);
				}
				task.setTdList(taskDescription);
			}
//			TaskTime tt = new TaskTime();
//			tt.setTaskId(taskId);
//			tt.setDelState(1);
//			ArrayList<TaskTime> taskTime = (ArrayList<TaskTime>) taskTimeDAO.getList(tt);
//			if (taskTime != null) {
//				task.setTtList(taskTime);
//			}
		}
		return task;
	}

	/**
	 * 更新任务本身以及创建更新任务日志，追加创建任务时的日志任务完成状态和更新时间
	 */
	@Override
	public Integer updateTasks(Task t) {
		try {
			t.setUpdateTime(new Date());
			Task taskTemp = t;
			// 根日志追加更新日志
			appendTaskLog(t);
			// 更新任务日期
//			updateTaskTimes(t);
			//更改任务日期和任务提醒推送时间
			updateClock(t);
			// 更新任务描述图片
			updateTaskDesPic(t);
			taskDAO.update(t, t.getTaskId());
			// 生成更新日志
			updateTaskLog(taskTemp);
		} catch (Exception e) {
			return null;
		}
		return 1;
	}

	/**
	 * 更新任务更新任务时间
	 * 
	 * @param t
	 */
//	public void updateTaskTimes(Task t) {
//		taskTimeDAO.remove(t.getTaskId());
//		createNewTaskTimes(t);
//	}

	/**
	 * 更新任务描述和关联图片
	 * 
	 * @param t
	 */
	private void updateTaskDesPic(Task t) {
		List<TaskDescription> tdList = t.getTdList();
		if (tdList != null) {
			for (TaskDescription tdTemp : tdList) {
				if (tdTemp.getDesId() == null) {
					tdTemp.setDesId(generateId(tdTemp.getDesId()));
					tdTemp.setTaskId(t.getTaskId());
					tdTemp.setDelState(t.getDelState());
					taskDescriptionDAO.insert(tdTemp);
					List<DesPicture> dpTemps = tdTemp.getDpList();
					if (dpTemps != null) {
						for (DesPicture dpTemp : dpTemps) {
							dpTemp.setDelState(1);
							dpTemp.setDesId(tdTemp.getDesId());
							desPictureDAO.insert(dpTemp);
						}
					}
				}
				taskDescriptionDAO.update(tdTemp, tdTemp.getDesId());
				TaskDescription taskDescription = new TaskDescription();
				taskDescription.setDelState(1);
				taskDescription.setTaskId(t.getTaskId());
				List<TaskDescription> dbList = taskDescriptionDAO.getList(taskDescription);
				ArrayList<Integer> newidsList = getIds1(tdList);
				if (dbList != null) {
					ArrayList<Integer> dbids = getIds1(dbList);
					for (Integer dbid : dbids) {
						if (!newidsList.contains(dbid)) {
							TaskDescription tasDes = taskDescriptionDAO.get(dbid);
							tasDes.setDelState(0);
							taskDescriptionDAO.update(tasDes, tasDes.getDesId());
							List<DesPicture> dpTemp2List = tasDes.getDpList();
							if (dpTemp2List != null) {
								for (DesPicture desPic2 : dpTemp2List) {
									desPic2.setDelState(0);
									desPictureDAO.update(desPic2, desPic2.getPicId());
								}
							}
						}
					}
				}

				List<DesPicture> dpList = tdTemp.getDpList();
				if (dpList != null) {
					for (DesPicture dpTemp : dpList) {
						if (dpTemp.getPicId() == null) {
							dpTemp.setPicId(generateId(dpTemp.getPicId()));
							dpTemp.setDelState(tdTemp.getDelState());
							dpTemp.setDesId(tdTemp.getDesId());
							desPictureDAO.insert(dpTemp);
						}
						desPictureDAO.update(dpTemp, dpTemp.getPicId());
					}
					DesPicture desPicture = new DesPicture();
					desPicture.setDelState(1);
					desPicture.setDesId(tdTemp.getDesId());
					List<DesPicture> dpsTemp = desPictureDAO.getList(desPicture);
					ArrayList<Integer> newids = getIds(dpList);
					if (dpsTemp != null) {
						ArrayList<Integer> dbids = getIds(dpsTemp);
						for (Integer bdid : dbids) {
							if (!newids.contains(bdid)) {
								DesPicture desPicTe = desPictureDAO.get(bdid);
								desPicTe.setDelState(0);
								desPictureDAO.update(desPicTe, desPicTe.getPicId());
							}
						}
					}
				}
			}
		}

	}

	/**
	 * 根据用户id和任务id删除任务
	 */
	@Override
	public Integer deleteTask(Integer uId, Integer taskId) {
		Task task = taskDAO.get(taskId);
		if (!task.getuId().equals(uId)) {
			return -1;
		} else {
			task.setDelState(0);
			task.setUpdateTime(new Date());
			taskDAO.update(task, taskId);
			// TaskDescription tdt = new TaskDescription();
			// tdt.setTaskId(taskId);
			// List<TaskDescription> tdList = taskDescriptionDAO.getList(tdt);
			// if(tdList != null) {
			// for (TaskDescription tdTemp : tdList) {
			// tdTemp.setDelState(0);
			// taskDescriptionDAO.update(tdTemp, tdTemp.getDesId());
			// DesPicture desPic = new DesPicture();
			// desPic.setDesId(tdTemp.getDesId());
			// List<DesPicture> dpList = desPictureDAO.getList(desPic);
			// if(dpList != null) {
			// for (DesPicture dpTemp : dpList) {
			// dpTemp.setDelState(0);
			// desPictureDAO.update(dpTemp, dpTemp.getPicId());
			// }
			// }
			// }
			// }
			deleteClock(task);
			deleteTaskLog(task);
		}
		return 1;
	}

	/**
	 * 从集合里提取id集合
	 * 
	 * @param list
	 * @return
	 */
	private ArrayList<Integer> getIds1(List<TaskDescription> list) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (TaskDescription dp : list) {
			Integer tdId = dp.getDesId();
			ids.add(tdId);
		}
		return ids;
	}

	/**
	 * 从集合里提取id集合
	 * 
	 * @param list
	 * @return
	 */
	private ArrayList<Integer> getIds(List<DesPicture> list) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (DesPicture dp : list) {
			Integer picId = dp.getPicId();
			ids.add(picId);
		}
		return ids;
	}

	/**
	 * 生成随机id
	 * 
	 * @param integer
	 * @return
	 */
	private Integer generateId(Integer integer) {
		return (int) (System.currentTimeMillis() + (int) (Math.random() * 311));
	}

	/**
	 * 插入任务日志
	 * 
	 * @param task
	 * @return
	 */
	private Integer insertLog(Task task) {
		List<TaskDescription> tdList = task.getTdList();
		task.setTaskId(generateId(task.getTaskId()));
		taskDAO.insert(task);
		if (tdList != null) {
			for (TaskDescription tdTemp : tdList) {
				tdTemp.setDelState(task.getDelState());
				tdTemp.setDesId(generateId(tdTemp.getDesId()));
				tdTemp.setTaskId(task.getTaskId());
				taskDescriptionDAO.insert(tdTemp);
				List<DesPicture> dpList = tdTemp.getDpList();
				if (dpList != null) {
					for (DesPicture dpTemp : dpList) {
						dpTemp.setPicId(generateId(dpTemp.getPicId()));
						dpTemp.setDesId(tdTemp.getDesId());
						dpTemp.setDelState(task.getDelState());
						desPictureDAO.insert(dpTemp);
					}
				}
			}
		}
		return 1;
	}

	/**
	 * 更新日志
	 * 
	 * @param task
	 * @return
	 */
	private Integer updateLog(Task task) {
		taskDAO.update(task, task.getTaskId());
		return 1;
	}

	/**
	 * 建立项目生成日志
	 * 
	 * @param warehouse
	 */
	@Override
	public void createNewProLog(WarehouseProjects warehouse) {
		Task task = new Task();
		task.setTaskType(0);
		task.setuId(warehouse.getuId());
		task.setpId(warehouse.getpId());
		task.setTaskName("建立项目");
		task.setDelState(1);
		task.setCreateTime(warehouse.getCreateP());
		insertLog(task);
	}

	/**
	 * 删除项目生成日志
	 * 
	 * @param warehouse
	 */
	@Override
	public void deleteProLog(WarehouseProjects warehouse) {
		Task task = new Task();
		task.setTaskType(0);
		task.setTaskId(generateId(task.getTaskId()));
		task.setuId(warehouse.getuId());
		task.setpId(warehouse.getpId());
		task.setTaskName("删除项目");
		task.setDelState(1);
		task.setCreateTime(warehouse.getUpdateP());
		List<TaskDescription> tdList = task.getTdList();
		TaskDescription td = new TaskDescription();
		td.setDelState(1);
		td.setTaskId(task.getTaskId());
		td.setTaskDescription(task.getCreateTime() + " 删除项目，原因：" + warehouse.getDelCause());
		tdList.add(td);
		task.setTdList(tdList);
		Task taskTemp = new Task();
		taskTemp.setpId(warehouse.getpId());
		taskTemp.setTaskType(1);
		Task task2 = taskDAO.get(taskTemp);
		if (task2 != null) {
			task2.setDelState(0);
			task2.setUpdateTime(new Date());
			taskDAO.update(task2, task2.getTaskId());
		}
		insertLog(task);
	}

	/**
	 * 恢复项目生成日志
	 * 
	 * @param warehouse
	 */
	@Override
	public void regainProLog(WarehouseProjects warehouse) {
		Task task = new Task();
		task.setTaskType(0);
		task.setuId(warehouse.getuId());
		task.setpId(warehouse.getpId());
		task.setTaskName("恢复项目");
		task.setDelState(1);
		task.setCreateTime(warehouse.getUpdateP());
		insertLog(task);
	}

	/**
	 * 更改项目状态生成日志
	 * 
	 * @param warehouse
	 */
	@Override
	public void changeProStateLog(WarehouseProjects warehouse) {
		Task task = new Task();
		task.setTaskType(0);
		task.setuId(warehouse.getuId());
		task.setpId(warehouse.getpId());
		String proState = dictCacheBO.getValue("projectState", warehouse.getState().toString());
		task.setTaskName("修改了项目状态：" + proState);
		task.setDelState(1);
		task.setCreateTime(warehouse.getUpdateP());
		insertLog(task);
	}

	/**
	 * 更改项目仓库生成日志
	 * 
	 * @param warehouse
	 */
	@Override
	public void changeWarehouseLog(WarehouseProjects warehouse) {
		Task task = new Task();
		task.setTaskType(0);
		task.setuId(warehouse.getuId());
		task.setpId(warehouse.getpId());
		String warehouseType = null;
		switch (warehouse.getWarehouseType()) {
		case 0:
			warehouseType = "私有仓";
			break;
		case 1:
			warehouseType = "公有仓";
			break;
		default:
			warehouseType = "私有仓";
			break;
		}
		task.setTaskName("修改了项目所属仓库：" + warehouseType);
		task.setDelState(1);
		task.setCreateTime(warehouse.getUpdateP());
		task.setWarehouseType(warehouse.getWarehouseType());
		insertLog(task);
		Task taskTemp = new Task();
		taskTemp.setuId(warehouse.getuId());
		taskTemp.setpId(warehouse.getpId());
		List<Task> tasks = taskDAO.getList(taskTemp);
		for (Task task2 : tasks) {
			task2.setWarehouseType(warehouse.getWarehouseType());
			taskDAO.update(task2, task2.getTaskId());
		}
	}

	/**
	 * 新建客户图谱生成日志
	 * 
	 * @param uId
	 * @param cusPro
	 */
	@Override
	public void createNewCusLog(Integer uId, CustomerProfile cusPro) {
		Task task = new Task();
		task.setTaskType(4);
		task.setuId(uId);
		task.setpId(cusPro.getpId());
		task.setTaskName("新建客户：" + cusPro.getCustomerName());
		task.setDelState(1);
		task.setCreateTime(cusPro.getUpdateDate());
		task.setAssociatedTask(cusPro.getCustomerId());
		insertLog(task);
	}

	/**
	 * 修改客户生成日志
	 * 
	 * @param uId
	 * @param cusPro
	 */
	@Override
	public void updateCusLog(Integer uId, CustomerProfile cusPro) {
		Task task = new Task();
		task.setTaskType(0);
		task.setuId(uId);
		task.setpId(cusPro.getpId());
		task.setTaskName("修改了客户：" + cusPro.getCustomerName() + "信息");
		task.setDelState(1);
		task.setCreateTime(cusPro.getUpdateDate());
		insertLog(task);
	}

	/**
	 * 删除客户生成日志
	 * 
	 * @param uId
	 * @param cusPro
	 */
	public void deleteCusLog(Integer uId, CustomerProfile cusPro) {
		Task task = new Task();
		task.setTaskType(0);
		task.setuId(uId);
		task.setpId(cusPro.getpId());
		task.setTaskName("删除了客户：" + cusPro.getCustomerName() + "信息");
		task.setDelState(1);
		task.setCreateTime(cusPro.getUpdateDate());
		insertLog(task);
	}

	/**
	 * 创建任务生成创建任务根日志
	 * 
	 * @param task
	 */
	private void createNewTaskLog(Task task) {
		task.setTaskName("创建任务：" + task.getTaskName());
		task.setTaskType(2);
		task.setAssociatedTask(task.getTaskId());
		insertLog(task);
	}

	/**
	 * 更新任务生成更新日志
	 * 
	 * @param task
	 */
	private void updateTaskLog(Task task) {
		Task taskUpdate = task;
		taskUpdate.setCreateTime(task.getUpdateTime());
		taskUpdate.setAssociatedTask(task.getTaskId());
		taskUpdate.setTaskType(3);
		taskUpdate.setTaskName("更新任务：" + task.getTaskName());
		insertLog(taskUpdate);
	}

	/**
	 * 更新任务时追加任务更新信息到根日志
	 * 
	 * @param task
	 */
	private void appendTaskLog(Task task) {
		Task taskAppend = task;
		Task taskTemp = new Task();
		taskTemp.setpId(task.getpId());
		taskTemp.setDelState(task.getDelState());
		taskTemp.setTaskType(2);
		taskTemp.setAssociatedTask(task.getTaskId());
		Task taskLog = taskDAO.get(taskTemp);
		taskLog.setTaskName("创建任务：" + task.getTaskName());
		taskLog.setWarehouseType(task.getWarehouseType());
		taskLog.setTaskTime(task.getTaskTime());
		taskLog.setStartTime(task.getStartTime());
		taskLog.setEndTime(task.getEndTime());
		taskLog.setClock(task.getClock());
		taskLog.setUpdateTime(new Date());
		String taskState = dictCacheBO.getValue("taskState", taskAppend.getTaskState().toString());
		;
		taskLog.setUpdateName("更新为" + taskState);
		taskLog.setUpdateState(taskAppend.getTaskState());
		TaskDescription taskDescription = new TaskDescription();
		taskDescription.setTaskId(taskLog.getTaskId());
		List<TaskDescription> listTemp = taskDescriptionDAO.getList(taskDescription);
		if (listTemp != null) {
			for (TaskDescription tdTemp : listTemp) {
				List<DesPicture> dpListTemp = tdTemp.getDpList();
				if (dpListTemp != null) {
					DesPicture desPicture = new DesPicture();
					desPicture.setDesId(tdTemp.getDesId());
					desPictureDAO.remove(desPicture);
				}
			}
			taskDescriptionDAO.remove(taskDescription);
		}
		List<TaskDescription> tdList = taskAppend.getTdList();
		if (tdList.size() != 0) {
			for (TaskDescription tdTemp : tdList) {
				tdTemp.setDesId(generateId(tdTemp.getDesId()));
				tdTemp.setDelState(1);
				tdTemp.setTaskId(taskLog.getTaskId());
				taskDescriptionDAO.insert(tdTemp);
				List<DesPicture> dpList = tdTemp.getDpList();
				if (dpList != null) {
					for (DesPicture dpTemp : dpList) {
						dpTemp.setPicId(generateId(dpTemp.getPicId()));
						dpTemp.setDelState(1);
						dpTemp.setDesId(tdTemp.getDesId());
						desPictureDAO.insert(dpTemp);
					}
				}
			}
		}
		updateLog(taskLog);
	}

	/**
	 * 删除任务生成日志并更新任务存在状态
	 * 
	 * @param task
	 */
	private void deleteTaskLog(Task task) {
		Task taskDelete = task;
		taskDelete.setCreateTime(task.getUpdateTime());
		taskDelete.setAssociatedTask(task.getTaskId());
		taskDelete.setTaskType(3);
		taskDelete.setTaskName("删除任务：" + task.getTaskName());
		taskDelete.setDelState(1);
		Task taskTemp = new Task();
		taskTemp.setAssociatedTask(task.getTaskId());
		taskTemp.setTaskType(2);
		Task taskTemp2 = taskDAO.get(taskTemp);
		taskTemp2.setUpdateName("已删除");
		taskTemp2.setUpdateTime(new Date());
		taskTemp2.setDelState(0);
		Integer taskState = taskTemp2.getUpdateState() == null ? taskTemp2.getTaskState() : taskTemp2.getUpdateState();
		taskTemp2.setUpdateState(taskState);
		taskDAO.update(taskTemp2, taskTemp2.getTaskId());
		insertLog(taskDelete);
	}

	/**
	 * 根据用户id和项目id查看我的笔记本
	 */
	@Override
	public List<Task> getMyNote(Integer uId, Integer pId) {
		Task task = new Task();
		task.setuId(uId);
		task.setpId(pId);
		List<Task> dbtaskList = taskDAO.getList(task);
		List<Task> taskList = new ArrayList<Task>();
		if (dbtaskList != null) {
			for (Task taskTemp : dbtaskList) {
				if (taskTemp.getTaskType() == 1) {
					continue;
				}
				Date updateTime = taskTemp.getUpdateTime();
				if (updateTime != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
					String fut = sdf.format(updateTime);
					if (taskTemp.getDelState() == 0) {
						if (taskTemp.getUpdateName() != null)
							taskTemp.setUpdateName(taskTemp.getUpdateName());
					} else {
						if (taskTemp.getUpdateName() != null)
							taskTemp.setUpdateName(fut + "/" + taskTemp.getUpdateName());
					}
				}
				TaskDescription tasDes = new TaskDescription();
				tasDes.setTaskId(taskTemp.getTaskId());
				tasDes.setDelState(1);
				List<TaskDescription> tdList = taskDescriptionDAO.getList(tasDes);
				if (tdList != null) {
					for (TaskDescription tdTemp : tdList) {
						DesPicture desPic = new DesPicture();
						desPic.setDesId(tdTemp.getDesId());
						List<DesPicture> dpList = desPictureDAO.getList(desPic);
						if (dpList != null) {
							tdTemp.setDpList(dpList);
						}
					}
				}
				taskTemp.setTdList(tdList);
				taskList.add(taskTemp);
			}
		}
		return taskList;
	}

	/**
	 * 招商笔记任务概况，数据看板
	 */
	@Override
	public Map<String, Object> getTasksOverview(Integer uId, Integer warehouseType, Integer taskTimeType,
			ArrayList<Date> taskTimes) {
		Map<String, Object> overview = new HashMap<String, Object>();
		Integer newCount = null;
		Integer doneCount = null;
		Integer undoneCount = null;
		Integer successCount = null;
		int sumNewCount = 0;
		int sumDoneCount = 0;
		int sumUndoneCount = 0;
		int sumSuccessCount = 0;
		if (taskTimeType == 1 || taskTimeType == 0) {
			if (taskTimes != null) {
				for (Date date : taskTimes) {
					newCount = taskDAO.getCount(uId, warehouseType, date, 1, null);
					doneCount = taskDAO.getCount(uId, warehouseType, date, 1, 1);
					undoneCount = taskDAO.getCount(uId, warehouseType, date, 1, 0);
					successCount = warehouseProjectsDAO.getSuccessCount(uId, warehouseType, date);
					sumNewCount += newCount;
					sumDoneCount += doneCount;
					sumUndoneCount += undoneCount;
					sumSuccessCount += successCount;
				}
			}
		} else {
			Date[] ttTemp = new Date[2];
			for (int i = 0; i < ttTemp.length; i++) {
				for (int j = 0; j < taskTimes.size(); j++) {
					ttTemp[j] = taskTimes.get(j);
				}
			}
			Date beginTime = ttTemp[0].compareTo(ttTemp[1]) < 0 ? ttTemp[0] : ttTemp[1];
			Date endTime = ttTemp[0].compareTo(ttTemp[1]) > 0 ? ttTemp[0] : ttTemp[1];
			sumNewCount = taskDAO.getCountBegEnd(uId, warehouseType, beginTime, endTime, 1, null);
			sumDoneCount = taskDAO.getCountBegEnd(uId, warehouseType, beginTime, endTime, 1, 1);
			sumUndoneCount = taskDAO.getCountBegEnd(uId, warehouseType, beginTime, endTime, 1, 0);
			sumSuccessCount = warehouseProjectsDAO.getSuccessCountBegEnd(uId, warehouseType, beginTime, endTime);
		}
		overview.put("sumNewCount", sumNewCount);
		overview.put("sumDoneCount", sumDoneCount);
		overview.put("sumUndoneCount", sumUndoneCount);
		overview.put("sumSuccessCount", sumSuccessCount);
		return overview;
	}

	/**
	 * 项目列表，任务数据展现
	 */
	@Override
	public List<Task> getTasksByPidTimes(Integer uId, Integer pId, Integer warehouseType, ArrayList<Date> dates) {
		Date[] ttTemp = new Date[2];
		for (int i = 0; i < ttTemp.length; i++) {
			for (int j = 0; j < dates.size(); j++) {
				ttTemp[j] = dates.get(j);
			}
		}
		Date beginDate = ttTemp[0].compareTo(ttTemp[1]) < 0 ? ttTemp[0] : ttTemp[1];
		Date endDate = ttTemp[0].compareTo(ttTemp[1]) > 0 ? ttTemp[0] : ttTemp[1];
		List<Task> tasks = taskDAO.getTasksByPidTimes(uId, pId, warehouseType, beginDate, endDate);
		return tasks;
	}

	/**
	 * 更改共享笔记状态
	 */
	@Override
	public Integer changeShareType(Integer uId, Integer pId, Integer shareType) {
		try {
			Task task = new Task();
			task.setpId(pId);
			task.setDelState(1);
			task.setShareType(shareType);
			taskDAO.updateTaskByPid(task);
		} catch (Exception e) {
			return null;
		}
		return 1;
	}

	/**
	 * 查看共享笔记
	 */
	@Override
	public List<Task> getShareType(Integer uId, Integer pId) {
		Task task = new Task();
		task.setpId(pId);
		task.setDelState(1);
		List<Task> taskdb = taskDAO.getList(task);
		if (taskdb != null) {
			for (Task taskTemp : taskdb) {
				if (taskTemp.getTaskType() == 1)
					continue;
			}
		}
		return taskdb;
	}
}
