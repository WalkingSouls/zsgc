package com.zsgc.core.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.BankBO;
import com.zsgc.core.dao.BankDAO;
import com.zsgc.core.model.Bank;

@Service("bankBO")
public class DefaultBankBO extends AbstractBO<Bank, java.lang.Integer> implements BankBO {
    @Autowired
    private BankDAO bankDAO;

    @Override
    protected DAO<Bank, java.lang.Integer> getDAO() {
        return bankDAO;
    }
}
