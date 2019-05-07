package com.zsgc.core.bo.impl;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.TaskBO;
import com.zsgc.core.bo.WarehouseProjectsBO;
import com.zsgc.core.dao.CustomerProfileDAO;
import com.zsgc.core.dao.ProjectDescriptionDAO;
import com.zsgc.core.dao.ProjectDescriptionPictureDAO;
import com.zsgc.core.dao.WarehouseProjectsDAO;
import com.zsgc.core.model.CustomerProfile;
import com.zsgc.core.model.ProjectDescription;
import com.zsgc.core.model.ProjectDescriptionPicture;
import com.zsgc.core.model.Task;
import com.zsgc.core.model.UcenterMember;
import com.zsgc.core.model.WarehouseProject;
import com.zsgc.core.model.WarehouseProjects;

/**
 * 项目信息及客户图谱业务类
 * 
 * @author lyd
 *
 */

@Service("warehouseProjectsBO")
public class DefaultWarehouseProjectsBO extends AbstractBO<WarehouseProjects, java.lang.Integer>
		implements WarehouseProjectsBO {
	@Autowired
	private WarehouseProjectsDAO warehouseProjectsDAO;
	@Autowired
	private ProjectDescriptionDAO projectDescriptionDAO;
	@Autowired
	private ProjectDescriptionPictureDAO projectDescriptionPictureDAO;
	@Autowired
	private CustomerProfileDAO customerProfileDAO;
	@Autowired
	private TaskBO taskBO;

//	@Autowired
//	private UcenterMemberTempDAO ucenterMemberTempDAO;
	@Override
	protected DAO<WarehouseProjects, Integer> getDAO() {
		return warehouseProjectsDAO;
	}

	/**
	 * 发布新项目
	 */
	@Override
	@Transactional
	public String createNewPro(WarehouseProjects warehouse) {
		String msg = null;
		warehouse.setpId(generateId(warehouse.getpId()));
		warehouse.setDelState(1);
		warehouse.setCreateP(new Date());
		warehouse.setUpdateP(new Date());
		warehouse.setScore(null);
		UcenterMember ucenterMember = new UcenterMember();
		ucenterMember.setId(warehouse.getuId());
		ucenterMember.setUpdatedAt(new Date());
//		Integer pro = ucenterMemberTempDAO.updatePro(ucenterMember);
//		if (pro == null || pro == 0) {
//			msg="发布项目获得积分已到今日上限";
//		}else {
//			msg="积分+10";
//		}
		warehouseProjectsDAO.insert(warehouse);
		List<ProjectDescription> proDes = warehouse.getList();
		if (proDes != null) {
			for (ProjectDescription proDesTemp : proDes) {
				proDesTemp.setDesId(generateId(proDesTemp.getDesId()));
				proDesTemp.setpId(warehouse.getpId());
				proDesTemp.setDesState(warehouse.getDelState());
				projectDescriptionDAO.insert(proDesTemp);

				List<ProjectDescriptionPicture> proDesPic = proDesTemp.getList();
				if (proDesPic != null) {
					for (ProjectDescriptionPicture proDesPicTemp : proDesPic) {
						proDesPicTemp.setDesPId(generateId(proDesPicTemp.getDesPId()));
						proDesPicTemp.setpState(warehouse.getDelState());
						proDesPicTemp.setDesId(proDesTemp.getDesId());
						projectDescriptionPictureDAO.insert(proDesPicTemp);
					}
				}
			}
		}
		List<CustomerProfile> customerProfile = warehouse.getListCP();
		if (customerProfile != null) {
			for (CustomerProfile cusProTemp : customerProfile) {
				cusProTemp.setParentId(0);
				insertCusPro(warehouse, cusProTemp);
				List<CustomerProfile> cpList = cusProTemp.getList();
				if (cpList != null) {
					for (CustomerProfile cusProTemp2 : cpList) {
						cusProTemp2.setParentId(cusProTemp.getCustomerId());
						insertCusPro(warehouse, cusProTemp2);
						List<CustomerProfile> cp = cusProTemp2.getList();
						if (cp != null) {
							for (CustomerProfile cusProTemp3 : cp) {
								cusProTemp3.setParentId(cusProTemp2.getCustomerId());
								insertCusPro(warehouse, cusProTemp3);
							}
						}
					}
				}
			}
		}
		taskBO.createNewProLog(warehouse);
		return msg;
	}

	/**
	 * 新建客户
	 * 
	 * @param warehouse
	 * @param cusPro
	 */
	private void insertCusPro(WarehouseProjects warehouse, CustomerProfile cusPro) {
		cusPro.setCustomerId(generateId(cusPro.getCustomerId()));
		cusPro.setpId(warehouse.getpId());
		cusPro.setCreateDate(new Date());
		cusPro.setUpdateDate(new Date());
		cusPro.setDelState(warehouse.getDelState());
		customerProfileDAO.insert(cusPro);
		taskBO.createNewCusLog(warehouse.getuId(), cusPro);
	}

	/**
	 * 根据项目id查看客户图谱
	 */
	@Override
	@Transactional
	public WarehouseProjects getProByProId(Integer pId) {
		WarehouseProjects warehouse = warehouseProjectsDAO.get(pId);
		if (warehouse != null) {
			List<ProjectDescription> proDesList = projectDescriptionDAO.getList(pId);
			warehouse.setList(proDesList);
			if (proDesList != null) {
				for (ProjectDescription proDes : proDesList) {
					List<ProjectDescriptionPicture> proDesPicList = projectDescriptionPictureDAO
							.getList(proDes.getDesId());
					proDes.setList(proDesPicList);
				}
			}
			setProCus(warehouse);
			return warehouse;
		} else {
			return null;
		}
	}

	/**
	 * 递归设置客户图谱
	 * 
	 * @param warehouse
	 * @return
	 */
	private WarehouseProjects setProCus(WarehouseProjects warehouse) {
		List<CustomerProfile> cusProParList = customerProfileDAO.findParentId(warehouse.getpId(), 0);
		List<CustomerProfile> cusProList = warehouse.getListCP();
		if (cusProParList != null) {
			for (CustomerProfile cusProSon : cusProParList) {
				List<CustomerProfile> cusProSonList = customerProfileDAO.findParentId(warehouse.getpId(),
						cusProSon.getCustomerId());
				getCustomerProfiles(cusProSonList, cusProSon);
				cusProList.add(cusProSon);
			}
		}
		warehouse.setListCP(cusProList);
		return warehouse;
	}

	/**
	 * 递归查看客户图谱操作方法
	 * 
	 * @param cusProSonList
	 * @param cusProSon
	 * @return
	 */
	private CustomerProfile getCustomerProfiles(List<CustomerProfile> cusProSonList, CustomerProfile cusProSon) {
		if (cusProSonList == null) {
			return null;
		} else {
			List<CustomerProfile> cpList = cusProSon.getList();
			for (CustomerProfile customerProfile : cusProSonList) {
				cpList.add(getCustomerProfiles(
						customerProfileDAO.findParentId(customerProfile.getpId(), customerProfile.getCustomerId()),
						customerProfile));
			}
			return cusProSon;
		}
	}

	/**
	 * 更新项目信息
	 */
	@Override
	@Transactional
	public Integer updateProByProId(WarehouseProjects warehouse) {
		WarehouseProjects dbwarehouse = warehouseProjectsDAO.get(warehouse.getpId());
		if (!dbwarehouse.getState().equals(warehouse.getState())) {
			taskBO.changeProStateLog(warehouse);
		}
		if (!dbwarehouse.getWarehouseType().equals(warehouse.getWarehouseType())) {
			taskBO.changeWarehouseLog(warehouse);
		}
		warehouseProjectsDAO.update(warehouse, warehouse.getpId());
		List<ProjectDescription> proDes = warehouse.getList();
		List<ProjectDescription> list = projectDescriptionDAO.getList(warehouse.getpId());
		compareDeleteProDes(list, proDes);
		if (proDes != null) {
			for (ProjectDescription proDesTemp : proDes) {
				if (proDesTemp.getDesId() == null) {
					proDesTemp.setDesId(generateId(proDesTemp.getDesId()));
					proDesTemp.setpId(warehouse.getpId());
					proDesTemp.setDesState(warehouse.getDelState());
					projectDescriptionDAO.insert(proDesTemp);
				} else {
					projectDescriptionDAO.update(proDesTemp, proDesTemp.getDesId());
					List<ProjectDescriptionPicture> proDesPic = proDesTemp.getList();
					if (proDesPic != null) {
						for (ProjectDescriptionPicture proDesPicTemp : proDesPic) {
							if (proDesPicTemp.getDesPId() == null) {
								proDesPicTemp.setDesId(proDesTemp.getDesId());
								proDesPicTemp.setDesPId(generateId(proDesPicTemp.getDesPId()));
								proDesPicTemp.setpState(warehouse.getDelState());
								projectDescriptionPictureDAO.insert(proDesPicTemp);
							}
							projectDescriptionPictureDAO.update(proDesPicTemp, proDesPicTemp.getDesPId());
						}
					}
				}
			}
		}
		List<CustomerProfile> customerProfile = warehouse.getListCP();
		compareUpdateCusPro(warehouse, customerProfile);
		return 1;
	}

	/**
	 * 比较客户图谱的变化
	 * 
	 * @param warehouse
	 * @param customerProfile
	 */
	private void compareUpdateCusPro(WarehouseProjects warehouse, List<CustomerProfile> customerProfile) {
		List<Integer> dbidsList = customerProfileDAO.getIdsList(warehouse.getpId());
		List<Integer> newIdsList = new ArrayList<Integer>();
		if (customerProfile != null) {
			for (CustomerProfile cusPro : customerProfile) {
				insertOrUpdateCP(cusPro, warehouse);
				newIdsList.add(cusPro.getCustomerId());
				List<CustomerProfile> cpList1 = cusPro.getList();
				if (cpList1 != null) {
					for (CustomerProfile cusPro1 : cpList1) {
						insertOrUpdateCP(cusPro1, warehouse);
						newIdsList.add(cusPro1.getCustomerId());
						List<CustomerProfile> cpList2 = cusPro1.getList();
						if (cpList2 != null) {
							for (CustomerProfile cusPro2 : cpList2) {
								insertOrUpdateCP(cusPro2, warehouse);
								newIdsList.add(cusPro2.getCustomerId());
							}
						}
					}
				}
			}
			if (dbidsList != null) {
				for (Integer bdid : dbidsList) {
					if (!newIdsList.contains(bdid)) {
						CustomerProfile customerPro = customerProfileDAO.getByCusId(bdid);
						customerPro.setDelState(0);
						customerPro.setUpdateDate(new Date());
						customerProfileDAO.update(customerPro, customerPro.getCustomerId());
						taskBO.deleteCusLog(warehouse.getuId(), customerPro);
					}
				}
			}
		}
	}

	/**
	 * 比较插入或者删除客户图谱
	 * 
	 * @param cp
	 * @param warehouse
	 */
	private void insertOrUpdateCP(CustomerProfile cp, WarehouseProjects warehouse) {
		if (cp.getCustomerId() == null) {
			cp.setCustomerId(generateId(cp.getCustomerId()));
			cp.setpId(warehouse.getpId());
			cp.setCreateDate(new Date());
			cp.setUpdateDate(new Date());
			cp.setDelState(warehouse.getDelState());
			customerProfileDAO.insert(cp);
			taskBO.createNewCusLog(warehouse.getuId(), cp);
		} else {
			customerProfileDAO.update(cp, cp.getCustomerId());
		}
	}

	/**
	 * 获取id集合
	 * 
	 * @param list
	 * @return
	 */
	private List<Integer> getPDIds(List<ProjectDescription> list) {
		List<Integer> idsList = new ArrayList<Integer>();
		if (list != null) {
			for (ProjectDescription pd : list) {
				idsList.add(pd.getDesId());
			}
		}
		return idsList;
	}

	/**
	 * 比较项目描述
	 * 
	 * @param list
	 * @param proDes
	 */
	private void compareDeleteProDes(List<ProjectDescription> list, List<ProjectDescription> proDes) {
		if (list != null) {
			List<Integer> idsList = getPDIds(list);
			List<Integer> newIdsList = getPDIds(proDes);
			for (Integer id : idsList) {
				if (!newIdsList.contains(id)) {
					ProjectDescription projectDescription = projectDescriptionDAO.get(id);
					projectDescription.setDesState(0);
					projectDescriptionDAO.update(projectDescription, projectDescription.getDesId());
					List<ProjectDescriptionPicture> ProDesPicList = projectDescriptionPictureDAO.getList(id);
					if (ProDesPicList != null) {
						for (ProjectDescriptionPicture proDesPic : ProDesPicList) {
							proDesPic.setpState(0);
							projectDescriptionPictureDAO.update(proDesPic, proDesPic.getDesPId());
						}
					}
				}

			}
		}
	}

	/**
	 * 查看项目是否重名
	 */
	@Override
	public WarehouseProjects getByProName(WarehouseProjects wareHouse) {
		return warehouseProjectsDAO.get(wareHouse);
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
	 * 查看所有客户图谱
	 */
	@Override
	public ArrayList<CustomerProfile> getAllCusPro(Integer uId, Integer pId) {
		WarehouseProjects warehouse = new WarehouseProjects();
		warehouse.setuId(uId);
		warehouse.setpId(pId);
		warehouse.setDelState(1);
		WarehouseProjects wh = setProCus(warehouse);
		List<CustomerProfile> listCP = wh.getListCP();
		return (ArrayList<CustomerProfile>) listCP;
	}

	/**
	 * 新建客户
	 */
	@Override
	public Integer createNewCP(CustomerProfile cusPro, Integer relatedId, Integer relationType) {
		if (relationType == -1) {
			cusPro.setCustomerId(generateId(cusPro.getCustomerId()));
			cusPro.setDelState(1);
			cusPro.setCreateDate(new Date());
			cusPro.setParentId(0);
			customerProfileDAO.insert(cusPro);
			WarehouseProjects warehouseProjects = warehouseProjectsDAO.get(cusPro.getpId());
			taskBO.createNewCusLog(warehouseProjects.getuId(), cusPro);
			return 1;
		}
		CustomerProfile customerProfile = customerProfileDAO.getByCusId(relatedId);
		if (customerProfile == null) {
			return null;
		}
		if (relationType == 0 || relationType == 1) {
			if (relationType == 0) {
				Integer parentId = customerProfile.getParentId();
				cusPro.setParentId(parentId);
			} else {
				cusPro.setParentId(relatedId);
			}
			cusPro.setCustomerId(generateId(cusPro.getCustomerId()));
			cusPro.setDelState(1);
			cusPro.setCreateDate(new Date());
			customerProfileDAO.insert(cusPro);
			WarehouseProjects warehouseProjects = warehouseProjectsDAO.get(cusPro.getpId());
			taskBO.createNewCusLog(warehouseProjects.getuId(), cusPro);
		} else {
			cusPro.setCustomerId(generateId(cusPro.getCustomerId()));
			cusPro.setDelState(1);
			cusPro.setCreateDate(new Date());
			cusPro.setParentId(0);
			customerProfileDAO.insert(cusPro);
			WarehouseProjects warehouseProjects = warehouseProjectsDAO.get(cusPro.getpId());
			taskBO.createNewCusLog(warehouseProjects.getuId(), cusPro);
			customerProfile.setParentId(cusPro.getCustomerId());
			updateCusPro(customerProfile, null, null);
		}
		return 1;
	}

	/**
	 * 更新客户图谱
	 */
	@Override
	public Integer updateCusPro(CustomerProfile cusPro, Integer relatedId, Integer relationType) {
		Integer rows;
		if (relatedId == null || relatedId == -1 || relatedId == 0) {
			cusPro.setUpdateDate(new Date());
			rows = customerProfileDAO.update(cusPro, cusPro.getCustomerId());
			if (cusPro.getDelState() == 1) {
				WarehouseProjects warehouseProjects = warehouseProjectsDAO.get(cusPro.getpId());
				taskBO.updateCusLog(warehouseProjects.getuId(), cusPro);
			} else {
				WarehouseProjects warehouseProjects = warehouseProjectsDAO.get(cusPro.getpId());
				taskBO.deleteCusLog(warehouseProjects.getuId(), cusPro);
			}
		} else {
			if (relationType == 0) {
				CustomerProfile cusTemp = customerProfileDAO.getByCusId(relatedId);
				Integer parentId = cusTemp.getParentId();
				cusPro.setParentId(parentId);
				rows = customerProfileDAO.update(cusPro, cusPro.getCustomerId());
				WarehouseProjects warehouseProjects = warehouseProjectsDAO.get(cusPro.getpId());
				taskBO.updateCusLog(warehouseProjects.getuId(), cusPro);
			} else if (relationType == 1) {
				cusPro.setParentId(relatedId);
				rows = customerProfileDAO.update(cusPro, cusPro.getCustomerId());
				WarehouseProjects warehouseProjects = warehouseProjectsDAO.get(cusPro.getpId());
				taskBO.updateCusLog(warehouseProjects.getuId(), cusPro);
			} else {
				CustomerProfile cusTemp = customerProfileDAO.getByCusId(relatedId);
				cusTemp.setParentId(cusPro.getCustomerId());
				rows = customerProfileDAO.update(cusTemp, cusTemp.getCustomerId());
				customerProfileDAO.update(cusPro, cusPro.getCustomerId());
				WarehouseProjects warehouseProjects = warehouseProjectsDAO.get(cusPro.getpId());
				taskBO.updateCusLog(warehouseProjects.getuId(), cusPro);
				taskBO.updateCusLog(warehouseProjects.getuId(), cusTemp);
			}
		}
		return rows;
	}

	/**
	 * 删除客户图谱
	 */
	@Override
	public Integer deleteCusPro(Integer pId, Integer customerId) {
		Integer rows;
		try {
			CustomerProfile cusPro = customerProfileDAO.getByCusId(customerId);
			cusPro.setUpdateDate(new Date());
			cusPro.setDelState(0);
			rows = updateCusPro(cusPro, null, null);
			List<CustomerProfile> list = cusPro.getList();
			if (list != null) {
				for (CustomerProfile cusProTemp : list) {
					cusProTemp.setDelState(0);
					updateCusPro(cusProTemp, null, null);
					List<CustomerProfile> list2 = cusProTemp.getList();
					if (list2 != null) {
						for (CustomerProfile cusProTemp2 : list2) {
							cusProTemp2.setDelState(0);
							updateCusPro(cusProTemp2, null, null);
						}
					}
				}
			}
		} catch (Exception e) {
			return 0;
		}
		return rows;
	}

	/**
	 * 查看客户谱图是否重名
	 */
	@Override
	public Integer getByCusName(Integer pId, String name) {
		CustomerProfile cusPro = new CustomerProfile();
		cusPro.setCustomerName(name);
		cusPro.setpId(pId);
		cusPro.setDelState(1);
		Integer count = customerProfileDAO.count(cusPro);
		return count;
	}

	/**
	 * 查看单个客户图谱
	 */
	@Override
	public CustomerProfile getByCusId(Integer pId, Integer customerId) {
		CustomerProfile cusPro = customerProfileDAO.getByCusId(customerId);
		return cusPro;
	}
}
