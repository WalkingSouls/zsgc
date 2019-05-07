package com.zsgc.core.dao;

import java.util.List;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.PageBean;
import com.zsgc.core.utils.ProjectsPageBean;
public interface WarehouseProjectDAO extends DAO<WarehouseProjects,java.lang.Integer>{
	
	PageBean<WarehouseProjects> selPage(WarehouseProjects wp,PageBean<WarehouseProjects> page);
	
	int updateByPid(WarehouseProjects wp);
	
	List<WarehouseProjects> selByPids(List<WarehouseProjects> list);
	
	List<WarehouseProjects> selByTop(WarehouseProjects w,ProjectsPageBean<WarehouseProjects> page);
	
	List<WarehouseProjects> selByOther(WarehouseProjects w,ProjectsPageBean<WarehouseProjects> page);
	
	int selByTopAll(WarehouseProjects w);
	
	int selAllCountProjects(WarehouseProjects wp);
	
	int insertProject(WarehouseProjects wp);
}