package com.zsgc.core.dao;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.Config;

public interface ConfigDAO extends DAO<Config, java.lang.Long> {
    String getNumber(String prefix);
    String getValueByName(String name);
}
