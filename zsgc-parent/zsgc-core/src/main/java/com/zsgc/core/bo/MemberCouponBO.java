package com.zsgc.core.bo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.calanger.common.bo.BO;
import com.zsgc.core.model.MemberCoupon;

public interface MemberCouponBO extends BO<MemberCoupon, java.lang.Integer> {
    /**
     * @Title: MemberCouponBO.java
     * @Prject: zsgc-core
     * @version: V1.0 
     * @param memberCoupon
     */
    void useCashCoupon(MemberCoupon memberCoupon);

    /**
     * 创建红包
     * @param userId            用户ID
     * @param type              
     * @param name              名称，
     * @param money             金额
     * @param rate              
     * @param minInvestMoney    
     * @param maxInvestMoney    
     * @param minDeadline       
     * @param maxDeadline       
     * @param validityDays      
     * @param fromWhere         
	 * @Param remark            备注
     */
    void createMemberCoupon(int userId, int type, String name, BigDecimal money, BigDecimal rate, BigDecimal minInvestMoney, BigDecimal maxInvestMoney, int minDeadline, int maxDeadline, int validityDays, String fromWhere,String remark);

	

	/**
	 * 根据状态获取用户红包集合
	 * @param uid 用户ID
	 * @param status 0-可用 1-已使用 2-已过期
	 * @param type 1-红包 4-息券
	 */
	List<Map<String, Object>> findMemberCouponByStatus(Integer uid, String status, Integer type);
}
