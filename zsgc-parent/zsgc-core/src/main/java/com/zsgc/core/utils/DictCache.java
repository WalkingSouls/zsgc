package com.zsgc.core.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
public class DictCache<T> {
	private static Map<String, EntityCache<?>> caches = new ConcurrentHashMap<String, EntityCache<?>>();

	// 存入缓存

	public void putCache(String key, EntityCache<?> cache) {
		caches.put(key, cache);
	}

	// 存入缓存

	public void putCache(String key, T datas, long timeOut, int version) {
		timeOut = timeOut > 0 ? timeOut : 0L;
		putCache(key, new EntityCache<T>(datas, timeOut, version, System.currentTimeMillis()));
	}

	// 获取对应缓存

	public EntityCache<?> getCacheByKey(String key) {
		if (this.isContains(key)) {
			return caches.get(key);
		}
		return null;
	}

	// 获取对应缓存

	public Object getCacheDataByKey(String key) {
		if (this.isContains(key)) {
			return caches.get(key).getDatas();
		}
		return null;
	}

	// 获取所有缓存

	public Map<String, EntityCache<?>> getCacheAll() {
		return caches;
	}

	// 判断是否在缓存中

	public boolean isContains(String key) {
		return caches.containsKey(key);
	}

	// 清除所有缓存

	public void clearAll() {
		caches.clear();
	}

	// 清除对应缓存

	public void clearByKey(String key) {
		if (this.isContains(key)) {
			caches.remove(key);
		}
	}

	// 缓存是否超时失效

	public boolean isTimeOut(String key) {
		if (!caches.containsKey(key)) {
			return true;
		}
		EntityCache<?> cache = caches.get(key);
		long timeOut = cache.getTimeOut();
		long lastRefreshTime = cache.getLastRefeshTime();
		if (timeOut == 0 || System.currentTimeMillis() - lastRefreshTime >= timeOut) {
			return true;
		}
		return false;
	}

	// 获取所有key

	public Set<String> getAllKeys() {
		return caches.keySet();
	}

}
