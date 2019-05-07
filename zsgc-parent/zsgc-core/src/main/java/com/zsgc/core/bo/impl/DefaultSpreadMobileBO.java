package com.zsgc.core.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.zsgc.core.bo.SpreadMobileBO;
import com.zsgc.core.dao.SpreadMobileDAO;
import com.zsgc.core.model.SpreadMobile;

@Service("spreadMobileBO")
public class DefaultSpreadMobileBO extends AbstractBO<SpreadMobile, java.lang.Integer> implements SpreadMobileBO {
    @Autowired
    private SpreadMobileDAO spreadMobileDAO;

    @Override
    protected DAO<SpreadMobile, java.lang.Integer> getDAO() {
        return spreadMobileDAO;
    }
}
