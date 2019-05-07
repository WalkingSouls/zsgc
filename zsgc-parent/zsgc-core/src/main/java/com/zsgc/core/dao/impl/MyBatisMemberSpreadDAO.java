package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.MemberSpreadDAO;
import com.zsgc.core.model.MemberSpread;

@Repository("memberSpreadDAO")
public class MyBatisMemberSpreadDAO extends AbstractDAO<MemberSpread, java.lang.Integer> implements MemberSpreadDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "memberSpreadDAO";
    }

    /**
     * 增加访问量
     * @param id
     * @return
     */
    public int addViewNumber(int id){
        return getSqlSessionTemplate().update(getNamespace() + ".addViewNumber", id);
    }
    /**
     * 增加访问量【独立IP数】
     * @param id
     * @return
     */
    public int addIpNumber(int id){
        return getSqlSessionTemplate().update(getNamespace() + ".addIpNumber", id);
    }
    public int addUserNumber(int id){
        return getSqlSessionTemplate().update(getNamespace() + ".addUserNumber", id);
    }
}
