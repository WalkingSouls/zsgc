package com.zsgc.core.bo;

import java.util.List;

import com.calanger.common.bo.BO;
import com.zsgc.core.model.CustomerProfile;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.ProjectsPageBean;

public interface WarehouseProjectBO extends BO<WarehouseProjects, java.lang.Integer>{

	int changProfile(WarehouseProjects wp);
	
	int changeWarehouse(WarehouseProjects wp);
	
	ProjectsPageBean<WarehouseProjects> getPage(WarehouseProjects wp, int index, ProjectsPageBean<WarehouseProjects> page);
	
	List<CustomerProfile>  getCustomerProfile(WarehouseProjects wp);
	
	WarehouseProjects getProject(WarehouseProjects wp,int collection);
	
	int collectionProject(WarehouseProjects wp, int uid,int index);

}