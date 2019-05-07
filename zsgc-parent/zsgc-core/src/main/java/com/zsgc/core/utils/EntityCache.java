package com.zsgc.core.utils;

public class EntityCache<T> {
    private  T datas;
     //   设置数据失效时间,为0表示永不失效
    private  long timeOut;
     //最后刷新时间
    private  long lastRefeshTime;
    //版本
    private int version;
    
    public EntityCache(){
    	
    }
	public EntityCache(T datas, long timeOut, int version , long lastRefeshTime) {
		this.datas = datas;
		this.timeOut = timeOut;
		this.lastRefeshTime = lastRefeshTime;
		this.version = version;
	}
	
	public T getDatas() {
		return datas;
	}
	public void setDatas(T datas) {
		this.datas = datas;
	}
	public long getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}
	public long getLastRefeshTime() {
		return lastRefeshTime;
	}
	public void setLastRefeshTime(long lastRefeshTime) {
		this.lastRefeshTime = lastRefeshTime;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	} 
	
}

