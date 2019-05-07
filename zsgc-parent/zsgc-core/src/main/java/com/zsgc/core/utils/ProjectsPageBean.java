package com.zsgc.core.utils;

public class ProjectsPageBean<T> extends PageBean<T>{
	
    public ProjectsPageBean(int pageNum, int pageSize) {
		super(pageNum, pageSize);
	}

	private int index; //偏移量
    
    private int collectionTotal; //采集总数

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCollectionTotal() {
		return collectionTotal;
	}

	public void setCollectionTotal(int collectionTotal) {
		this.collectionTotal = collectionTotal;
	}

}
