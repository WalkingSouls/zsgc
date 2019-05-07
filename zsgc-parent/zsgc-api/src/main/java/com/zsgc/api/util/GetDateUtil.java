package com.zsgc.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDateUtil {
	/** 
     * 默认日期格式 
     */  
    public static String DEFAULT_FORMAT = "yyyy-MM-dd"; 
    public static String STRING_DATE_FORMAT = "yyyyMMddHHmmss";  
    public static String MONTH_FORMAT = "yyyy-MM";  
    
    public static Calendar cal = Calendar.getInstance();

    //当前年
    public static int YEAR = cal.get(Calendar.YEAR);
    //当前月
    public static int month = (cal.get(Calendar.MONTH))+1;
    //当前月的第几天：即当前日
    public static int DAY_OF_MONTH = cal.get(Calendar.DAY_OF_MONTH);
    //当前时：HOUR_OF_DAY-24小时制；HOUR-12小时制
    public static int HOUR_OF_DAY = cal.get(Calendar.HOUR_OF_DAY);
    //当前分
    public static int MINUTE = cal.get(Calendar.MINUTE);
    //当前秒
    public static int SECOND = cal.get(Calendar.SECOND);
    //0-上午；1-下午
    public static  int AM_PM = cal.get(Calendar.AM_PM);
    //当前年的第几周
    public static int WEEK_OF_YEAR = cal.get(Calendar.WEEK_OF_YEAR);
    //当前月的第几周
    public static int WEEKOFMONTH = cal.get(Calendar.WEEK_OF_MONTH);
    //当前年的第几天
    public static int DAYOFYEAR = cal.get(Calendar.DAY_OF_YEAR);
    
    
      
    /** 
     * 格式化日期 
     * @param date 日期对象 
     * @return String 日期字符串 
     */  
    public static String formatDate(Date date){  
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);  
        String sDate = f.format(date);  
        return sDate;  
    }  
    
    /** 
     * 格式化日期 
     * @param date 日期对象 
     * @return String 年-月字符串 
     */  
    public static String MonthformatDate(Date date){  
        SimpleDateFormat f = new SimpleDateFormat(MONTH_FORMAT);  
        String sDate = f.format(date);  
        return sDate;  
    } 
    /** 
     * 格式化日期 
     * @param date 日期字符串 
     * @return Date 日期 
     */  
    public static Date formatString(String date){  
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);  
        Date sDate = null;
		try {
			sDate = f.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        return sDate;  
    }  
    /** 
     * 格式化不带杠日期 
     * @param string 日期字符串 
     * @return Date 日期 
     */  
    public static Date StringformatDate(String date){  
        SimpleDateFormat f = new SimpleDateFormat(STRING_DATE_FORMAT);  
        Date sDate = null;
		try {
			sDate = f.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        return sDate;  
    } 
    /*
     * 
    */
    /** 
     * 获取当年的第一天 
     * @param year 
     * @return 
     */  
    public static Date getCurrYearFirst(){  
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearFirst(currentYear);  
    }  
      
    /** 
     * 获取当年的最后一天 
     * @param year 
     * @return 
     */  
    public static Date getCurrYearLast(){  
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearLast(currentYear);  
    }  
    /** 
     * 获取当月第一天
     * @param 
     * @return Date 
     */  
    public static Date getMonthfirst(){  
    	Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        Date first = c.getTime();
        return first;
    } 
    /** 
     * 获取当月最后一天
     * @param 
     * @return Date 
     */  
    public static Date getMonthlast(){  
    	Calendar c = Calendar.getInstance();    
    	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
        Date last = c.getTime(); 
        return last;
    } 
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return currYearLast;  
    } 
    /** 
     * 获取某天 
     * @param n 动态参数 
     * @return String 
     */  
    public static String getTime(int n){  
		Date todayDate = new Date();
		Calendar Date = Calendar.getInstance();
		Date.setTime(todayDate);
		Date.add(Calendar.DATE, n);;
         String day=formatDate(Date.getTime());
        return day;  
    }
    /** 
     * 获取某年月 
     * @param n 动态参数 
     * @return String 
     */  
    public static String getMonth(int n){  
		Date todayDate = new Date();
		Calendar Date = Calendar.getInstance();
		Date.setTime(todayDate);
		Date.add(Calendar.MONTH, n);;
         String day=MonthformatDate(Date.getTime());
       // return day.substring(0, 7); 
         return day;
    }
}
