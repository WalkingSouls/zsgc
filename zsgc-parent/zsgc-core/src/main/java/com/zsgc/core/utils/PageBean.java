package com.zsgc.core.utils;


import java.util.List;

public class PageBean<T> {
	
    private int pageNum;    //当前页
    
    private int pageSize;    //每页显示的数据条数。
    
    private int totalRecord;    //总的记录条数。
  
    private int totalPage;    //总页数
    
    private List<T> list;
        
	public PageBean(int pageNum,int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
	
	public void totalPage(){
		 if(totalRecord%pageSize==0){
	          
	            this.totalPage = totalRecord / pageSize;
	        }else{
	            
	            this.totalPage = totalRecord / pageSize +1;
	        }
	}
	
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
