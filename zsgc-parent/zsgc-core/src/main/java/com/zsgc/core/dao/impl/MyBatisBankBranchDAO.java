package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.BankBranchDAO;
import com.zsgc.core.model.BankBranch;

@Repository("bankBranchDAO")
public class MyBatisBankBranchDAO extends AbstractDAO<BankBranch, java.lang.Integer> implements BankBranchDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "bankBranchDAO";
    }
}
