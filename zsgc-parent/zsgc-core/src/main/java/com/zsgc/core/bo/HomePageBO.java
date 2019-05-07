package com.zsgc.core.bo;

import java.util.Date;

import com.zsgc.core.model.HomePageWarehouseProjects;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.ProjectsPageBean;

public interface HomePageBO {
	ProjectsPageBean<HomePageWarehouseProjects> getPage(WarehouseProjects wp, int index,int pageNum,int pageSize,Date timeStart,Date timeEnd);
	int updateFirstTop(int pId,Byte firstTop);
}
