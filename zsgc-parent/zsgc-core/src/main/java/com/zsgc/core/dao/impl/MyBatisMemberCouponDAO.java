package com.zsgc.core.dao.impl;

import com.google.common.collect.Maps;
import com.zsgc.core.dao.MemberCouponDAO;
import com.zsgc.core.model.MemberCoupon;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;

import java.util.Map;

@Repository("memberCouponDAO")
public class MyBatisMemberCouponDAO extends AbstractDAO<MemberCoupon, java.lang.Integer> implements MemberCouponDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "memberCouponDAO";
    }

	/**
	 * 红包使用人数
	 */
	@Override
	public int countMemberCouponNumber(String todayBegin, String todayEnd) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("todayBegin", todayBegin);
		params.put("todayEnd", todayEnd);
		return getSqlSessionTemplate().<Integer> selectOne(getNamespace() + ".countMemberCouponNumber", params);
	}
}
