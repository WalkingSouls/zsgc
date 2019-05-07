package com.zsgc.core.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.calanger.common.dao.AbstractDAO;
import com.google.common.collect.Maps;
import com.zsgc.core.dao.ConfigDAO;
import com.zsgc.core.model.Config;

@Repository("configDAO")
public class MyBatisConfigDAO extends AbstractDAO<Config, java.lang.Long> implements ConfigDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    protected String getNamespace() {
        return "configDAO";
    }

    @Override
    public String getNumber(String prefix) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("prefix", prefix);

        return getSqlSessionTemplate().<String> selectOne(getNamespace() + ".getNumber", params);
    }

    public String getValueByName(String name) {
        Config configVO = new Config();
        configVO.setName(name);
        Config config = get(configVO);
        if (config != null) {
            return config.getConfigValue();
        }
        return null;
    }
}
