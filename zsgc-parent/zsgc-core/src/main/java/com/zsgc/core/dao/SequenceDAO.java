package com.zsgc.core.dao;

import org.springframework.beans.factory.InitializingBean;

import com.zsgc.core.model.SequenceRange;


public interface SequenceDAO extends InitializingBean {
    SequenceRange nextRange(String name);
}
