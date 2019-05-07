
package com.zsgc.core.dao.impl;

import com.calanger.common.dao.AbstractDAO; 
import com.zsgc.core.dao.DesPictureDAO;
import com.zsgc.core.model.DesPicture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository;

@Repository("desPictureDAO") 
public class MyBatisDesPictureDAO extends AbstractDAO<DesPicture,java.lang.Integer> implements
DesPictureDAO{

	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate;

	@Override 
	protected SqlSessionTemplate getSqlSessionTemplate() { 
		return sqlSessionTemplate; 
	}

	@Override 
	protected String getNamespace() { 
		return "desPictureDAO"; 
	}

	@Override
	public List<DesPicture> getList(DesPicture desPic) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("condition", desPic);  
		return getSqlSessionTemplate().selectList(getNamespace()+".getList",params);
	}
}
