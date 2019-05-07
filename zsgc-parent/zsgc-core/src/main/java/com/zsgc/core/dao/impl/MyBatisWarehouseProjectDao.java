package com.zsgc.core.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.WarehouseProjectDAO;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.PageBean;
import com.zsgc.core.utils.ProjectsPageBean;

@Repository("warehouseProjectDAO")
public class MyBatisWarehouseProjectDao extends AbstractDAO<WarehouseProjects, java.lang.Integer> implements WarehouseProjectDAO{
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
	@Override
	protected String getNamespace() {
		// TODO Auto-generated method stub
		return "warehouseProjectDAO";
	}

	@Override
	protected SqlSessionTemplate getSqlSessionTemplate() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate;
	}

	@Override
	public int updateByPid(WarehouseProjects wp) {
		return sqlSessionTemplate.update("warehouseProjectsDAO.updateByPrimaryKeySelective", wp);
	}
	
	@Override
	public PageBean<WarehouseProjects> selPage(WarehouseProjects wp, PageBean<WarehouseProjects> page) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bean", wp);
		map.put("count", (page.getPageNum()-1)*page.getPageSize());
		map.put("PageSize",page.getPageSize());
		List<WarehouseProjects> list = sqlSessionTemplate.selectList("warehouseProjectsDAO.selByBean", map);
		page.setList(list);
		return page;
	}

	@Override
	public List<WarehouseProjects> selByPids(List<WarehouseProjects> projects) {
		if (projects!=null) {
			return sqlSessionTemplate.selectList("warehouseProjectsDAO.selByPids", projects);
		}
		return null;
	}

	@Override
	public List<WarehouseProjects> selByTop(WarehouseProjects w,ProjectsPageBean<WarehouseProjects> page) {
		w.setTop(1);
		HashMap<String, Object> map = new HashMap<>();
		map.put("bean", w);
		map.put("count", (page.getPageNum()-1)*page.getPageSize());
		map.put("PageSize",page.getPageSize());
		return sqlSessionTemplate.selectList("warehouseProjectsDAO.selByTop",map);
	}

	@Override
	public List<WarehouseProjects> selByOther(WarehouseProjects w, ProjectsPageBean<WarehouseProjects> page) {
		w.setTop(0);
		if (page.getIndex()-page.getPageSize()*(page.getPageNum())<0) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("bean", w);
			int k = page.getIndex()%page.getPageSize();
			int n = (page.getIndex()/page.getPageSize())+1;
			if (page.getPageNum()==n) {
				map.put("count",0);
				map.put("PageSize",page.getPageSize()-k);
			}else{
			map.put("count", ((page.getPageNum()-n) *page.getPageSize()-k));
			map.put("PageSize",page.getPageSize());
			}
			return sqlSessionTemplate.selectList("warehouseProjectsDAO.selByOther",map);
		}
		return null;
	}

	@Override
	public int selByTopAll(WarehouseProjects wp) {
		return sqlSessionTemplate.selectOne("warehouseProjectsDAO.selByTopAll", wp);
	}

	@Override
	public int selAllCountProjects(WarehouseProjects wp) {
		return sqlSessionTemplate.selectOne("warehouseProjectsDAO.sel_count",wp);
	}

	@Override
	public int insertProject(WarehouseProjects wp) {
		return sqlSessionTemplate.insert("warehouseProjectsDAO.insertSelective", wp);
	}
	
}
