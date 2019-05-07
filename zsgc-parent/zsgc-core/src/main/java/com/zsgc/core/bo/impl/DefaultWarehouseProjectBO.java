package com.zsgc.core.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.MsgBO;
import com.zsgc.core.bo.WarehouseProjectBO;
import com.zsgc.core.dao.CustomerProfileDAO;
import com.zsgc.core.dao.ProjectDescriptionDAO;
import com.zsgc.core.dao.ProjectDescriptionPictureDAO;
import com.zsgc.core.dao.UcenterMemberDAO;
import com.zsgc.core.dao.UserExtendDAO;
import com.zsgc.core.dao.WarehouseProjectDAO;
import com.zsgc.core.model.CustomerProfile;
import com.zsgc.core.model.ProjectDescription;
import com.zsgc.core.model.ProjectDescriptionPicture;
import com.zsgc.core.model.UcenterMember;
import com.zsgc.core.model.UserExtend;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.ProjectsPageBean;

@Service("warehouseProjectBO")
public class DefaultWarehouseProjectBO extends AbstractBO<WarehouseProjects, java.lang.Integer>
		implements WarehouseProjectBO {
	@Autowired
	private WarehouseProjectDAO warehouseProjectDao;

	@Autowired
	private ProjectDescriptionDAO projectDescriptionDao;

	@Autowired
	private ProjectDescriptionPictureDAO projectDescriptionPictureDao;

	@Autowired
	private CustomerProfileDAO customerProfileDao;

	@Autowired
	private UcenterMemberDAO ucenterMemberDAO;

	@Autowired
	private UserExtendDAO userExtendDAO;

	@Autowired
	private MsgBO msgBO;

	@Autowired
	private DefaultTaskBO taskBO;
	
	@SuppressWarnings("unused")
	private final static int PROJECT_STATE_DEL = 0;

	private final static int PROJECT_STATE_EXIST = 1;

	@Override
	protected DAO<WarehouseProjects, Integer> getDAO() {
		return warehouseProjectDao;
	}

	/* 编辑项目删除恢复 */
	@Override
	@Transactional
	public int changProfile(WarehouseProjects wp) {
		int index = 0;
		int update_index = 0;
		int state = wp.getDelState();
		String delCause = wp.getDelCause();
		int type = wp.getWarehouseType();
		// int pId = wp.getpId();
		try {
			wp.setUpdateP(new Date());
			update_index += warehouseProjectDao.updateByPid(wp);
			index++;
			List<WarehouseProjects> list = new ArrayList<>();
			list.add(wp);
			WarehouseProjects project = warehouseProjectDao.selByPids(list).get(0);
			if (state == 0 && type == 1) {
				msgBO.delProjectsMsg(project, delCause,userExtendDAO.selAllByPid(wp.getpId()));
				SetupCollection(project.getpId());
			}
			if (type == 2 && state == 0) {
				UserExtend ue1 = new UserExtend();
				ue1.setNowPId(project.getpId());
				userExtendDAO.upDatePR(ue1);
			}
			if (state==0) {
				taskBO.deleteProLog(project);
			}else {
				taskBO.regainProLog(project);
			}
			
			/*
			 * ProjectDescription pd = new ProjectDescription(); pd.setpId(pId);
			 * if (state == 0) { pd.setDesState(1); } else { pd.setDesState(0);
			 * } List<ProjectDescription> list_projectDescription =
			 * projectDescriptionDao.selByPid(pd); index +=
			 * list_projectDescription.size(); for (ProjectDescription
			 * projectDescription : list_projectDescription) {
			 * projectDescription.setDesState(state); update_index +=
			 * projectDescriptionDao.updateById(projectDescription);
			 * ProjectDescriptionPicture pdp = new ProjectDescriptionPicture();
			 * pdp.setDesId(projectDescription.getDesId()); if (state == 0) {
			 * pdp.setpState(1); } else { pdp.setpState(0); }
			 * List<ProjectDescriptionPicture> list_pdp =
			 * projectDescriptionPictureDao.selByDesId(pdp); index +=
			 * list_pdp.size(); for (ProjectDescriptionPicture
			 * projectDescriptionPicture : list_pdp) {
			 * projectDescriptionPicture.setpState(state); int
			 * count_projectDescriptionPicture = projectDescriptionPictureDao
			 * .updateById(projectDescriptionPicture); if
			 * (count_projectDescriptionPicture == 1) { update_index++; } } }
			 * CustomerProfile cp = new CustomerProfile(); cp.setpId(pId); if
			 * (state == 0) { cp.setDelState(1); } else { cp.setDelState(0); }
			 * 
			 * List<CustomerProfile> selByPidAll =
			 * customerProfileDao.SelByPidAll(cp); index += selByPidAll.size();
			 * 
			 * for (CustomerProfile customerProfile : selByPidAll) {
			 * customerProfile.setDelState(state); int updateById =
			 * customerProfileDao.updateById(customerProfile); if (updateById ==
			 * 1) { update_index++; } }
			 */
			
		} catch (Exception e) {
			index = -1;
		}
		if (index == update_index) {
			return 1;
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}

	/* 编辑仓库状态 */
	@Override
	@Transactional
	public int changeWarehouse(WarehouseProjects wp) {
		int index = 0;
		try {
			index = warehouseProjectDao.updateByPid(wp);
			if (wp.getWarehouseType() != null) {
				List<WarehouseProjects> list = new ArrayList<>();
				list.add(wp);
				WarehouseProjects project = warehouseProjectDao.selByPids(list).get(0);
				taskBO.changeWarehouseLog(project);
				if (wp.getWarehouseType() == 0) {
					msgBO.changeProjectsMsg(project,userExtendDAO.selAllByPid(wp.getpId()));
					SetupCollection(wp.getpId());
				}
			}
			
			if (wp.getState()!=null) {
				List<WarehouseProjects> list = new ArrayList<>();
				list.add(wp);
				WarehouseProjects project = warehouseProjectDao.selByPids(list).get(0);
				taskBO.changeProStateLog(project);
			}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			index = -1;
		}
		if (index == 1) {
			return 1;
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}

	/* 获取仓库 */
	@Override
	public ProjectsPageBean<WarehouseProjects> getPage(WarehouseProjects wp, int index,
			ProjectsPageBean<WarehouseProjects> page) {
		page.setTotalRecord(warehouseProjectDao.selAllCountProjects(wp));
		page.totalPage();
		page.setIndex(warehouseProjectDao.selByTopAll(wp));
		List<WarehouseProjects> top_list = warehouseProjectDao.selByTop(wp, page);
		List<WarehouseProjects> other_list = warehouseProjectDao.selByOther(wp, page);
		if (top_list != null) {
			if (other_list != null) {
				other_list.forEach(project -> top_list.add(project));
			}
			page.setList(top_list);
		} else {
			page.setList(other_list);
		}
		List<WarehouseProjects> list = page.getList();
		int collectionTotal = 0;
		for (WarehouseProjects project : list) {
			if (index != 0) {
				getCustomerProfile(project, index);
			}
			ProjectDescription pd = new ProjectDescription();
			pd.setpId(project.getpId());
			pd.setDesState(PROJECT_STATE_EXIST);
			ProjectDescription selOneByPid = projectDescriptionDao.selOneByPid(pd);
			if (selOneByPid!=null) {
				project.getList().add(selOneByPid);
			}
			if (project.getCollectionNum() != null) {
				collectionTotal += project.getCollectionNum();
			}
			if (wp.getWarehouseType() != 0) {
				UserExtend ue = new UserExtend();
				ue.setNowPId(project.getpId());
				UserExtend selByNowPId = userExtendDAO.selByNowPId(ue);
				if (selByNowPId != null && selByNowPId.getIsScore() != null) {
					project.setCommentState(selByNowPId.getIsScore());
					project.setCommentContent(selByNowPId.getContent());
				} else {
					project.setCommentState(0);
				}
			}
		}
		page.setCollectionTotal(collectionTotal);
		return page;
	}

	private void getCustomerProfile(WarehouseProjects warehouseProjects, int index) {
		List<CustomerProfile> listCP = warehouseProjects.getListCP();
		CustomerProfile cp = new CustomerProfile();
		cp.setpId(warehouseProjects.getpId());
		cp.setDelState(warehouseProjects.getDelState());
		List<CustomerProfile> selByPidAll = customerProfileDao.SelByPidAll(cp);
		int num = 0;
		Iterator<CustomerProfile> iter = selByPidAll.iterator();
		while (iter.hasNext()) {
			CustomerProfile customerProfile = iter.next();
			if (customerProfile.getParentId() == 0) {
				listCP.add(customerProfile);
				iter.remove();
				num++;
				if (num == index) {
					return;
				}
			}
		}
		for (CustomerProfile customerProfile : selByPidAll) {
			num++;
			listCP.add(customerProfile);
			if (num == index) {
				return;
			}
		}
	}

	/* 获取客户图谱 */
	@Override
	public List<CustomerProfile> getCustomerProfile(WarehouseProjects wp) {
		CustomerProfile cp = new CustomerProfile();
		cp.setpId(wp.getpId());
		if (wp.getDelState() != 0) {
			cp.setDelState(wp.getDelState());
		}
		List<CustomerProfile> selByPidAll = customerProfileDao.SelByPidAll(cp);
		List<CustomerProfile> parent_list = new ArrayList<>();
		Iterator<CustomerProfile> iter = selByPidAll.iterator();
		while (iter.hasNext()) {
			CustomerProfile customerProfile = iter.next();
			if (customerProfile.getParentId() == 0) {
				parent_list.add(customerProfile);
				iter.remove();
			}
		}
		for (CustomerProfile customerProfile : parent_list) {
			getAllC(customerProfile, selByPidAll);
		}
		return parent_list;
	}

	private void getAllC(CustomerProfile customerProfile, List<CustomerProfile> all_list) {
		List<CustomerProfile> selByAllC = new ArrayList<>();
		Iterator<CustomerProfile> iter = all_list.iterator();
		while (iter.hasNext()) {
			CustomerProfile cp = iter.next();
			if (cp.getParentId().intValue() == customerProfile.getCustomerId().intValue()) {
				selByAllC.add(cp);
				iter.remove();
			}
		}
		if (selByAllC.size() == 0) {
			return;
		}
		List<CustomerProfile> list = customerProfile.getList();
		for (CustomerProfile customerProfileC : selByAllC) {
			getAllC(customerProfileC, all_list);
			list.add(customerProfileC);
		}
	}

	/* 项目集市查看项目 */
	@Override
	public WarehouseProjects getProject(WarehouseProjects wp, int collection) {
		List<WarehouseProjects> list = new ArrayList<>();
		list.add(wp);
		WarehouseProjects project = warehouseProjectDao.selByPids(list).get(0);
		List<ProjectDescription> project_list = project.getList();
		ProjectDescription pd = new ProjectDescription();
		pd.setpId(project.getpId());
		pd.setDesState(wp.getDelState());
		List<ProjectDescription> list_projectDescription = projectDescriptionDao.selByPid(pd);
		for (ProjectDescription projectDescription : list_projectDescription) {
			List<ProjectDescriptionPicture> list2 = projectDescription.getList();
			ProjectDescriptionPicture pdp = new ProjectDescriptionPicture();
			pdp.setDesId(projectDescription.getDesId());
			pdp.setpState(wp.getDelState());
			List<ProjectDescriptionPicture> list_pdp = projectDescriptionPictureDao.selByDesId(pdp);
			for (ProjectDescriptionPicture projectDescriptionPicture : list_pdp) {
				list2.add(projectDescriptionPicture);
			}
			project_list.add(projectDescription);
		}
		if (collection == 0) {
			return project;
		}
		wp.setDelState(1);
		project.setListCP(getCustomerProfile(project));

		return project;
	}

	/* 项目采集 */
	@Override
	@Transactional
	public int collectionProject(WarehouseProjects wp, int uid, int index) {
		UserExtend falg = null;
		UserExtend ue1 = new UserExtend();
		ue1.setpId(wp.getpId());
		ue1.setuId(uid);
		UserExtend userExtend = userExtendDAO.selByUidAndPid(ue1);
		if (userExtend != null) {
			falg = userExtend;
			if (userExtend.getIsCollection() != null && userExtend.getIsCollection() == 1) {
				return 3;
			}
		}
		UcenterMember ucenterMember = ucenterMemberDAO.get(uid);
		Integer score = ucenterMember.getScore();
		if (index > score) {
			return 2;
		}
		WarehouseProjects project = getProject(wp, 1);
		Integer old_uId = project.getuId();
		wp.setpId(project.getpId());
		Integer collectionNum = project.getCollectionNum();
		if (collectionNum == null) {
			wp.setCollectionNum(1);
		} else {
			wp.setCollectionNum(++collectionNum);
		}
		List<CustomerProfile> list = project.getListCP();
		project.setListCP(list);
		project.setuId(uid);
		project.setpId(null);
		Date date = new Date();
		project.setCreateP(date);
		project.setUpdateP(date);
		project.setWarehouseType(2);// 以後改
		project.setState(0);
		project.setDelCause(null);
		project.setScore(null);
		project.setTop(0);
		project.setTopTime(null);
		project.setCollectionNum(null);
		project.setCommentNum(null);
		int k = 0;
		try {
			insertProject(project);
			if (falg == null) {
				UserExtend ue = new UserExtend();
				ue.setuId(uid);
				ue.setpId(wp.getpId());
				ue.setIsCollection(1);
				ue.setNowPId(project.getpId());
				k += userExtendDAO.insertByCollection(ue);
			} else {
				falg.setIsCollection(1);
				k += userExtendDAO.updateByPrimaryKey(falg);
			}
			k += warehouseProjectDao.updateByPid(wp);
			UcenterMember um = new UcenterMember();
			um.setScore(score - index, true);
			k += ucenterMemberDAO.update(um, uid);
			msgBO.collectionProjectsMsg(project, old_uId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			k = -1;
		}
		if (k == 3) {
			return 1;
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}

	private void insertProject(WarehouseProjects wp) {
		warehouseProjectDao.insertProject(wp);
		List<ProjectDescription> list = wp.getList();
		List<ProjectDescriptionPicture> pdp_list = new ArrayList<>();
		for (ProjectDescription projectDescription : list) {
			projectDescription.setDesId(null);
			projectDescription.setpId(wp.getpId());
			projectDescriptionDao.insertPD(projectDescription);
			List<ProjectDescriptionPicture> list2 = projectDescription.getList();
			for (ProjectDescriptionPicture projectDescriptionPicture : list2) {
				projectDescriptionPicture.setDesId(projectDescription.getDesId());
				projectDescriptionPicture.setDesPId(null);
				pdp_list.add(projectDescriptionPicture);
			}
		}
		if (pdp_list.size()>0) {
			projectDescriptionPictureDao.insertMore(pdp_list);
		}
		insertCustomerProfile(wp.getListCP(), wp.getpId(), 0);
	}

	private void insertCustomerProfile(List<CustomerProfile> list, int pid, int id) {

		if (list == null) {
			return;
		}
		for (CustomerProfile customerProfile : list) {
			customerProfile.setCustomerId(null);
			customerProfile.setpId(pid);
			Date date = new Date();
			customerProfile.setCreateDate(date);
			customerProfile.setUpdateDate(date);
			customerProfile.setParentId(id);
			customerProfileDao.insertCP(customerProfile);

			insertCustomerProfile(customerProfile.getList(), pid, customerProfile.getCustomerId());
		}
	}

	// 设置采集
	private void SetupCollection(int pId) {
		UserExtend ue1 = new UserExtend();
		ue1.setpId(pId);
		ue1.setIsCollection(null);
		userExtendDAO.upDateMarketPR(ue1);
	}
}
