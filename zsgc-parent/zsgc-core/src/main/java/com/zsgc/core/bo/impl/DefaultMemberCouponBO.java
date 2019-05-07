package com.zsgc.core.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.calanger.common.dao.Expressions;
import com.zsgc.core.bo.MemberCouponBO;
import com.zsgc.core.dao.CashDAO;
import com.zsgc.core.dao.MemberCouponDAO;
import com.zsgc.core.dao.MoneyDAO;
import com.zsgc.core.model.Cash;
import com.zsgc.core.model.MemberCoupon;
import com.zsgc.core.model.Money;
import com.zsgc.core.utils.Sequences;

@Service("memberCouponBO")
public class DefaultMemberCouponBO extends AbstractBO<MemberCoupon, java.lang.Integer> implements MemberCouponBO {
    @Autowired
    private MemberCouponDAO memberCouponDAO;
    @Autowired
    private MoneyDAO moneyDAO;
    @Autowired
    private CashDAO cashDAO;

    @Override
    protected DAO<MemberCoupon, java.lang.Integer> getDAO() {
        return memberCouponDAO;
    }

    /**
     * @Title: DefaultMemberCouponBO.java
     * @version: V1.0 
     * @param memberCoupon
     */
    @Transactional
    public void useCashCoupon(MemberCoupon memberCoupon){
        if(memberCoupon == null){
            return;
        }
        if(memberCoupon.getType() != 3){
            return;
        }
        if(memberCoupon.getUsedAt() != null){
            return;
        }
        if(memberCoupon.getEndAt().before(new Date())){
            return;
        }

        // 修改红包状态
        MemberCoupon memberCouponVO = new MemberCoupon();
        memberCouponVO.setId(memberCoupon.getId());
        memberCouponVO.setType(3);
        memberCouponVO.and(Expressions.isNull("usedAt"));
        memberCouponVO.and(Expressions.ge("endAt", new Date()));
        MemberCoupon couponUpdate = new MemberCoupon();
        couponUpdate.setUsedAt(new Date());
        int rows = memberCouponDAO.update(couponUpdate, memberCouponVO);
        if(rows == 0){
            throw new RuntimeException("Can not update coupon status");
        }

        // 增加用户余额
        Money selectedMoney = null;
        for (int i = 0; i < 32; i++) {
            Money moneyVO = new Money();
            moneyVO.setUid(memberCoupon.getUid());
            selectedMoney = moneyDAO.get(moneyVO);

            Money moneyEntity = new Money();
            moneyEntity.setBalance(selectedMoney.getBalance().add(memberCoupon.getMoney()));
            
            Money moneyCondition = new Money();
            moneyCondition.setUid(selectedMoney.getUid());
            moneyCondition.setVersion(selectedMoney.getVersion());

            if (moneyDAO.update(moneyEntity, moneyCondition) == 1) {
                break;
            }
            selectedMoney = null;
        }

        if (selectedMoney == null) {
            throw new RuntimeException("Money optimistic lock, retry failed");
        }

        // 生成流水
        String number = Sequences.getNo("cash_no");
        Cash cashEntity = new Cash();
        cashEntity.setNumber(number);
        cashEntity.setOrderId("PRB" + memberCoupon.getId());
        cashEntity.setUid(memberCoupon.getUid());
        cashEntity.setCash(memberCoupon.getMoney());
        cashEntity.setType(-1);
        cashEntity.setBalance(selectedMoney.getBalance().add(memberCoupon.getMoney()));
        cashEntity.setFreeze(selectedMoney.getFreeze());
        BigDecimal total = cashEntity.getBalance().add(cashEntity.getFreeze()).add(selectedMoney.getReceivableInvestMoney()).add(selectedMoney.getReceivableInterestMoney());
        cashEntity.setTotal(total);

        cashDAO.insert(cashEntity);
    }

	@Override
	public void createMemberCoupon(int userId, int type, String name, BigDecimal money, BigDecimal rate,
			BigDecimal minInvestMoney, BigDecimal maxInvestMoney, int minDeadline, int maxDeadline, int validityDays,
			String fromWhere, String remark) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> findMemberCouponByStatus(Integer uid, String status, Integer type) {
		// TODO Auto-generated method stub
		return null;
	}
}
