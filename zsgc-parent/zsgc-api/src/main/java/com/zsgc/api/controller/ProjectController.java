package com.zsgc.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.zsgc.api.dto.UpdateCusProAccept;
import com.zsgc.core.bo.WarehouseProjectsBO;
import com.zsgc.core.model.CustomerProfile;
import com.zsgc.core.model.WarehouseProjects;

/**
 * 项目管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	private WarehouseProjectsBO warehouseProjectsBO;

	/**
	 * 发布新项目
	 * 
	 * @param warehouse
	 * @return
	 */
	@RequestMapping(value = "/createNewPro", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Map<String, Object>> createNewPro(@RequestBody WarehouseProjects warehouse) {
		List<CustomerProfile> listCP = warehouse.getListCP();
		if (listCP != null) {
			for (CustomerProfile cp : listCP) {
				if (warehouseProjectsBO.getByCusName(warehouse.getpId(), cp.getCustomerName()) >= 1) {
					return new ApiResult<Map<String, Object>>(3, "该姓名您已创建过，请试一下其他的");
				}
				List<CustomerProfile> list1 = cp.getList();
				if (list1 != null) {
					for (CustomerProfile CPTemp1 : list1) {
						if (warehouseProjectsBO.getByCusName(warehouse.getpId(), CPTemp1.getCustomerName()) >= 1) {
							return new ApiResult<Map<String, Object>>(3, "该姓名您已创建过，请试一下其他的");
						}
						List<CustomerProfile> list2 = CPTemp1.getList();
						if (list2 != null) {
							for (CustomerProfile CPTemp2 : list2) {
								if (warehouseProjectsBO.getByCusName(warehouse.getpId(),
										CPTemp2.getCustomerName()) >= 1) {
									return new ApiResult<Map<String, Object>>(3, "该姓名您已创建过，请试一下其他的");
								}
							}
						}
					}
				}
			}
		}
		if (warehouseProjectsBO.getByProName(warehouse) != null) {
			return new ApiResult<Map<String, Object>>(1, "该项目名称已存在");
		} else if (IsNullOrEmpty(warehouse)) {
			return new ApiResult<Map<String, Object>>(2, "您还有信息没有填写");
		} else {
			String msgTemp = warehouseProjectsBO.createNewPro(warehouse);
			String msg = msgTemp==null?"":msgTemp;
			return new ApiResult<Map<String, Object>>(0, "项目发布成功 "+msg);
		}
	}

	/**
	 * 再次判断必填数据是否为空
	 * 
	 * @param warehouse
	 * @return
	 */
	public Boolean IsNullOrEmpty(WarehouseProjects warehouse) {
		if ((warehouse.getProjectName() == null ^ warehouse.getIntentionalCity() == null
				^ warehouse.getIndustry() == null ^ warehouse.getCity() == null ^ warehouse.getWarehouseType() == null
				^ warehouse.getInvestmentAmount() == null)) {
			return true;
		}
		List<CustomerProfile> customerProfile = warehouse.getListCP();
		for (CustomerProfile customerTemp : customerProfile) {
			if (customerTemp.getCustomerName() == null ^ customerTemp.getTelephone() == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据项目id查看项目
	 * 
	 * @param pId
	 * @return
	 */
	@RequestMapping(value = "/getPro/{pId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<WarehouseProjects> getProByProId(@PathVariable(value = "pId") Integer pId) {
		WarehouseProjects warehouseProjects = warehouseProjectsBO.getProByProId(pId);
		if (warehouseProjects != null) {
			return new ApiResult<WarehouseProjects>(0, "查询成功", warehouseProjects);
		} else {
			return new ApiResult<WarehouseProjects>(1, "项目不存在或者已被删除", warehouseProjects);
		}
	}

	/**
	 * 更新项目
	 * 
	 * @param warehouse
	 * @return
	 */
	@RequestMapping(value = "/updatePro", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Map<String, Object>> updateProByProId(@RequestBody WarehouseProjects warehouse) {
		try {
			if (warehouseProjectsBO.getProByProId(warehouse.getpId()) == null) {
				return new ApiResult<Map<String, Object>>(1, "项目不存在或者已被删除");
			}
			warehouseProjectsBO.updateProByProId(warehouse);
		} catch (NullPointerException e) {
			return new ApiResult<Map<String, Object>>(1, "项目不存在或者已被删除");
		} catch (Exception e) {
			return new ApiResult<Map<String, Object>>(2, "项目修改失败");
		}
		return new ApiResult<Map<String, Object>>(0, "修改成功");
	}

	/**
	 * 查看所有客户图谱
	 * 
	 * @param uId
	 * @param pId
	 * @return
	 */
	@RequestMapping(value = "/getCusPro/{uId}/{pId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, ArrayList<CustomerProfile>>> getCusPro(@PathVariable Integer uId,
			@PathVariable Integer pId) {
		HashMap<String, ArrayList<CustomerProfile>> params = new HashMap<String, ArrayList<CustomerProfile>>();
		ArrayList<CustomerProfile> cusPro = warehouseProjectsBO.getAllCusPro(uId, pId);
		params.put("customerProfiles", cusPro);
		if (cusPro == null || cusPro.size() == 0) {
			return new ApiResult<Map<String, ArrayList<CustomerProfile>>>(1, "暂时还没有建联系人");
		}
		return new ApiResult<Map<String, ArrayList<CustomerProfile>>>(0, "查询成功", params);
	}
	/**
	 * 查看单个客户图谱
	 * @param pId
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value = "/getByCusId/{pId}/{customerId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, CustomerProfile>> getByCusId(@PathVariable Integer pId,
			@PathVariable Integer customerId) {
		HashMap<String, CustomerProfile> params = new HashMap<String, CustomerProfile>();
		CustomerProfile cusPro = warehouseProjectsBO.getByCusId(pId, customerId);
		if (cusPro == null) {
			return new ApiResult<Map<String, CustomerProfile>>(1, "该联系人不存在");
		}
		params.put("customerProfiles", cusPro);
		return new ApiResult<Map<String, CustomerProfile>>(0, "查看成功", params);
	}
	/**
	 * 新增或更新客户图谱
	 * @param cusPro
	 * @return
	 */
	@RequestMapping(value = "/changeCusPro", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Map<String, Object>> changeCusPro(@RequestBody UpdateCusProAccept cusPro) {
		Integer relatedId = cusPro.getRelatedId();
		CustomerProfile cusProTemp = cusPro.getCusPro();
		Integer relationType = cusPro.getRelationType();
		if (cusProTemp.getCustomerId() != null) {
			ApiResult<Map<String, Object>> updateCusPro = updateCusPro(cusProTemp, relatedId, relationType);
			return new ApiResult<Map<String, Object>>(updateCusPro.getCode(), updateCusPro.getMsg());
		} else {
			ApiResult<Map<String, Object>> createNewCP = createNewCP(cusProTemp, relatedId, relationType);
			return new ApiResult<Map<String, Object>>(createNewCP.getCode(), createNewCP.getMsg());
		}
	}
	/**
	 * 新增客户图谱
	 * @param cusPro
	 * @param relatedId
	 * @param relationType
	 * @return
	 */
	public ApiResult<Map<String, Object>> createNewCP(CustomerProfile cusPro, Integer relatedId,
			Integer relationType) {
		if (warehouseProjectsBO.getByCusName(cusPro.getpId(), cusPro.getCustomerName()) != null) {
			return new ApiResult<Map<String, Object>>(1, "该姓名您已创建过，请试一下其他的");
		}
		Integer rows = warehouseProjectsBO.createNewCP(cusPro,relatedId,relationType);
		if (rows == null) {
			return new ApiResult<Map<String, Object>>(2, "新建失败");
		}
		return new ApiResult<Map<String, Object>>(0, "新建成功");
	}
	/**
	 * 更新客户图谱
	 * @param cusPro
	 * @param relatedId
	 * @param relationType
	 * @return
	 */
	public ApiResult<Map<String, Object>> updateCusPro(CustomerProfile cusPro, Integer relatedId,
			Integer relationType) {
		if (warehouseProjectsBO.getByCusName(cusPro.getpId(), cusPro.getCustomerName()) > 1) {
			return new ApiResult<Map<String, Object>>(1, "该姓名您已创建过，请试一下其他的");
		}
		Integer rows = warehouseProjectsBO.updateCusPro(cusPro,relatedId,relationType);
		if (rows == null) {
			return new ApiResult<Map<String, Object>>(1, "更新失败");
		}
		return new ApiResult<Map<String, Object>>(0, "更新成功");
	}
	/**
	 * 根据id删除客户图谱
	 * @param pId
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value = "/deleteCusPro/{pId}/{customerId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String, Object>> deleteCusPro(@PathVariable Integer pId, @PathVariable Integer customerId) {
		Integer rows = warehouseProjectsBO.deleteCusPro(pId, customerId);
		if (rows == null) {
			return new ApiResult<Map<String, Object>>(1, "删除失败");
		}
		return new ApiResult<Map<String, Object>>(0, "删除成功");
	}
}
