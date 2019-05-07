package com.zsgc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsgc.api.dto.ApiResult;
import com.zsgc.core.bo.WarehouseProjectBO;
import com.zsgc.core.model.WarehouseProjects;

@Controller
@RequestMapping("/collection")
public class CollectionProjectController {
	@Autowired
	private WarehouseProjectBO warehouseProjectBO;
	
	//在项目集市中打开项目信息
	@RequestMapping(value = "/openProject/{pId}/{collection}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<WarehouseProjects> openProject(@PathVariable String pId, @PathVariable String collection) {
		if (pId.equals("") || pId == null || collection.equals("") || collection == null) {
			return new ApiResult<WarehouseProjects>(2, "参数错误");
		}
		WarehouseProjects wp = new WarehouseProjects();
		wp.setpId(Integer.parseInt(pId));
		WarehouseProjects data = warehouseProjectBO.getProject(wp, Integer.parseInt(collection));
		if (data == null) {
			return new ApiResult<WarehouseProjects>(1, "数据为null");
		}
		return new ApiResult<WarehouseProjects>(0, "success", data);
	}
	//采集项目
	@RequestMapping(value = "/inserProject/{uId}/{pId}/{index}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<String> inserProject(@PathVariable String uId,@PathVariable String pId,@PathVariable String index) {
		if (uId.equals("") || uId == null||pId.equals("") || pId == null||index.equals("") || index == null){
			return new ApiResult<String>(2, "参数错误");
		}
		WarehouseProjects wp = new WarehouseProjects();
		wp.setpId(Integer.parseInt(pId));
		wp.setDelState(1);
		int type = warehouseProjectBO.collectionProject(wp,Integer.parseInt(uId),Integer.parseInt(index));
		if (type==2) {
			return new ApiResult<String>(3, "积分不足");
		}else if(type==1){
			return new ApiResult<String>(0, "success");
		}else if (type==3) {
			return new ApiResult<String>(1, "已经采集过了");
		}
		return new ApiResult<String>(2, "更新失败");
	}
}