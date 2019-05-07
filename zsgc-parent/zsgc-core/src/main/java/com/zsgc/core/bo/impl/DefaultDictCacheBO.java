package com.zsgc.core.bo.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calanger.common.dao.OrderBy;
import com.zsgc.core.bo.DictCacheBO;
import com.zsgc.core.dao.DictDAO;
import com.zsgc.core.dao.DictTypeDAO;
import com.zsgc.core.model.Dict;
import com.zsgc.core.model.DictType;
import com.zsgc.core.utils.DictCache;
import com.zsgc.core.utils.EntityCache;

@Service("dictCacheBO")
public class DefaultDictCacheBO<T> implements DictCacheBO {
	@Autowired
	private DictTypeDAO dictTypeDAO;
	@Autowired
	private DictDAO dictDAO;
	
	private static final Long TIME_OUT = 60 * 1000*10L;
	
	private DictCache<?> dictCache = new DictCache<>();

	private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

	{
		executorService.scheduleAtFixedRate(() -> {
			List<DictType> dictTypeList = dictTypeDAO.find();
			for (DictType dictType : dictTypeList) {
				String typeKey = dictType.getTypeKey();
				EntityCache<?> cacheByKey = dictCache.getCacheByKey(typeKey);
				if (cacheByKey == null) {
					continue;
				}
				if (dictCache.isTimeOut(typeKey)) {
					dictCache.clearByKey(typeKey);
					continue;
				}
				if (cacheByKey.getVersion() != dictType.getVersion()) {
					dictCache.clearByKey(typeKey);
				}
			}
		} , 60, 120, TimeUnit.SECONDS);
	}
	
	@Override
	public String getValue(String typeKey, String dictKey) {
		cache(typeKey);
		@SuppressWarnings("unchecked")
		Map<String, Dict> mapDict = (Map<String, Dict>) dictCache.getCacheDataByKey(typeKey);
		return mapDict.get(dictKey).getDictValue();
	}
	
	@Override
	public Map<String,String> getMapValue(String typeKey, String dictKey) {
		cache(typeKey);
		@SuppressWarnings("unchecked")
		Map<String, Dict> mapDict = (Map<String, Dict>) dictCache.getCacheDataByKey(typeKey);
		Dict dict = mapDict.get(dictKey);
		Map<String,String> map = new HashMap<>();
		map.put(dict.getDictValue1(), dict.getDictValue());
		return map;
	}
	
	@Override
	public Map<String,String> getALLMapValue(String typeKey) {
		cache(typeKey);
		@SuppressWarnings("unchecked")
		Map<String, Dict> mapDict = (Map<String, Dict>) dictCache.getCacheDataByKey(typeKey);
		Map<String,String> map = new HashMap<>();
		for (Dict dict : mapDict.values()) {
			map.put(dict.getDictValue1(), dict.getDictValue());
		}
		return map;
	}
	
	private void cache(String typeKey) {
		if (!dictCache.isContains(typeKey)) {
			synchronized (this) {
				if (!dictCache.isContains(typeKey)) {
					DictType dictTypeCondition = new DictType();
					dictTypeCondition.setTypeKey(typeKey);
					DictType dictType = dictTypeDAO.get(dictTypeCondition);
					EntityCache<Map<String, Dict>> entityCache = new EntityCache<>();
					if (dictType != null) {
						Dict dictCondition = new Dict();
						dictCondition.setTypeId(dictType.getId());
						List<Dict> dictList = dictDAO.find(dictCondition, new OrderBy().add("sortOrder"));
						Map<String, Dict> dictMap = new LinkedHashMap<String, Dict>();
						for (Dict dict : dictList) {
							dictMap.put(dict.getDictKey(), dict);
						}
						entityCache.setVersion(dictType.getVersion());
						entityCache.setDatas(dictMap);
						entityCache.setTimeOut(TIME_OUT);
						entityCache.setLastRefeshTime(System.currentTimeMillis());
					} else {
						entityCache.setVersion(0);
						entityCache.setDatas(Collections.<String, Dict> emptyMap());
					}
					dictCache.putCache(typeKey, entityCache);
				}
			}
		}
	}	
	@PreDestroy
	public void shutDown() {
		executorService.shutdown();
	}

}
