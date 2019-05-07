package com.zsgc.core.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.BankBranchBO;
import com.zsgc.core.dao.BankBranchDAO;
import com.zsgc.core.model.BankBranch;

@Service("bankBranchBO")
public class DefaultBankBranchBO extends AbstractBO<BankBranch, java.lang.Integer> implements BankBranchBO {
    @Autowired
    private BankBranchDAO bankBranchDAO;

    @Override
    protected DAO<BankBranch, java.lang.Integer> getDAO() {
        return bankBranchDAO;
    }
}
