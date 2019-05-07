package com.zsgc.core.bo;

import java.util.List;


import com.calanger.common.bo.BO;
import com.calanger.common.dao.OrderBy;
import com.zsgc.core.model.BankInfo;
import com.zsgc.core.model.Mybank;
import com.zsgc.core.model.ShortMessage;

public interface MybankBO extends BO<Mybank, java.lang.Integer> {
    void addBankCard(Mybank mybank, ShortMessage shortMessage, ShortMessage shortMessageVO);

    List<BankInfo> getBankList(Mybank mybank, OrderBy orderBy);

    Mybank getMybank(int id);

    void create(Mybank mybank);
}
