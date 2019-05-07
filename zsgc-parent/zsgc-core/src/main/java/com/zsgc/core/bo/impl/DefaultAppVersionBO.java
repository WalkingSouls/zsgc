package com.zsgc.core.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.AppVersionBO;
import com.zsgc.core.dao.AppVersionDAO;
import com.zsgc.core.model.AppVersion;

@Service("appVersionBO")
public class DefaultAppVersionBO extends AbstractBO<AppVersion, java.lang.Integer> implements AppVersionBO {
    @Autowired
    private AppVersionDAO appVersionDAO;

    @Override
    protected DAO<AppVersion, java.lang.Integer> getDAO() {
        return appVersionDAO;
    }
}
