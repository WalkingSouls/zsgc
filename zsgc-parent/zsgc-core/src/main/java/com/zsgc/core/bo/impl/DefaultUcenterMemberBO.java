package com.zsgc.core.bo.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.calanger.common.util.RandomUtils;
import com.google.common.base.Strings;
import com.zsgc.core.bo.SpreadMobileBO;
import com.zsgc.core.bo.UcenterMemberBO;
import com.zsgc.core.dao.ActivityInvitationDAO;
import com.zsgc.core.dao.CouponConfigDAO;
import com.zsgc.core.dao.LoginLogDAO;
import com.zsgc.core.dao.MemberCheckDAO;
import com.zsgc.core.dao.MemberSpreadDAO;
import com.zsgc.core.dao.MoneyDAO;
import com.zsgc.core.dao.SpreadUserDAO;
import com.zsgc.core.dao.UcenterMemberDAO;
import com.zsgc.core.model.ActivityInvitation;
import com.zsgc.core.model.CouponConfig;
import com.zsgc.core.model.LoginLog;
import com.zsgc.core.model.MemberCheck;
import com.zsgc.core.model.MemberSpread;
import com.zsgc.core.model.Money;
import com.zsgc.core.model.SpreadMobile;
import com.zsgc.core.model.SpreadUser;
import com.zsgc.core.model.UcenterMember;

@Service("ucenterMemberBO")
public class DefaultUcenterMemberBO extends AbstractBO<UcenterMember, java.lang.Integer> implements UcenterMemberBO {
    private static final String INVITATION_CODE_CHARS = "ABDEFGHMNQRTYabcdefghijkmnpqrstuvwxyz23456789";

    @Autowired
    private UcenterMemberDAO ucenterMemberDAO;
    @Autowired
    private MoneyDAO moneyDAO;
    @Autowired
    private MemberSpreadDAO memberSpreadDAO;
    @Autowired
    private SpreadMobileBO spreadMobileBO;
    @Autowired
    private CouponConfigDAO couponConfigDAO;
    @Autowired
    private SpreadUserDAO spreadUserDAO;
    @Autowired
    private LoginLogDAO loginLogDAO;
    @Autowired
    private MemberCheckDAO memberCheckDAO;
    @Autowired
    private ActivityInvitationDAO activityInvitationDAO;

    @Override
    protected DAO<UcenterMember, java.lang.Integer> getDAO() {
        return ucenterMemberDAO;
    }

    @Transactional
    @Override
    public void loginSuccessUpdate(UcenterMember ucenterMember, UcenterMember ucenterMemberVO) {
        ucenterMemberDAO.update(ucenterMember, ucenterMemberVO);

        LoginLog loginLog = new LoginLog();
        loginLog.setUid(ucenterMember.getId());
        loginLog.setLoginTime(ucenterMember.getLastLoginTime());
        loginLog.setIp(ucenterMember.getLastLoginIp());
        loginLogDAO.insert(loginLog);
    }

