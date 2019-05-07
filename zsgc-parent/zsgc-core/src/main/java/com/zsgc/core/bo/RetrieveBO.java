package com.zsgc.core.bo;

import com.zsgc.core.model.TaskRecovery;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.PageBean;

public interface RetrieveBO {
	PageBean<WarehouseProjects> ProjectsPage(int uId ,int pageNum ,int pageSize,Byte delCauseNum);
	PageBean<TaskRecovery> TasksPage(int uId ,int pageNum ,int pageSize);
}
