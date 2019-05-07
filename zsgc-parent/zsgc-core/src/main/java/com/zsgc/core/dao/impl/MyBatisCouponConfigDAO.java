package com.zsgc.core.dao.impl;

import java.math.BigDecimal;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.calanger.common.dao.AbstractDAO;
import com.zsgc.core.dao.CouponConfigDAO;
import com.zsgc.core.model.CouponConfig;

@Repository("couponConfigDAO")
public class MyBatisCouponConfigDAO extends AbstractDAO<CouponConfig, java.lang.Integer> implements CouponConfigDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "couponConfigDAO";
    }

    /**
     * 根据编号获取红包配置
     * @param code
     * @return
     */
    public CouponConfig getByCode(String code){
        CouponConfig couponConfigVO = new CouponConfig();
        couponConfigVO.setCode(code);
        return get(couponConfigVO);
    }

    /**
     * 获取指定范围内的随机金额（不包含小数点）
     * @param couponConfig
     * @return
     */
    public BigDecimal getRandomCouponMoney(CouponConfig couponConfig){
        BigDecimal minMoney = couponConfig.getMoneyMin();
        BigDecimal maxMoney = couponConfig.getMoneyMax();
        if(minMoney.compareTo(maxMoney) == 0){
            return minMoney;
        }
        BigDecimal rnd = new BigDecimal(Math.random());
        BigDecimal couponMoney = rnd.multiply((maxMoney.subtract(minMoney)))
                .add(minMoney)
                .setScale(0, BigDecimal.ROUND_DOWN);
        return couponMoney;
    }
}
