package com.zsgc.core.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.SecurityTicketBO;
import com.zsgc.core.dao.SecurityTicketDAO;
import com.zsgc.core.model.SecurityTicket;

@Service("securityTicketBO")
public class DefaultSecurityTicketBO extends AbstractBO<SecurityTicket, java.lang.String> implements SecurityTicketBO {
    @Autowired
    private SecurityTicketDAO securityTicketDAO;

    @Override
    protected DAO<SecurityTicket, java.lang.String> getDAO() {
        return securityTicketDAO;
    }
}
