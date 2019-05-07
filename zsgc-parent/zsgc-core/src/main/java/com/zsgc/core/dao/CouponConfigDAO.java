package com.zsgc.core.dao;

import java.math.BigDecimal;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.CouponConfig;

public interface CouponConfigDAO extends DAO<CouponConfig, java.lang.Integer> {
    /**
     * 
     * @param code
     * @return
     */
    CouponConfig getByCode(String code);
    /**
     * 
     * @param couponConfig
     * @return
     */
    BigDecimal getRandomCouponMoney(CouponConfig couponConfig);
}
