package com.zsgc.admin.util;

public final class IpLongUtils {
    /**
     * 把IP转换成Long类型数据
     * @param ip
     * @return
     */
    public static Long getIpToLong(String ip) {
        String[] ipStr = ip.split("\\.");
        return 256 * 256 * 256 * Long.parseLong(ipStr[0])
                + 256 * 256 * Long.parseLong(ipStr[1])
                + 256 * Long.parseLong(ipStr[2])
                + Long.parseLong(ipStr[3]);
    }
    /**
     * @Title: IpLongUtils.java
     * @Description: long类型的IP转为String
     * @version: V1.0 
     * @param longIp
     * @return
     */
    public static String getIpToStr(long longIp){
        StringBuffer sb = new StringBuffer("");  
        //直接右移24位  
        sb.append(String.valueOf((longIp >>> 24)));  
        sb.append(".");  
        //将高8位置0，然后右移16位  
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));  
        sb.append(".");  
        //将高16位置0，然后右移8位  
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));  
        sb.append(".");  
        //将高24位置0  
        sb.append(String.valueOf((longIp & 0x000000FF)));  
        return sb.toString();  
    }
}
