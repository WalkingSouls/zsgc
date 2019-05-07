package com.zsgc.core.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zsgc.core.bo.HomePageBO;
import com.zsgc.core.dao.HomePageDAO;
import com.zsgc.core.model.HomePageWarehouseProjects;
import com.zsgc.core.model.Task;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.ProjectsPageBean;

@Service("homePageBO")
public class DefaultHomePageBO implements HomePageBO {
	@Autowired
	private HomePageDAO homePageDAO;
	
	@Autowired
	private DefaultTaskBO taskBO;
	@Override
	public ProjectsPageBean<HomePageWarehouseProjects> getPage(WarehouseProjects wp, int index , int pageNum, int pageSize,Date timeStart,Date timeEnd) {
		ProjectsPageBean<HomePageWarehouseProjects> page = new ProjectsPageBean<>(pageNum, pageSize);
		page.setTotalRecord(homePageDAO.selAllCountProjects(wp));
		page.totalPage();
		page.setIndex(homePageDAO.selByTopAll(wp));
		List<HomePageWarehouseProjects> top_list = homePageDAO.selByTop(wp, page);
		List<HomePageWarehouseProjects> other_list = null;
		if (index==1) {
			other_list = homePageDAO.selByOther(wp, page,"update_p");
		}else{
			other_list  = homePageDAO.selByOther(wp, page,"create_p");
		}
			
		if (top_list != null) {
			if (other_list != null) {
				other_list.forEach(project -> top_list.add(project));
			}
			page.setList(top_list);
		} else {
			page.setList(other_list);
		}
		ArrayList<Date> dates = new ArrayList<>();
		dates.add(timeStart);
		dates.add(timeEnd);
		for (HomePageWarehouseProjects hwp : page.getList()) {
			List<Task> tasksByPidTimes = taskBO.getTasksByPidTimes(hwp.getuId(), hwp.getpId(), wp.getWarehouseType(), dates);
			if (tasksByPidTimes!=null) {
				hwp.setList(tasksByPidTimes);
			}
		}
		return page;
	}

	@Override
	@Transactional
	public int updateFirstTop(int pId, Byte firstTop) {
		int index = 0;
		try {
			index = homePageDAO.updateFirstTop(pId,firstTop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			index = -1;
		}
		if (index==1) {
			return 1;
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();	
		return 0;
	}

}
