package com.zsgc.core.bo;

import java.math.BigDecimal;
import java.util.Map;

import com.calanger.common.bo.BO;
import com.zsgc.core.model.Money;
import com.zsgc.core.model.UcenterMember;

public interface MoneyBO extends BO<Money, java.lang.Integer> {
	
    /**
     * @Title: MoneyBO.java
     * @Prject: zsgc-core
     * @Package: com.zsgc.core.bo.impl
     * @Description: 根据用户获取资金数据
     * @version: V1.0 
     * @param uid
     * @return
     */
    Money getByUid(int uid);
    /**
     * 得到用户资金信息
     * @param uid 用户id
     * @return
     */
    Map<String, BigDecimal> getUserMoneyInfo(int uid);

    /**
     * 统计投资总金额
     * @return
     */
    BigDecimal sumInvestTotal(UcenterMember condition);
}
