package com.zsgc.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.calanger.common.dao.OrderBy;
import com.zsgc.core.dao.DictDAO;
import com.zsgc.core.dao.DictTypeDAO;
import com.zsgc.core.model.Dict;
import com.zsgc.core.model.DictType;

public final class DictUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictUtils.class);

    private static final ConcurrentMap<String, CacheObject<Map<String, Dict>>> DICT_TYPE_MAP = new ConcurrentHashMap<String, CacheObject<Map<String, Dict>>>();

    private static final ScheduledExecutorService EXECUTOR = Executors.newScheduledThreadPool(1);
    
    private static DictTypeDAO dictTypeDAO;

    private static DictDAO dictDAO;

    public static void setDictTypeDAO(DictTypeDAO dictTypeDAO) {
        DictUtils.dictTypeDAO = dictTypeDAO;
    }

    public static void setDictDAO(DictDAO dictDAO) {
        DictUtils.dictDAO = dictDAO;
    }

    static {
        EXECUTOR.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    List<DictType> dictTypeList = dictTypeDAO.find();
                    for (DictType dictType : dictTypeList) {
                        CacheObject<Map<String, Dict>> cacheObject = DICT_TYPE_MAP.get(dictType.getTypeKey());
                        if (cacheObject == null) {
                            continue;
                        }

                        if (cacheObject.getVersion() != dictType.getVersion()) {
                            DICT_TYPE_MAP.remove(dictType.getTypeKey());
                        }
                    }
                } catch (Throwable e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }, 60, 120, TimeUnit.SECONDS);
    }

    public static List<Dict> getDictList(String typeKey) {
        cache(typeKey);

        return new ArrayList<Dict>(DICT_TYPE_MAP.get(typeKey).getObject().values());
    }

    public static List<Map<String, String>> getKeyValueList(String typeKey) {
        List<Dict> dictList = getDictList(typeKey);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>(dictList.size());
        for (Dict dict : dictList) {
            Map<String, String> e = new LinkedHashMap<String, String>();
            e.put("key", dict.getDictKey());
            e.put("value", dict.getDictValue());
            list.add(e);
        }
        return list;
    }
    
    public static Map<String, Dict> getAllDict(String typeKey) {
    	cache(typeKey);
        return DICT_TYPE_MAP.get(typeKey).getObject();
    }
    
    public static Dict getDict(String typeKey, String dictKey) {
        cache(typeKey);

        return DICT_TYPE_MAP.get(typeKey).getObject().get(dictKey);
    }

    public static Dict getDict(String typeKey, int dictKey) {
        return getDict(typeKey, String.valueOf(dictKey));
    }

    public static String getDictValue(String typeKey, String dictKey) {
        Dict dict = getDict(typeKey, dictKey);

        return dict != null ? dict.getDictValue() : dictKey;
    }
    
    public static String getDictValue(String typeKey, int dictKey) {
        return getDictValue(typeKey, String.valueOf(dictKey));
    }

    private static void cache(String typeKey) {
        if (!DICT_TYPE_MAP.containsKey(typeKey)) {
            synchronized (DictUtils.class) {
                if (!DICT_TYPE_MAP.containsKey(typeKey)) {
                    CacheObject<Map<String, Dict>> cacheObject = new CacheObject<Map<String, Dict>>();

                    DictType dictTypeCondition = new DictType();
                    dictTypeCondition.setTypeKey(typeKey);
                    DictType dictType = dictTypeDAO.get(dictTypeCondition);
                    if (dictType != null) {
                        Dict dictCondition = new Dict();
                        dictCondition.setTypeId(dictType.getId());
                        List<Dict> dictList = dictDAO.find(dictCondition, new OrderBy().add("sortOrder"));
                        Map<String, Dict> dictMap = new LinkedHashMap<String, Dict>();
                        for (Dict dict : dictList) {
                            dictMap.put(dict.getDictKey(), dict);
                        }

                        cacheObject.setVersion(dictType.getVersion());
                        cacheObject.setObject(dictMap);
                    } else {
                        cacheObject.setVersion(0);
                        cacheObject.setObject(Collections.<String, Dict> emptyMap());
                    }

                    DICT_TYPE_MAP.putIfAbsent(typeKey, cacheObject);
                }
            }
        }
    }

    private static class CacheObject<T> {
        private int version;
        private T object;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public T getObject() {
            return object;
        }

        public void setObject(T object) {
            this.object = object;
        }
    }

    private DictUtils() {
    }
}
