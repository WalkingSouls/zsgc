package com.zsgc.core.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.SecurityTicketDAO;
import com.zsgc.core.model.SecurityTicket;

@Repository("securityTicketDAO")
public class MyBatisSecurityTicketDAO extends AbstractDAO<SecurityTicket, java.lang.String> implements SecurityTicketDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "securityTicketDAO";
    }
}
