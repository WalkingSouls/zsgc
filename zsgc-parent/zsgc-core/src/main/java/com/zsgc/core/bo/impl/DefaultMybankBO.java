package com.zsgc.core.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.calanger.common.dao.OrderBy;
import com.zsgc.core.bo.MemberCouponBO;
import com.zsgc.core.bo.MybankBO;
import com.zsgc.core.dao.MybankDAO;
import com.zsgc.core.dao.ShortMessageDAO;
import com.zsgc.core.dao.UcenterMemberDAO;
import com.zsgc.core.model.BankInfo;
import com.zsgc.core.model.Mybank;
import com.zsgc.core.model.ShortMessage;
import com.zsgc.core.model.UcenterMember;

@Service("mybankBO")
public class DefaultMybankBO extends AbstractBO<Mybank, java.lang.Integer> implements MybankBO {
    @Autowired
    private MybankDAO mybankDAO;
    @Autowired
    private ShortMessageDAO shortMessageDAO;
    @Autowired
    private UcenterMemberDAO ucenterMemberDAO;
    @Autowired
    private MemberCouponBO memberCouponBO;

    @Override
    protected DAO<Mybank, java.lang.Integer> getDAO() {
        return mybankDAO;
    }

    @Transactional
    @Override
    public void addBankCard(Mybank mybank, ShortMessage shortMessage, ShortMessage shortMessageVO) {
        mybankDAO.insert(mybank);
        shortMessageDAO.update(shortMessage, shortMessageVO);
    }

    @Override
    public List<BankInfo> getBankList(Mybank mybankVO, OrderBy orderBy) {
        return mybankDAO.getBankList(mybankVO,orderBy);
    }

    @Override
    public Mybank getMybank(int id) {
        Mybank mybankVO = new Mybank();
        mybankVO.setUid(id);
        return mybankDAO.get(mybankVO);
    }

    @Override
    public void create(Mybank mybank) {
        mybankDAO.insert(mybank);

        UcenterMember entity = new UcenterMember();
        entity.setIsBindBankCard(1);
        ucenterMemberDAO.update(entity, mybank.getUid());

        int userId = mybank.getUid();

     
    }
}
