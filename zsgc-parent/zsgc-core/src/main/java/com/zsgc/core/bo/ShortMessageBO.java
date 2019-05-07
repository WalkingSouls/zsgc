package com.zsgc.core.bo;

import com.calanger.common.bo.BO;
import com.zsgc.core.model.ShortMessage;

public interface ShortMessageBO extends BO<ShortMessage, java.lang.Integer> {
    void insertShortMessage(ShortMessage shortMessage);
    void updateMessageTimes(ShortMessage shortMessageVO);

    /**
     * 添加发送的短信
     */
    void add(ShortMessage shortMessage);
    /**
     * 验证短信验证码
     * 
     * @param mobile 手机号码
     * @param code   短信验证码
     * @param type   1注册  2忘记登录密码 3绑定银行卡 4投标 5提现 6设置交易密码 7忘记/修改交易密码 8充值 9修改手机号码
     * @return
     *      0 验证通过
     *      1000 请重新获取验证码
     *      1001 验证码错误
     *      1002 验证码失效
     */
    int isValidCode(String mobile, String code, Integer type);
}
