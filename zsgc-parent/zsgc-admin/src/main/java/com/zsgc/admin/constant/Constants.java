package com.zsgc.admin.constant;

import java.util.Map;

import com.calanger.common.util.Config;
import com.google.common.collect.Maps;

public final class Constants {
    public static final String BASE_URL;
    public static final String BASE_DOMAIN;
    public static final String STATIC_DOMAIN;
    public static final String HOME_DOMAIN;

    public static final String COOKIE_NAME;
    public static final String COOKIE_DOMAIN;
    public static final String COOKIE_PATH;

    public static final String SECRET_KEY;
    public static final String USER_IDENTITY_KEY;
    public static final int SESSION_TIMEOUT;

    public static final String PASSWORD_KEY = Config.getConfig().get("admin.password.key");
    public static final String USER_PASSWORD_KEY = Config.getConfig().get("password.key");

    public static final String VALIDATE_CODE;

    public static final String UPYUN_DOMAIN;

    static {
        BASE_URL = Config.getConfig().get("admin.baseUrl");
        BASE_DOMAIN = Config.getConfig().get("admin.base.domain");
        STATIC_DOMAIN = Config.getConfig().get("admin.static.domain");
        HOME_DOMAIN = Config.getConfig().get("admin.home.domain");

        COOKIE_NAME = Config.getConfig().get("admin.cookie.name");
        COOKIE_DOMAIN = Config.getConfig().get("admin.cookie.domain");
        COOKIE_PATH = Config.getConfig().get("admin.cookie.path");

        SECRET_KEY = Config.getConfig().get("admin.secret.key");
        USER_IDENTITY_KEY = Config.getConfig().get("admin.user.identity.key");
        SESSION_TIMEOUT = Integer.parseInt(Config.getConfig().get("admin.session.timeout"));
        VALIDATE_CODE = "_zsgc_admin_code";

        UPYUN_DOMAIN = Config.getConfig().get("upyun.base.domain");
    }

    public static final int PAGE_SIZE = 12; // 分页数据条数

    /** 充值、提现 */
    public static final Map<String, String> PAY_CHANNEL_MAP = Maps.newHashMap(); // 支付渠道
    public static final Map<String, String> CLIENT_TYPE_MAP = Maps.newHashMap(); // 来源
    public static final Map<String, String> BALANCEPAY_STATUS_MAP = Maps.newHashMap(); // 充值状态
    public static final Map<String, String> WITHDRAW_STATUS_MAP = Maps.newHashMap(); // 提现状态
    static {
        PAY_CHANNEL_MAP.put("zhifubao", "支付宝");
        PAY_CHANNEL_MAP.put("weixin", "微信");

        CLIENT_TYPE_MAP.put("1", "pc");
        CLIENT_TYPE_MAP.put("2", "android");
        CLIENT_TYPE_MAP.put("3", "ios");

        BALANCEPAY_STATUS_MAP.put("0", "未支付");
        BALANCEPAY_STATUS_MAP.put("1", "充值成功");
        BALANCEPAY_STATUS_MAP.put("2", "充值失败");

        WITHDRAW_STATUS_MAP.put("0", "未处理");
        WITHDRAW_STATUS_MAP.put("1", "提现成功");
        WITHDRAW_STATUS_MAP.put("2", "提现失败");
        WITHDRAW_STATUS_MAP.put("3", "打款中");
    }

    /**  */
    public static final Map<String, String> INVEST_STATUS = Maps.newHashMap(); // 支付状态
    public static final Map<String, String> CASH_TYPE = Maps.newHashMap(); // 资金流水状态
    public static final Map<String, String> MODULE_TYPE = Maps.newHashMap(); // 系统模块类型
    public static final Map<String, String> MEMBER_TYPE = Maps.newHashMap();
    static {
        INVEST_STATUS.put("0", "未支付");
        INVEST_STATUS.put("1", "支付成功");
        INVEST_STATUS.put("2", "支付失败");


        CASH_TYPE.put("-1", "红包抵用");
        CASH_TYPE.put("1", "充值");
        CASH_TYPE.put("2", "提现成功");
        CASH_TYPE.put("21", "提现冻结");
        CASH_TYPE.put("22", "提现解冻");
        CASH_TYPE.put("3", "购买项目");
        
        MODULE_TYPE.put("0", "权限管理");
        MODULE_TYPE.put("1", "系统设置");
        MODULE_TYPE.put("2", "资金管理");
        MODULE_TYPE.put("3", "用户管理");
        MODULE_TYPE.put("9", "内容管理");

        MEMBER_TYPE.put("0", "体验金");
        MEMBER_TYPE.put("1", "抵用红包");
        MEMBER_TYPE.put("2", "交易后提现");
        MEMBER_TYPE.put("3", "可直接提现");
    }

    /** 加减币 */
    public static final Map<String, String> ADD_SUB_COIN_TYPE = Maps.newHashMap(); // 加减币类型
    public static final Map<String, String> ADD_SUB_COIN_STATUS = Maps.newHashMap(); // 加减币状态
    static {
        ADD_SUB_COIN_TYPE.put("1", "银行转账");
        ADD_SUB_COIN_TYPE.put("2", "支付宝");
        ADD_SUB_COIN_TYPE.put("3", "其他");

        ADD_SUB_COIN_STATUS.put("0", "待审核");
        ADD_SUB_COIN_STATUS.put("1", "审核成功");
        ADD_SUB_COIN_STATUS.put("2", "审核失败");
    }

    
    public static final int[] OPTIONAL_BANK_ID = {1, 2, 3, 7, 9, 12};

    public static final String TRY_TRANSFER_KEY = "Ysyhl9t@Flzx3000c#";
    public static final String SERVICE_DOMAIN = Config.getConfig().get("service.domain");
	public static final String MOBILE_BASEURL = Config.getConfig().get("mobile.baseUrl");

    public static final String SIGN_KEY = "DJFHJ34556#@$ghg$%^&DFG561"; // MD5加密秘钥

    public static final String GEXINBAO_MERCHANT_NO = Config.getConfig().get("gexinbao.merchantNo");
    public static final String GEXINBAO_MERCHANT_KEY = Config.getConfig().get("gexinbao.merchantKey");
    public static final String GEXINBAO_IDENTITY_BANKCARD4CHECK = Config.getConfig().get("gexinbao.identity.bankCard4Check");

    // ot_admin_action column `type`
    public static final Map<String, String> ACTION_TYPES = Maps.newHashMap();
    static {
        ACTION_TYPES.put("1", "主菜单（无URL）");
        ACTION_TYPES.put("2", "次菜单（无URL）");
        ACTION_TYPES.put("3", "叶子菜单");
        ACTION_TYPES.put("4", "固有操作(增删改)");
        ACTION_TYPES.put("5", "其它请求");
    }

    private Constants() {
    }
}
