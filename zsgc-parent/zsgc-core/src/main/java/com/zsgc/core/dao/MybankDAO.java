package com.zsgc.core.dao;

import java.util.List;

import com.calanger.common.dao.DAO;
import com.calanger.common.dao.OrderBy;
import com.zsgc.core.model.BankInfo;
import com.zsgc.core.model.Mybank;

public interface MybankDAO extends DAO<Mybank, java.lang.Integer> {
    List<BankInfo> getBankList(Mybank mybankVO, OrderBy orderBy);
}
