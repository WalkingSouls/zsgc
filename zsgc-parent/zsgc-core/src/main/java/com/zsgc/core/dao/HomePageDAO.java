package com.zsgc.core.dao;

import java.util.List;

import com.zsgc.core.model.HomePageWarehouseProjects;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.ProjectsPageBean;

public interface HomePageDAO {
	List<HomePageWarehouseProjects> selByTop(WarehouseProjects w,ProjectsPageBean<HomePageWarehouseProjects> page);
	
	List<HomePageWarehouseProjects> selByOther(WarehouseProjects w,ProjectsPageBean<HomePageWarehouseProjects> page,String order);
	
	int selByTopAll(WarehouseProjects w);
	
	int selAllCountProjects(WarehouseProjects wp);

	int updateFirstTop(int pId,Byte firstTop);
}
