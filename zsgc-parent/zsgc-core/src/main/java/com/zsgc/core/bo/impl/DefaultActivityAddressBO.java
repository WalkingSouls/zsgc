package com.zsgc.core.bo.impl;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.ActivityAddressBO;
import com.zsgc.core.dao.ActivityAddressDAO;
import com.zsgc.core.model.ActivityAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("activityAddressBO")
public class DefaultActivityAddressBO extends AbstractBO<ActivityAddress,java.lang.Integer> implements ActivityAddressBO{

    @Autowired
    private ActivityAddressDAO activityAddressDAO;

    @Override
    protected DAO<ActivityAddress, Integer> getDAO() {
        return activityAddressDAO;
    }
}
