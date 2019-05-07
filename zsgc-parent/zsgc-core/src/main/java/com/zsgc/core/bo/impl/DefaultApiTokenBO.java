package com.zsgc.core.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.ApiTokenBO;
import com.zsgc.core.dao.ApiTokenDAO;
import com.zsgc.core.model.ApiToken;

@Service("apiTokenBO")
public class DefaultApiTokenBO extends AbstractBO<ApiToken, java.lang.Integer> implements ApiTokenBO {
    @Autowired
    private ApiTokenDAO apiTokenDAO;

    @Override
    protected DAO<ApiToken, java.lang.Integer> getDAO() {
        return apiTokenDAO;
    }
}
