package com.zsgc.core.bo.impl;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.MemberCouponRuleBO;
import com.zsgc.core.model.MemberCouponRule;

import org.springframework.stereotype.Service;


@Service("memberCouponRuleBO")
public class DefaultMemberCouponRuleBO extends AbstractBO<MemberCouponRule, Integer> implements MemberCouponRuleBO {

   /* @Autowired
    private MemberCouponRuleDAO memberCouponRuleDAO;*/


    @Override
    protected DAO<MemberCouponRule, Integer> getDAO() {
//        return memberCouponRuleDAO;
        return null;
    }
}
