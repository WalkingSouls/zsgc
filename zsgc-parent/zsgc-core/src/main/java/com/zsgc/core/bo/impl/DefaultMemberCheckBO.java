package com.zsgc.core.bo.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.MemberCheckBO;
import com.zsgc.core.dao.MemberCheckDAO;
import com.zsgc.core.dao.UcenterMemberDAO;
import com.zsgc.core.model.MemberCheck;
import com.zsgc.core.model.Mybank;
import com.zsgc.core.model.UcenterMember;

@Service("memberCheckBO")
public class DefaultMemberCheckBO extends AbstractBO<MemberCheck, java.lang.Integer> implements MemberCheckBO {
    @Autowired
    private MemberCheckDAO memberCheckDAO;
    @Autowired
    private UcenterMemberDAO ucenterMemberDAO;

    @Override
    protected DAO<MemberCheck, java.lang.Integer> getDAO() {
        return memberCheckDAO;
    }

    @Override
    public MemberCheck getMemberCheck(int id) {
        MemberCheck memberCheckVO = new MemberCheck();
        memberCheckVO.setUid(id);
        return memberCheckDAO.get(memberCheckVO);
    }

    /**
     * 绑定银行卡
     */
    @Transactional
    public void bindBankCard(MemberCheck memberCheck, Mybank mybank) {
        if (memberCheck != null) {
            MemberCheck memberCheckVO = new MemberCheck();
            memberCheckVO.setUid(memberCheck.getUid());
            MemberCheck old = memberCheckDAO.get(memberCheckVO);
            if (old == null) {
                memberCheckDAO.insert(memberCheck);
                //赠送推荐人现金红包
                UcenterMember ucenterMember = ucenterMemberDAO.get(memberCheck.getUid());
                if(ucenterMember.getReferrerId() != null && ucenterMember.getReferrerId() != 0){
        
                }
            } else {
                memberCheckDAO.update(memberCheck, memberCheckVO);
            }
        }
  
    }

    @Transactional
    @Override
    public void create(MemberCheck entity) {
        UcenterMember ucenterMemberEntity = new UcenterMember();
        ucenterMemberEntity.setIsAuthenticated(1);
        ucenterMemberEntity.setName(entity.getName(), true);

        ucenterMemberDAO.update(ucenterMemberEntity, entity.getUid());

        MemberCheck memberCheckVO = new MemberCheck();
        memberCheckVO.setUid(entity.getUid());

        MemberCheck memberCheck = memberCheckDAO.get(memberCheckVO);

        if (memberCheck == null) {
        } else {
            if (memberCheck.getStatus() != 1) {
                memberCheckDAO.update(entity, memberCheck.getId());
            }
        }
    }
}
