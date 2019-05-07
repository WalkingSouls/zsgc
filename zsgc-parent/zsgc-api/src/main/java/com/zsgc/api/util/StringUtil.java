package com.zsgc.api.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aliyuncs.utils.StringUtils;

public final class StringUtil extends StringUtils{
    private static final String mobile_reg = "^1[346578][0-9]{9}$";

    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖" };
    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元",
            "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
            "佰", "仟" };
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

    /**
     * 显示姓，隐藏名字
     * @param name 姓名
     * @return 被局部隐藏后的姓名
     */
    public static String maskName(String name){
        StringBuilder sb = new StringBuilder();
        sb.append(name.substring(0,1));
        for(int i = 0; i < name.length()-1; i++)
            sb.append("*");
        return sb.toString();
    }

    /**
     * 局部隐藏银行卡号，只显示前4后4
     * @param bankCardNo 银行卡号
     * @return 被局部隐藏后的银行卡号
     */
    public static String maskBankCardNo(String bankCardNo){
        StringBuilder sb = new StringBuilder();
        sb.append(bankCardNo.substring(0,4));
        for(int i = 0;i<bankCardNo.length()-10;i++)
            sb.append("*");
        sb.append(bankCardNo.substring(bankCardNo.length()-4,bankCardNo.length()));
        return sb.toString();
    }

    /**
     * 隐藏企业中间部分
     * @param name 企业名称
     * @return 被局部隐藏后的姓名
     */
    public static String maskCompanyName(String name){
        StringBuilder sb = new StringBuilder();
        sb.append(name.substring(0,2));
        for(int i = 0; i < name.length()-5; i++){
            sb.append("*");
        }
        sb.append(name.substring(name.length()-4,name.length()));
        return sb.toString();
    }

    /**
     * 局部隐藏手机号位，隐藏从第4位到第7位
     * @param mobileNo 传入的手机号码
     * @return 被局部隐藏后的手机号码
     */
    public static String maskMobileNo(String mobileNo){       
        StringBuilder sb = new StringBuilder();
        sb.append(mobileNo.substring(0, 3)).append("****").append(mobileNo.substring(7));
        return sb.toString();      
    }
    
    /**
     * 局部隐藏身份证号位，隐藏生日部分
     * @param idCardNo 传入的身份证号
     * @return 被局部隐藏后的身份证号码
     */
    public static String maskIDCardNo(String idCardNo){
        StringBuilder sb = new StringBuilder();
        sb.append(idCardNo.substring(0,6)).append("********").append(idCardNo.substring(14));
        return sb.toString();
    }
    /**
     * 局部隐藏身份证号位，隐藏生日部分
     * @param idCardNo 传入的身份证号
     * @return 被局部隐藏后的身份证号码
     */
    public static String maskCardName(String name){
        StringBuilder sb = new StringBuilder(name);
        sb.replace(1, 2, "*");
        return sb.toString();
    }

    /**
     * 验证手机号码
     * @param mobiles
     */
    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile(mobile_reg);
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    
    /**
     * 验证是否是银行卡
     */
    public static boolean isBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }
    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeCardId
     * @return
     */
     public static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;           
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
     }

     /**
      * 把输入的金额转换为汉语中人民币的大写
      * 
      * @param numberOfMoney
      *            输入的金额
      * @return 对应的汉语大写
      */
     public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
         StringBuffer sb = new StringBuffer();
         // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
         // positive.
         int signum = numberOfMoney.signum();
         // 零元整的情况
         if (signum == 0) {
             return CN_ZEOR_FULL;
         }
         //这里会进行金额的四舍五入
         long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                 .setScale(0, 4).abs().longValue();
         // 得到小数点后两位值
         long scale = number % 100;
         int numUnit = 0;
         int numIndex = 0;
         boolean getZero = false;
         // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
         if (!(scale > 0)) {
             numIndex = 2;
             number = number / 100;
             getZero = true;
         }
         if ((scale > 0) && (!(scale % 10 > 0))) {
             numIndex = 1;
             number = number / 10;
             getZero = true;
         }
         int zeroSize = 0;
         while (true) {
             if (number <= 0) {
                 break;
             }
             // 每次获取到最后一个数
             numUnit = (int) (number % 10);
             if (numUnit > 0) {
                 if ((numIndex == 9) && (zeroSize >= 3)) {
                     sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                 }
                 if ((numIndex == 13) && (zeroSize >= 3)) {
                     sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                 }
                 sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                 sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                 getZero = false;
                 zeroSize = 0;
             } else {
                 ++zeroSize;
                 if (!(getZero)) {
                     sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                 }
                 if (numIndex == 2) {
                     if (number > 0) {
                         sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                     }
                 } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                     sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                 }
                 getZero = true;
             }
             // 让number每次都去掉最后一个数
             number = number / 10;
             ++numIndex;
         }
         // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
         if (signum == -1) {
             sb.insert(0, CN_NEGATIVE);
         }
         // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
         if (!(scale > 0)) {
             sb.append(CN_FULL);
         }
         return sb.toString();
     }

     public static void main(String[] args) {
         double money = 1500000;
         BigDecimal numberOfMoney = new BigDecimal(money);
         String s = number2CNMontrayUnit(numberOfMoney);
         System.out.println("你输入的金额为：【"+ money +"】   #--# [" +s.toString()+"]");
     }

     /**
      * 显示姓加性别，隐藏名字
      * @param name 姓名
      * @param idCard 身份证号
      * @return 被局部隐藏后的姓名
      */
     public static String maskNameByIdCard(String name, String idCard){
         StringBuilder sb = new StringBuilder();
         try {
             sb.append(name.substring(0,1));

             String sex;  
             if (Integer.parseInt(idCard.substring(16, 17)) % 2 == 0) {// 判断性别  
                 sex = "女士";  
             } else {  
                 sex = "先生";  
             }
             sb.append(sex);
         } catch (Exception e) {}

         return sb.toString();
     }
}
