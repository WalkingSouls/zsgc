package com.zsgc.core.bo;

import com.calanger.common.bo.BO;
import com.zsgc.core.model.AppToken;

public interface AppTokenBO extends BO<AppToken, java.lang.Integer> {
    void create(AppToken entity);
    AppToken getByToken(String token);
    void off(int userId);
}