    @Transactional
    @Override
    public void register(UcenterMember ucenterMember) {
    	
    	
    	
    	
        ucenterMember.setInvitationCode(getInvitationCode()); // 生成邀请码
        ucenterMemberDAO.insert(ucenterMember);
        int userId = ucenterMember.getId();

        if (ucenterMember.getReferrerId() != null) {
            SpreadMobile spreadMobileVO = new SpreadMobile();
            spreadMobileVO.setMobile(ucenterMember.getMobile());
            SpreadMobile spreadMobile = spreadMobileBO.get(spreadMobileVO);
            if (spreadMobile != null) {
                UcenterMember ucenterMemberEntity = new UcenterMember();
                if (ucenterMember.getReferrerType() == null && ucenterMember.getReferrerId() != 0) {
                    ucenterMemberEntity.setReferrerType(2); // 推荐类型 ：1 链接， 2 手机
                }
                Integer referrerId = spreadMobile.getReferrerId();
                String registerId = spreadMobile.getRegisterId();
                String material = spreadMobile.getMaterial();
                Integer lx = spreadMobile.getLx();

                if (referrerId != null) {
                    ucenterMemberEntity.setReferrerId(referrerId);
                }
                if (!Strings.isNullOrEmpty(registerId)) {
                    ucenterMemberEntity.setRegisterId(registerId);
                }
                if (!Strings.isNullOrEmpty(material)) {
                    ucenterMemberEntity.setMaterial(material);
                }
                if (lx != null) {
                    ucenterMemberEntity.setLx(lx);
                }
                ucenterMemberDAO.update(ucenterMemberEntity, userId);

                SpreadMobile entity = new SpreadMobile();
                entity.setUid(ucenterMember.getId());
                spreadMobileBO.update(entity, spreadMobile.getId());
            } else if (spreadMobile == null) {
                SpreadMobile entity = new SpreadMobile();
                String material1 = ucenterMember.getMaterial();
                Integer lx1 = ucenterMember.getLx();
                if (material1 != null) {
                    entity.setMaterial(material1);
                }
                if (lx1 != null) {
                    entity.setLx(lx1);
                }
                entity.setMobile(ucenterMember.getMobile());
                entity.setUid(ucenterMember.getId());
                entity.setReferrerId(ucenterMember.getReferrerId());
                entity.setRegisterId(ucenterMember.getRegisterId());
                spreadMobileBO.insert(entity);
            }
        }

        Money money = new Money();
        money.setUid(ucenterMember.getId());
        money.setBalance(new BigDecimal(0));
        money.setFreeze(new BigDecimal(0));

        moneyDAO.insert(money);

        //

  /*      // 更新推荐人信息
        if (ucenterMember.getReferrerId() != null && ucenterMember.getReferrerId() != 0) {
            memberSpreadDAO.addUserNumber(ucenterMember.getReferrerId());
            // 
            SpreadUser spreadUser = new SpreadUser();
            spreadUser.setUid(ucenterMember.getId());
            spreadUser.setReferrerId(ucenterMember.getReferrerId());
            spreadUser.setRegistedAt(new Date());
            spreadUserDAO.insert(spreadUser);

            // 新增邀请好友注册人数
            ActivityInvitation activityInvitation = activityInvitationDAO.get(ucenterMember.getReferrerId());
            if (activityInvitation != null) {
                ActivityInvitation entity =  new ActivityInvitation();
                entity.increment("registerNumber", 1);
                activityInvitationDAO.update(entity, activityInvitation.getId());
            } else {
                activityInvitation = new ActivityInvitation();
                activityInvitation.setId(ucenterMember.getReferrerId());
                activityInvitation.setRegisterNumber(1);
                activityInvitation.setInvestedNumber(0);
                activityInvitation.setInvestedMoney(BigDecimal.ZERO);
                activityInvitation.setUndistributedMoney(BigDecimal.ZERO);
                activityInvitation.setCouponInvest(BigDecimal.ZERO);
                activityInvitationDAO.insert(activityInvitation);
            }
        }*/

    }

    @Transactional
    @Override
    public void register(UcenterMember ucenterMember, MemberCheck memberCheck) {
        ucenterMember.setInvitationCode(getInvitationCode()); // 生成邀请码
        ucenterMemberDAO.insert(ucenterMember);

        // 资金账户
        Money money = new Money();
        money.setUid(ucenterMember.getId());
        money.setBalance(new BigDecimal(0));
        money.setFreeze(new BigDecimal(0));

        moneyDAO.insert(money);

        // 插入推广用户统计
        MemberSpread memberSpread = new MemberSpread();
        memberSpread.setId(ucenterMember.getId());
        memberSpreadDAO.insert(memberSpread);

        // 实名认证
        memberCheck.setUid(ucenterMember.getId());
        memberCheck.setAdminId(0);
        memberCheckDAO.insert(memberCheck);

    }

    /**
     * 生成邀请码
     * @return
     */
    public String getInvitationCode() {
        String invitationCode = null;
        for (int i = 0; i < 100; i++) {
            invitationCode = RandomUtils.getRandomString(INVITATION_CODE_CHARS, 4);

            UcenterMember ucenterMemberVO = new UcenterMember();
            ucenterMemberVO.setInvitationCode(invitationCode);
            if (ucenterMemberDAO.get(ucenterMemberVO) == null) {
                break;
            }
        }
        return invitationCode;
    }


}
