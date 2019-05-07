package com.zsgc.core.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zsgc.core.dao.HomePageDAO;
import com.zsgc.core.model.HomePageWarehouseProjects;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.ProjectsPageBean;

@Repository("homePageDAO")
public class MyBatisHomePageDAO implements HomePageDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<HomePageWarehouseProjects> selByTop(WarehouseProjects w, ProjectsPageBean<HomePageWarehouseProjects> page) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bean", w);
		System.out.println("page.getPageNum()"+page.getPageNum());
		map.put("count", (page.getPageNum()-1)*page.getPageSize());
		map.put("PageSize",page.getPageSize());
		return sqlSessionTemplate.selectList("warehouseProjectsDAO.selH_ByTop",map);
	}

	@Override
	public List<HomePageWarehouseProjects> selByOther(WarehouseProjects w, ProjectsPageBean<HomePageWarehouseProjects> page,String order) {
		if (page.getIndex()-page.getPageSize()*(page.getPageNum())<0) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("bean", w);
			map.put("order", order);
			int k = page.getIndex()%page.getPageSize();
			int n = (page.getIndex()/page.getPageSize())+1;
			if (page.getPageNum()==n) {
				map.put("count",0);
				map.put("PageSize",page.getPageSize()-k);
			}else{
			map.put("count", ((page.getPageNum()-n) *page.getPageSize()-k));
			map.put("PageSize",page.getPageSize());
			}
			return sqlSessionTemplate.selectList("warehouseProjectsDAO.selH_ByOther",map);
		}
		return null;
	}

	@Override
	public int selByTopAll(WarehouseProjects wp) {
		return sqlSessionTemplate.selectOne("warehouseProjectsDAO.selH_ByTopAll", wp);
	}

	@Override
	public int selAllCountProjects(WarehouseProjects wp) {
		return sqlSessionTemplate.selectOne("warehouseProjectsDAO.sel_count",wp);
	}

	@Override
	public int updateFirstTop(int pId, Byte firstTop) {
		Map<String,Object> map = new HashMap<>();
		map.put("pId", pId);
		map.put("firstTop", firstTop);
		map.put("time", new Date());
		return sqlSessionTemplate.update("warehouseProjectsDAO.updateH_FirstTop",map);
	}

	
	
	
}
