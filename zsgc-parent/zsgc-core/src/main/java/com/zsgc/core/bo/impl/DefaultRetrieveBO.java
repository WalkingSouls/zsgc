package com.zsgc.core.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsgc.core.bo.RetrieveBO;
import com.zsgc.core.dao.ProjectDescriptionDAO;
import com.zsgc.core.dao.TaskDAO;
import com.zsgc.core.dao.TaskDescriptionDAO;
import com.zsgc.core.dao.WarehouseProjectDAO;
import com.zsgc.core.model.ProjectDescription;
import com.zsgc.core.model.TaskDescription;
import com.zsgc.core.model.TaskRecovery;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.PageBean;

@Service("retrieveBO")
public class DefaultRetrieveBO implements RetrieveBO {
	private final static int PROJECT_STATE_DEL = 0;

	private final static int PROJECT_STATE_EXIST = 1;

	@Autowired
	private WarehouseProjectDAO warehouseProjectDao;

	@Autowired
	private ProjectDescriptionDAO projectDescriptionDao;

	@Autowired
	private TaskDAO taskDAO;
	
	@Autowired
	private TaskDescriptionDAO taskDescriptionDAO;

	// 项目回收站
	@Override
	public PageBean<WarehouseProjects> ProjectsPage(int uId, int pageNum, int pageSize, Byte delCauseNum) {
		PageBean<WarehouseProjects> page = new PageBean<>(pageNum, pageSize);
		WarehouseProjects project = new WarehouseProjects();
		project.setuId(uId);
		project.setDelState(PROJECT_STATE_DEL);
		if (delCauseNum != null) {
			project.setDelCauseNum(delCauseNum);
		}
		page.setTotalRecord(warehouseProjectDao.selAllCountProjects(project));
		page.totalPage();
		PageBean<WarehouseProjects> selPage = warehouseProjectDao.selPage(project, page);
		List<WarehouseProjects> list = selPage.getList();
		list.forEach(warehouseProject -> {
			ProjectDescription pd = new ProjectDescription();
			pd.setpId(warehouseProject.getpId());
			pd.setDesState(PROJECT_STATE_EXIST);
			warehouseProject.getList().add(projectDescriptionDao.selOneByPid(pd));
		});
		return selPage;
	}

	// 任务回收站
	@Override
	public PageBean<TaskRecovery> TasksPage(int uId, int pageNum, int pageSize) {
		PageBean<TaskRecovery> page = new PageBean<>(pageNum, pageSize);
		page.setTotalRecord(taskDAO.selAllTaskRecovery(uId));
		page.totalPage();
		PageBean<TaskRecovery> selPage = taskDAO.selPage(uId, page);
		List<TaskRecovery> list = selPage.getList();
		for (TaskRecovery tr : list) {
			tr.setTaskDescription(taskDescriptionDAO.selByTaskId(tr.getTaskId()));
		}
		return selPage;
	}

}
