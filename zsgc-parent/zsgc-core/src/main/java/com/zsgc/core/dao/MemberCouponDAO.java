package com.zsgc.core.dao;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.MemberCoupon;

public interface MemberCouponDAO extends DAO<MemberCoupon, java.lang.Integer> {

	/**
	 * 红包使用人数
	 */
	int countMemberCouponNumber(String todayBegin, String todayEnd);
}
