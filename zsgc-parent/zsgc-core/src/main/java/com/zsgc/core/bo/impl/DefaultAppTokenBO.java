package com.zsgc.core.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.AppTokenBO;
import com.zsgc.core.dao.AppTokenDAO;
import com.zsgc.core.model.AppToken;

@Service("appTokenBO")
public class DefaultAppTokenBO extends AbstractBO<AppToken, java.lang.Integer> implements AppTokenBO {
    @Autowired
    private AppTokenDAO appTokenDAO;

    @Override
    protected DAO<AppToken, java.lang.Integer> getDAO() {
        return appTokenDAO;
    }

    @Transactional
    @Override
    public void create(AppToken entity) {
        off(entity.getUserId());

        appTokenDAO.insert(entity);

    }

    @Override
    public AppToken getByToken(String token) {
        AppToken condition = new AppToken();
        condition.setToken(token);
        condition.setDisabled(0);
        return appTokenDAO.get(condition);
    }

    @Transactional
    @Override
    public void off(int userId) {
        AppToken entity = new AppToken();
        entity.setDisabled(1);

        AppToken condition = new AppToken();
        condition.setUserId(userId);
        condition.setDisabled(0);

        appTokenDAO.update(entity, condition);
    }
}
