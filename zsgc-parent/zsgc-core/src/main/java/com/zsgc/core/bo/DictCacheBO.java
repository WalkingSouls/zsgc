package com.zsgc.core.bo;

import java.util.Map;

public interface DictCacheBO {
	Map<String,String> getMapValue(String typeKey, String dictKey);
	String getValue(String typeKey, String dictKey);
	Map<String,String> getALLMapValue(String typeKey);
}
