package com.zsgc.core.bo;

import com.calanger.common.bo.BO;
import com.zsgc.core.model.MemberCheck;
import com.zsgc.core.model.Mybank;

public interface MemberCheckBO extends BO<MemberCheck, java.lang.Integer> {
    MemberCheck getMemberCheck(int id);
    /**
     * 绑定银行卡
     */
    void bindBankCard(MemberCheck memberCheck, Mybank mybank);

    void create(MemberCheck entity);
}
