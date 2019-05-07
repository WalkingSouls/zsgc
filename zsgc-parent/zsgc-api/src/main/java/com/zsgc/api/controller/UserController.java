package com.zsgc.api.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.calanger.common.dao.Expressions;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.calanger.common.util.RandomUtils;
import com.calanger.common.web.util.RequestUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zsgc.api.constant.Constants;
import com.zsgc.api.dto.ApiResult;
import com.zsgc.api.dto.UserIdentity;
import com.zsgc.api.util.HttpUtils;
import com.zsgc.api.util.StringUtil;
import com.zsgc.api.util.UserIdentityUtils;
import com.zsgc.core.bo.ActivityAddressBO;
import com.zsgc.core.bo.AppTokenBO;
import com.zsgc.core.bo.BankBO;
import com.zsgc.core.bo.FollowBO;
import com.zsgc.core.bo.MemberCheckBO;
import com.zsgc.core.bo.MybankBO;
import com.zsgc.core.bo.ShortMessageBO;
import com.zsgc.core.bo.UcenterMemberBO;
import com.zsgc.core.model.ActivityAddress;
import com.zsgc.core.model.AppToken;
import com.zsgc.core.model.Bank;
import com.zsgc.core.model.Follow;
import com.zsgc.core.model.MemberCheck;
import com.zsgc.core.model.Mybank;
import com.zsgc.core.model.ShortMessage;
import com.zsgc.core.model.UcenterMember;

import org.springframework.web.multipart.MultipartFile;

/**
 * 用户
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    

    @Autowired
    private AppTokenBO appTokenBO;
    @Autowired
    private UcenterMemberBO ucenterMemberBO;
    @Autowired
    private ShortMessageBO shortMessageBO;
    @Autowired
    private MemberCheckBO memberCheckBO;
    @Autowired
    private MybankBO mybankBO;
    @Autowired
    private BankBO bankBO;
    @Autowired
    private ActivityAddressBO activityAddressBO;
    @Autowired
    private FollowBO followBO;
    /**
     * 用户信息
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Map<String, Object>> info() {
        UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();
//        UserIdentity userIdentity = new UserIdentity();
//        userIdentity.setId(6336);
      if(userIdentity==null){
          return new ApiResult<Map<String, Object>>(101, "用户不能为空，请登录！");
      }
        int userId = userIdentity.getId();
        Map<String, Object> data = new LinkedHashMap<String, Object>();

        // 个人基本信息
        UcenterMember ucenterMember = ucenterMemberBO.get(userId);
        data.put("userId", ucenterMember.getId());
        data.put("username", ucenterMember.getUsername());
        data.put("mobile", ucenterMember.getMobile());
        data.put("avatarPath", Strings.isNullOrEmpty(ucenterMember.getAvatarPath()) ? "" : ucenterMember.getAvatarPath());
        data.put("email", Strings.isNullOrEmpty(ucenterMember.getEmail()) ? "" : ucenterMember.getEmail());
        data.put("regIp", Strings.isNullOrEmpty(ucenterMember.getRegIp()) ? "" : ucenterMember.getRegIp());
        data.put("lastLoginTime", ucenterMember.getLastLoginTime() == null ? "" : DateFormatUtils.format(ucenterMember.getLastLoginTime(), "yyyy-MM-dd HH:mm:ss"));
        data.put("lastLoginIp", Strings.isNullOrEmpty(ucenterMember.getLastLoginIp()) ? "" : ucenterMember.getLastLoginIp());
        data.put("status",  1 == ucenterMember.getStatus() ? "正常":0 == ucenterMember.getStatus()?"禁用":"删除");
        data.put("sex", ucenterMember.getSex() == null ? "" : 1 == ucenterMember.getSex() ? "男" : "女");
        data.put("birthday", ucenterMember.getBirthday() == null ? "" : DateFormatUtils.format(ucenterMember.getBirthday(), "yyyy-MM-dd"));
        data.put("qq", Strings.isNullOrEmpty(ucenterMember.getQq()) ? "" : ucenterMember.getQq());
        data.put("score", ucenterMember.getScore());
        data.put("experience", ucenterMember.getExperience());
        data.put("isAuthenticated", ucenterMember.getIsAuthenticated());
        data.put("isCheck", ucenterMember.getIsCheck());
        data.put("invitationCode", ucenterMember.getInvitationCode());
        data.put("signature", ucenterMember.getSignature());
        data.put("type", ucenterMember.getType());
        Follow condition = new Follow();
        condition.setUserId(userId);
        int followsum = followBO.count(condition);
        data.put("followsum", followsum);
        Follow condition2 = new Follow();
        condition2.setFollowUserId(userId);
        int followedsum = followBO.count(condition2);
        data.put("followedsum", followedsum);

        ActivityAddress activityVO=new ActivityAddress();
        activityVO.setUserId(userId);
        ActivityAddress activityAddress=activityAddressBO.get(activityVO);
        if(activityAddress!=null){
            data.put("recipients",activityAddress.getRecipients());
            data.put("addressDetail",activityAddress.getAddressDetail());
            data.put("contactNumber",activityAddress.getContactNumber());
        }else {
            data.put("recipients","");
            data.put("addressDetail","");
            data.put("contactNumber","");
        }
        return new ApiResult<Map<String, Object>>(0, "success", data);
    }
    
    
    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getInfoByuserId", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Map<String, Object>> getInfo(Integer userId) {
        Map<String, Object> data = new LinkedHashMap<String, Object>();

     // 个人基本信息
        UcenterMember ucenterMember = ucenterMemberBO.get(userId);
        data.put("userId", ucenterMember.getId());
        data.put("username", ucenterMember.getUsername());
        data.put("mobile", ucenterMember.getMobile());
        data.put("avatarPath", Strings.isNullOrEmpty(ucenterMember.getAvatarPath()) ? "" : ucenterMember.getAvatarPath());
        data.put("email", Strings.isNullOrEmpty(ucenterMember.getEmail()) ? "" : ucenterMember.getEmail());
        data.put("regIp", Strings.isNullOrEmpty(ucenterMember.getRegIp()) ? "" : ucenterMember.getRegIp());
        data.put("lastLoginTime", ucenterMember.getLastLoginTime() == null ? "" : DateFormatUtils.format(ucenterMember.getLastLoginTime(), "yyyy-MM-dd HH:mm:ss"));
        data.put("lastLoginIp", Strings.isNullOrEmpty(ucenterMember.getLastLoginIp()) ? "" : ucenterMember.getLastLoginIp());
        data.put("status",  1 == ucenterMember.getStatus() ? "正常":0 == ucenterMember.getStatus()?"禁用":"删除");
        data.put("sex", ucenterMember.getSex() == null ? "" : 1 == ucenterMember.getSex() ? "男" : "女");
        data.put("birthday", ucenterMember.getBirthday() == null ? "" : DateFormatUtils.format(ucenterMember.getBirthday(), "yyyy-MM-dd"));
        data.put("qq", Strings.isNullOrEmpty(ucenterMember.getQq()) ? "" : ucenterMember.getQq());
        data.put("score", ucenterMember.getScore());
        data.put("experience", ucenterMember.getExperience());
        data.put("isAuthenticated", ucenterMember.getIsAuthenticated());
        data.put("isCheck", ucenterMember.getIsCheck());
        data.put("invitationCode", ucenterMember.getInvitationCode());
        data.put("signature", ucenterMember.getSignature());
        data.put("type", ucenterMember.getType());
        Follow condition = new Follow();
        condition.setUserId(userId);
        int followsum = followBO.count(condition);
        data.put("followsum", followsum);
        Follow condition2 = new Follow();
        condition2.setFollowUserId(userId);
        int followedsum = followBO.count(condition2);
        data.put("followedsum", followedsum);

        ActivityAddress activityVO=new ActivityAddress();
        activityVO.setUserId(userId);
        ActivityAddress activityAddress=activityAddressBO.get(activityVO);
        if(activityAddress!=null){
            data.put("recipients",activityAddress.getRecipients());
            data.put("addressDetail",activityAddress.getAddressDetail());
            data.put("contactNumber",activityAddress.getContactNumber());
        }else {
            data.put("recipients","");
            data.put("addressDetail","");
            data.put("contactNumber","");
        }
        return new ApiResult<Map<String, Object>>(0, "success", data);
    }
    /**
     * 注册
     * @param mobile 手机号码
     * @param password 密码
     * @param code 短信验证码
     * @param registerFrom 注册来自
     * @param deviceId 设备ID
     * @param deviceName 设备名称
     * @param osName 系统名
     * @param sid 渠道
     * @param packageName 包名
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> register(String mobile, String password, String code, Integer registerFrom,
            String deviceId, String deviceName, String osName, String sid, String packageName,String area,Integer isCheck,@RequestParam(required = false) String invitationCode) {
        if (Strings.isNullOrEmpty(mobile)) {
            return new ApiResult<Map<String, Object>>(101, "手机号码错误");
        }
        if (Strings.isNullOrEmpty(password)) {
            return new ApiResult<Map<String, Object>>(102, "密码错误");
        }
        if (Strings.isNullOrEmpty(code)) {
            return new ApiResult<Map<String, Object>>(103, "验证码错误");
        }

        int isValidCode = shortMessageBO.isValidCode(mobile, code, 1);
        if (isValidCode != 0) {
            switch(isValidCode) {
            case 1000:
                return new ApiResult<Map<String, Object>>(201, "请重新获取验证码");
            case 1001:
                return new ApiResult<Map<String, Object>>(202, "验证码错误");
            case 1002:
                return new ApiResult<Map<String, Object>>(203, "验证码已失效");
            }
        }

        UcenterMember ucenterMemberVO = new UcenterMember();
        ucenterMemberVO.setMobile(mobile);
        UcenterMember ucenterMember = ucenterMemberBO.get(ucenterMemberVO);
        if (ucenterMember != null) {
            return new ApiResult<Map<String, Object>>(204, "该手机号码已注册");
        }

        UcenterMember entity = new UcenterMember();
        entity.setUsername(mobile);
        entity.setMobile(mobile);
        entity.setPassword(DigestUtils.sha1Hex(password));
        entity.setRegIp(RequestUtils.getClientIp());
        entity.setStatus(1);
        entity.setArea(area);
        entity.setRegisterFrom(registerFrom);
        entity.setType(0);
        if(invitationCode.isEmpty()){
        	entity.setScore(20);
        }else{
        	UcenterMember ucenter= new UcenterMember();
        	ucenter.setInvitationCode(invitationCode);
        	UcenterMember ucenterMember2=ucenterMemberBO.get(ucenter);
        	if(ucenterMember2 !=null){
        		entity.setScore(30);
        		UcenterMember ucenterMemberVO2=new UcenterMember();
        		int score =ucenterMember2.getScore()+20;
        		ucenterMemberVO2.setScore(score);
        		ucenterMemberBO.update(ucenterMemberVO2,ucenterMember2);
        	}
        	
        }
        if (!StringUtils.isEmpty(sid)) {
            entity.setSid(sid);
        }
        if (!StringUtils.isEmpty(packageName)) {
            entity.setPackageName(packageName);
        }
        
        LOGGER.info("=========注册用户===="+mobile);
        ucenterMemberBO.register(entity);

        AppToken appTokenEntity = new AppToken();
        appTokenEntity.setToken(RandomUtils.getRandomUUID(false));
        appTokenEntity.setUserId(entity.getId());
        appTokenEntity.setMobile(entity.getMobile());
        appTokenEntity.setDeviceId(deviceId);
        appTokenEntity.setDeviceName(deviceName);
        appTokenEntity.setOsName(osName);
        appTokenEntity.setLoginIp(RequestUtils.getClientIp());
        appTokenEntity.setDisabled(0);

        appTokenBO.create(appTokenEntity);

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("userId", entity.getId());
        data.put("mobile", entity.getMobile());
        data.put("token", appTokenEntity.getToken());


        return new ApiResult<Map<String, Object>>(0, "success", data);
    }
    
    /**
     * 设置个性签名
     * @param mobile 手机号码
     * @param signature 个性签名
     * @return
     */
    @RequestMapping(value = "/setSignature", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> setSignature(String mobile, String signature) {
        if (Strings.isNullOrEmpty(mobile)) {
            return new ApiResult<Map<String, Object>>(101, "手机号码错误");
        }
        if (Strings.isNullOrEmpty(signature)) {
            return new ApiResult<Map<String, Object>>(105, "个性签名错误");
        }


        UcenterMember ucenterMemberVO = new UcenterMember();
        ucenterMemberVO.setMobile(mobile);
        UcenterMember ucenterMember = ucenterMemberBO.get(ucenterMemberVO);
        if ( null == ucenterMember) {
            return new ApiResult<Map<String, Object>>(208, "对不起找不到用户");
        }
        if(ucenterMember.getSignature().isEmpty()){
        	int score = ucenterMember.getScore()+5;
        	ucenterMember.setScore(score);
        }
        ucenterMember.setSignature(signature);
        UcenterMember condition = new UcenterMember();
        condition.setMobile(mobile);
        ucenterMemberBO.update(ucenterMember, condition);
        return new ApiResult<Map<String, Object>>(0, "success");
    }

    /**
     * 登录
     * @param mobile 手机号
     * @param password 密码
     * @param deviceId 设备ID
     * @param deviceName 设备名称
     * @param osName 系统名称
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> login(String mobile, String password, String deviceId, String deviceName, String osName, HttpServletRequest request) {
        String osversion = (String) request.getAttribute("osversion");
        String osname = (String) request.getAttribute("osname");
        LOGGER.info("app osversion:" + osversion + ", osname:" + osname);

        if (StringUtils.isEmpty(mobile)) {
            return new ApiResult<Map<String, Object>>(101, "手机号不能为空");
        }
        if (!StringUtil.isMobileNO(mobile)) {
            return new ApiResult<Map<String, Object>>(102, "手机号码错误");
        }
        if (StringUtils.isEmpty(password)) {
            return new ApiResult<Map<String, Object>>(103, "密码不能为空");
        }

        LOGGER.info("=============登陆手机号码："+mobile);
        UcenterMember ucenterMemberVO = new UcenterMember();
        ucenterMemberVO.setMobile(mobile);
        UcenterMember ucenterMember = ucenterMemberBO.get(ucenterMemberVO);
        if (ucenterMember == null) {
                return new ApiResult<Map<String, Object>>(201, "手机号或密码错误");
        }

        if (ucenterMember.getStatus() != 1) {
            return new ApiResult<Map<String, Object>>(201, "该账户已被禁用，请联系客服");
        }
        if (!StringUtils.equals(ucenterMember.getPassword(), DigestUtils.sha1Hex(password))) {
            return new ApiResult<Map<String, Object>>(201, "手机号或密码错误");
        }
        
        AppToken appTokenEntity = new AppToken();
        appTokenEntity.setToken(RandomUtils.getRandomUUID(false));
        appTokenEntity.setUserId(ucenterMember.getId());
        appTokenEntity.setMobile(ucenterMember.getMobile());
        appTokenEntity.setDeviceId(deviceId);
        appTokenEntity.setDeviceName(deviceName);
        appTokenEntity.setOsName(osName);
        appTokenEntity.setLoginIp(RequestUtils.getClientIp());
        appTokenEntity.setDisabled(0);

        appTokenBO.create(appTokenEntity);

        UcenterMember ucenterMemberEntity = new UcenterMember();
        ucenterMemberEntity.setLastLoginTime(new Date());
        ucenterMemberEntity.setLastLoginIp(appTokenEntity.getLoginIp());
        ucenterMemberEntity.increment("login", 1);

        ucenterMemberBO.update(ucenterMemberEntity, ucenterMember.getId());

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("mobile", ucenterMember.getMobile());
        data.put("token", appTokenEntity.getToken());
        data.put("userId", appTokenEntity.getUserId());

        return new ApiResult<Map<String, Object>>(0, "success", data);
    }

    
    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> logout(HttpServletRequest request) {
        UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();
        if (userIdentity != null) {
            appTokenBO.off(userIdentity.getId());
        }

        request.setAttribute(Constants.USER_IDENTITY_KEY, null);

        return new ApiResult<Map<String, Object>>(0, "success");
    }

    /**
     * 忘记密码
     * @param mobile 手机号
     * @param password 密码
     * @param code 短信验证码
     * @return
     */
    @RequestMapping(value = "/password/forget", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> forget(String mobile, String password, String code) {
        if (StringUtils.isEmpty(mobile)) {
            return new ApiResult<Map<String, Object>>(101, "手机号码不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return new ApiResult<Map<String, Object>>(102, "密码不能为空");
        }
        if (StringUtils.isEmpty(code)) {
            return new ApiResult<Map<String, Object>>(103, "短信验证码不能为空");
        }

        UcenterMember condition = new UcenterMember();
        condition.setMobile(mobile);
        UcenterMember ucenterMember = ucenterMemberBO.get(condition);
        if (ucenterMember == null) {
            return new ApiResult<Map<String, Object>>(201, "手机号或密码错误");
        }
        if (ucenterMember.getStatus() == -1) {
            return new ApiResult<Map<String, Object>>(202, "你的账户已被删除");
        }
        if (ucenterMember.getStatus() == 0) {
            return new ApiResult<Map<String, Object>>(202, "你的账户已被禁用"); 
        }

        int isValidCode = shortMessageBO.isValidCode(mobile, code, 2);
        if (isValidCode != 0) {
            switch(isValidCode) {
            case 1000:
                return new ApiResult<Map<String, Object>>(203, "请重新获取验证码");
            case 1001:
                return new ApiResult<Map<String, Object>>(203, "验证码错误");
            case 1002:
                return new ApiResult<Map<String, Object>>(203, "验证码已失效");
            }
        }

        if (ucenterMember.getPassword().equals(DigestUtils.sha1Hex(password))) {
            return new ApiResult<Map<String, Object>>(204, "新密码与原密码一致");
        }

        UcenterMember entity = new UcenterMember();
        entity.setPassword(DigestUtils.sha1Hex(password));
        ucenterMemberBO.update(entity, ucenterMember.getId());

        return new ApiResult<Map<String, Object>>(0, "success");
    }

    /**
     * 重置密码
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return
     */
    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> reset(String oldPassword, String newPassword) {
        if (StringUtils.isEmpty(oldPassword)) {
            return new ApiResult<Map<String, Object>>(101, "旧密码不能为空");
        }
        if (StringUtils.isEmpty(newPassword)) {
            return new ApiResult<Map<String, Object>>(102, "新密码不能为空");
        }

        UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();
        int userId = userIdentity.getId();

        UcenterMember ucenterMember = ucenterMemberBO.get(userId);

        if (!ucenterMember.getPassword().equals(DigestUtils.sha1Hex(oldPassword))) {
            return new ApiResult<Map<String, Object>>(201, "原密码错误");
        }
        if (ucenterMember.getPassword().equals(DigestUtils.sha1Hex(newPassword))) {
            return new ApiResult<Map<String, Object>>(201, "新密码与原密码一致");
        }

        AppToken appTokenEntity = new AppToken();
        appTokenEntity.setToken(RandomUtils.getRandomUUID(false));
        appTokenEntity.setUserId(userId);
        appTokenEntity.setMobile(ucenterMember.getMobile());
        appTokenEntity.setLoginIp(RequestUtils.getClientIp());
        appTokenEntity.setDisabled(0);

        appTokenBO.create(appTokenEntity);

        UcenterMember entity = new UcenterMember();
        entity.setPassword(DigestUtils.sha1Hex(newPassword));
        ucenterMemberBO.update(entity, userId);

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("mobile", ucenterMember.getMobile());
        data.put("token", appTokenEntity.getToken());

        return new ApiResult<Map<String, Object>>(0, "success", data);
    }

    /**
     * 重置手机号码 - 验证旧手机号码
     * @param mobile 旧手机号码
     * @param code   旧手机短信验证码
     * @return
     */
    @RequestMapping(value = "/resetMobile/verification", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> verification(String mobile, String code,
            HttpServletRequest request) {
        if (!StringUtil.isMobileNO(mobile)) {
            return new ApiResult<Map<String, Object>>(100, "请输入正确的手机号码");
        }
        if (Strings.isNullOrEmpty(code)) {
            return new ApiResult<Map<String, Object>>(101, "短信验证码不能为空");
        }

        int isValidCode = shortMessageBO.isValidCode(mobile, code, 9);
        if (isValidCode != 0) {
            switch(isValidCode) {
            case 1000:
                return new ApiResult<Map<String, Object>>(201, "请重新获取验证码");
            case 1001:
                return new ApiResult<Map<String, Object>>(202, "验证码错误");
            case 1002:
                return new ApiResult<Map<String, Object>>(203, "验证码已失效");
            }
        }

        // 判断旧手机号码是否存在
        UcenterMember memberVO = new UcenterMember();
        memberVO.setId(UserIdentityUtils.getUserIdentity().getId());
        memberVO.setMobile(mobile);
        if (ucenterMemberBO.get(memberVO) == null) {
            return new ApiResult<Map<String, Object>>(300, "旧手机号错误");
        }

        return new ApiResult<Map<String, Object>>(0, "success");
    }

	/**
	 * 重置手机号
	 * @param mobile:用户输入的手机号
	 * @param code:验证码
	 */
	@RequestMapping(value = "/resetMobile", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Map<String, Object>> resetMobile(String mobile, String code,
													  HttpServletRequest request, HttpServletResponse response) {
		if (Strings.isNullOrEmpty(mobile) && !StringUtil.isMobileNO(mobile)) {
			return new ApiResult<Map<String, Object>>(100, "请输入正确的手机号码");
		}
		if (Strings.isNullOrEmpty(code)) {
			return new ApiResult<Map<String, Object>>(101, "短信验证码不能为空");
		}

		UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();

		int isValidCode = shortMessageBO.isValidCode(mobile, code, 10);
		if (isValidCode != 0) {
			switch(isValidCode) {
				case 1000:
					return new ApiResult<Map<String, Object>>(201, "请重新获取验证码");
				case 1001:
					return new ApiResult<Map<String, Object>>(202, "验证码错误");
				case 1002:
					return new ApiResult<Map<String, Object>>(203, "验证码已失效");
			}
		}

		// 判断新手机号码是否被使用
		UcenterMember memberVO = new UcenterMember();
		memberVO.setMobile(mobile);
		UcenterMember member = ucenterMemberBO.get(memberVO);
		if (member != null) {
			return new ApiResult<Map<String, Object>>(301, "该手机号码已被使用");
		}

		UcenterMember entity = new UcenterMember();
		entity.setUsername(mobile);
		entity.setMobile(mobile);
		ucenterMemberBO.update(entity, userIdentity.getId());

		// 退出登录
        if (userIdentity != null) {
            appTokenBO.off(userIdentity.getId());
        }
        request.setAttribute(Constants.USER_IDENTITY_KEY, null);

		return new ApiResult<Map<String, Object>>(0, "success");
	}

    /**
     * 实名认证
     * @param name 姓名
     * @param idCardNo 身份证号
     * @return
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> authenticate(String name, String idCardNo) {
        if (StringUtils.isEmpty(name)) {
            return new ApiResult<Map<String, Object>>(101, "姓名不能为空");
        }
        if (StringUtils.isEmpty(idCardNo)) {
            return new ApiResult<Map<String, Object>>(102, "身份证号码不能为空");
        }
        idCardNo = StringUtils.replace(idCardNo, " ", "");

        UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();

        MemberCheck memberCheckVO = new MemberCheck();
        memberCheckVO.and(Expressions.notIn("status", 2)); // 2 实名认证失败
        memberCheckVO.setIdCard(idCardNo);
        MemberCheck memberCheck = memberCheckBO.get(memberCheckVO);
        if (memberCheck != null) {
            return new ApiResult<Map<String, Object>>(201, "该身份证号已绑定其他账户");
        }

        // 实名认证接口
        Map<String, String> params = Maps.newHashMap();
        params.put("merchantNo", Constants.GEXINBAO_MERCHANT_NO);
        params.put("merchantKey", Constants.GEXINBAO_MERCHANT_KEY);
        params.put("name", name);
        params.put("idCardNo", idCardNo);

        String result = HttpUtils.doPost(Constants.GEXINBAO_IDENTITY_IDCHECK, params);
        JSONObject json = JSONObject.parseObject(result);

        String returnCode = json.getString("code");
        String returnMsg = json.getString("msg");
        LOGGER.info("实名认证接口返回：returnCode={}=returnMsg={}=", returnCode, returnMsg);

        if (!"0".equals(returnCode)) {
            if (!"0".equals(returnCode)) {
                if ("2000".equals(returnCode) || "2001".equals(returnCode)
                        || "2002".equals(returnCode) || "3000".equals(returnCode)) {
                    return new ApiResult<Map<String, Object>>(Integer.valueOf(returnCode), "认证错误，请联系客服");
                } else {
                    return new ApiResult<Map<String, Object>>(Integer.valueOf(returnCode), returnMsg);
                }
            }
        }

        JSONObject dataJson = json.getJSONObject("data");
        String dataCode = dataJson.getString("code");
        String dataMsg = dataJson.getString("msg");
        LOGGER.info("实名认证接口结果返回：dataCode={}=dataMsg={}=", dataCode, dataMsg);
        if (!"0".equals(dataCode)) {
            return new ApiResult<Map<String, Object>>(Integer.valueOf(dataCode), dataMsg);
        }

        MemberCheck entity = new MemberCheck();
        entity.setUid(userIdentity.getId());
        entity.setName(name.trim());
        entity.setIdCard(idCardNo.trim());
        entity.setStatus(1);
        entity.setAdminId(0);
        memberCheckBO.create(entity);

        return new ApiResult<Map<String, Object>>(0, "success");
    }
    
    /**
     * 身份认证
     * @param isCheck
     * @param checkType
     * @param organize
     * @param name
     * @param company
     * @param zone
     * @return
     */
	@RequestMapping(value = "/isCheck", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> isCheck(Integer isCheck,Integer checkType,String organize,String name,String company, String zone) {
    	UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();
//      UserIdentity userIdentity = new UserIdentity();
//      userIdentity.setId(6336);
      if(userIdentity==null){
          return new ApiResult<Map<String, Object>>(101, "用户不能为空，请登录！");
      }
        if (checkType == null) {
            return new ApiResult<Map<String, Object>>(101, "认证类型不能为空");
        }
        if (StringUtils.isEmpty(name)) {
            return new ApiResult<Map<String, Object>>(102, "姓名不能为空");
        }
        if(checkType == 2){
        	if (StringUtils.isEmpty(organize)) {
                return new ApiResult<Map<String, Object>>(103, "组织类型不能为空");
            }
        	if (StringUtils.isEmpty(company)) {
                return new ApiResult<Map<String, Object>>(104, "组织名不能为空");
            }
        }
        if (StringUtils.isEmpty(company)) {
            return new ApiResult<Map<String, Object>>(104, "公司名不能为空");
        }
        if (StringUtils.isEmpty(zone)) {
            return new ApiResult<Map<String, Object>>(105, "园区不能为空");
        }
		// 判断新手机号码是否被使用
		UcenterMember memberVO = new UcenterMember();
		memberVO.setId(userIdentity.getId());;
		UcenterMember member = ucenterMemberBO.get(memberVO);
		member.setIsCheck(0);
		member.setCheckType(checkType);
		member.setName(name);
		member.setOrganize(organize);
		member.setCompany(company);
		member.setZone(zone);
		ucenterMemberBO.update(member, memberVO);


        return new ApiResult<Map<String, Object>>(0, "success");
    }

    /**
     * 银行信息列表
     * @return
     */
    @RequestMapping(value = "/bank", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Map<String, Object>> bank() {
        Bank bankVO = new Bank();
        bankVO.setStatus(1);

        List<Bank> bankList = bankBO.find(bankVO);

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        if (!bankList.isEmpty()) {
            List<Map<String, Object>> bankDataList = Lists.newArrayList();
            for (Bank bank : bankList) {
                Map<String, Object> bankDataMap = Maps.newHashMap();
                bankDataMap.put("id", bank.getId());
                bankDataMap.put("name", bank.getName());
                bankDataMap.put("code", bank.getCode());
                bankDataMap.put("icon", Constants.UPYUN_BASE_URL + bank.getIcon());
                bankDataMap.put("backgroundImage", StringUtils.isEmpty(bank.getBackgroundImage()) ? "" : Constants.UPYUN_BASE_URL + bank.getBackgroundImage());
                bankDataMap.put("singleLimit", StringUtils.isEmpty(bank.getSingleLimit()) ? "" : bank.getSingleLimit());
                bankDataMap.put("dailyAccumulativeLimit", StringUtils.isEmpty(bank.getDailyAccumulativeLimit()) ? "" : bank.getDailyAccumulativeLimit());
                bankDataMap.put("monthlyAccumulativeLimit", StringUtils.isEmpty(bank.getMonthlyAccumulativeLimit()) ? "" : bank.getMonthlyAccumulativeLimit());
                bankDataMap.put("conditionsOfUsage", StringUtils.isEmpty(bank.getConditionsOfUsage()) ? "" : bank.getConditionsOfUsage());
                bankDataList.add(bankDataMap);
            }
            data.put("bankDataList", bankDataList);
        }

        return new ApiResult<Map<String, Object>>(0, "success", data);
    }

    /**
     * 绑定银行卡
     * @param bankId 银行ID
     * @param bankCardNo 银行卡号
     * @param mobile 预留手机号
     * @param code 短信验证码
     * @return
     */
    @RequestMapping(value = "/tied-card", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> tiedCard(String bankId, String bankCardNo, String mobile, String code) {
        if (StringUtils.isEmpty(bankId)) {
            return new ApiResult<Map<String, Object>>(101, "请选择银行");
        }
        if (StringUtils.isEmpty(bankCardNo)) {
            return new ApiResult<Map<String, Object>>(102, "银行卡号不能为空");
        }
        if (StringUtils.isEmpty(mobile)) {
            return new ApiResult<Map<String, Object>>(103, "预留手机号不能为空");
        }
        if (Strings.isNullOrEmpty(code)) {
            return new ApiResult<Map<String, Object>>(104, "验证码不能为空");
        }
        bankCardNo = StringUtils.replace(bankCardNo, " ", "");
        mobile = StringUtils.replace(mobile, " ", "");

        int isValidCode = shortMessageBO.isValidCode(mobile, code, 3);
        if (isValidCode != 0) {
            switch(isValidCode) {
            case 1000:
                return new ApiResult<Map<String, Object>>(201, "请重新获取验证码");
            case 1001:
                return new ApiResult<Map<String, Object>>(202, "验证码错误");
            case 1002:
                return new ApiResult<Map<String, Object>>(203, "验证码已失效");
            }
        }

        UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();
        int userId = userIdentity.getId();

        MemberCheck memberCheckVO = new MemberCheck();
        memberCheckVO.setUid(userId);
        memberCheckVO.setStatus(1);

        MemberCheck memberCheck = memberCheckBO.get(memberCheckVO);
        if (memberCheck == null) {
            return new ApiResult<Map<String, Object>>(204, "未实名认证");
        }

        // 银行卡三要素核验接口
        Map<String, String> params = Maps.newHashMap();
        params.put("merchantNo", Constants.GEXINBAO_MERCHANT_NO);
        params.put("merchantKey", Constants.GEXINBAO_MERCHANT_KEY);
        params.put("name", memberCheck.getName());
        params.put("idCardNo", memberCheck.getIdCard());
        // params.put("mobile", mobile);
        params.put("bankCardNo", bankCardNo);

        String result = HttpUtils.doPost(Constants.GEXINBAO_IDENTITY_BANKCARD3CHECK, params);
        JSONObject json = JSONObject.parseObject(result);

        String returnCode = json.getString("code");
        String returnMsg = json.getString("msg");
        LOGGER.info("银行卡三要素核验接口返回：returnCode={}=returnMsg={}=", returnCode, returnMsg);
        if (!"0".equals(returnCode)) {
            if (!"0".equals(returnCode)) {
                if ("2000".equals(returnCode) || "2001".equals(returnCode)
                        || "2002".equals(returnCode) || "3000".equals(returnCode)) {
                    return new ApiResult<Map<String, Object>>(Integer.valueOf(returnCode), "认证错误，请联系客服");
                } else {
                    return new ApiResult<Map<String, Object>>(Integer.valueOf(returnCode), returnMsg);
                }
            }
        }

        JSONObject dataJson = json.getJSONObject("data");
        String dataCode = dataJson.getString("code");
        String dataMsg = dataJson.getString("msg");
        LOGGER.info("银行卡三要素核验接口结果返回：dataCode={}=dataMsg={}=", dataCode, dataMsg);
        if (!"0".equals(dataCode)) {
            return new ApiResult<Map<String, Object>>(Integer.valueOf(dataCode), dataMsg);
        }

        Mybank entity = new Mybank();
        entity.setUid(userIdentity.getId());
        entity.setBid(Integer.valueOf(bankId));
        entity.setName(memberCheck.getName());
        entity.setNumber(bankCardNo);
        entity.setMobile(mobile);

        Mybank mybankVO = new Mybank();
        mybankVO.setUid(userIdentity.getId());
        Mybank mybank = mybankBO.get(mybankVO);
        if (mybank != null) {
            return new ApiResult<Map<String, Object>>(205, "已绑定银行卡");
        }

        mybankBO.create(entity);

        // 绑卡成功后即设置交易密码
        ShortMessage messageEntity = new ShortMessage();
        messageEntity.setCode(RandomUtils.getRandomString("1234567890", 6));
        messageEntity.setType(6);
        messageEntity.setStatus(0);
        messageEntity.setMobile(mobile);
        messageEntity.setRequestTimes(0);
        messageEntity.setValidTime(300);
        shortMessageBO.insert(messageEntity);

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("firstSetCode", messageEntity.getCode());

       return new ApiResult<Map<String, Object>>(0, "success", data);
    }

    @RequestMapping(value = "/tied-card3", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> tiedCard3(String t, String bankCardNo) {
        if (Strings.isNullOrEmpty(t)) {
            throw new IllegalArgumentException("t is null or empty");
        }
        AppToken appToken = appTokenBO.getByToken(t);
        if (appToken == null) {
            throw new IllegalArgumentException("t is error");
        }
        MemberCheck memberCheckVO = new MemberCheck();
        memberCheckVO.setUid(appToken.getUserId());
        memberCheckVO.setStatus(1);

        MemberCheck memberCheck = memberCheckBO.get(memberCheckVO);
        if (memberCheck == null) {
            return new ApiResult<Map<String, Object>>(204, "未实名认证");
        }

        // 银行卡三要素核验接口
        Map<String, String> params = Maps.newHashMap();
        params.put("merchantNo", Constants.GEXINBAO_MERCHANT_NO);
        params.put("merchantKey", Constants.GEXINBAO_MERCHANT_KEY);
        params.put("name", memberCheck.getName());
        params.put("idCardNo", memberCheck.getIdCard());
        // params.put("mobile", mobile);
        params.put("bankCardNo", bankCardNo);

        String result = HttpUtils.doPost(Constants.GEXINBAO_IDENTITY_BANKCARD3CHECK, params);
        JSONObject json = JSONObject.parseObject(result);
        
        String returnCode = json.getString("code");
        String returnMsg = json.getString("msg");
        LOGGER.info("银行卡三要素核验接口返回：returnCode={}=returnMsg={}=", returnCode, returnMsg);
        if (!"0".equals(returnCode)) {
            if (!"0".equals(returnCode)) {
                if ("2000".equals(returnCode) || "2001".equals(returnCode)
                        || "2002".equals(returnCode) || "3000".equals(returnCode)) {
                    return new ApiResult<Map<String, Object>>(Integer.valueOf(returnCode), "认证错误，请联系客服");
                } else {
                    return new ApiResult<Map<String, Object>>(Integer.valueOf(returnCode), returnMsg);
                }
            }
        }
        
        JSONObject dataJson = json.getJSONObject("data");
        String dataCode = dataJson.getString("code");
        String dataMsg = dataJson.getString("msg");
        LOGGER.info("银行卡三要素核验接口结果返回：dataCode={}=dataMsg={}=", dataCode, dataMsg);
        if (!"0".equals(dataCode)) {
            return new ApiResult<Map<String, Object>>(Integer.valueOf(dataCode), dataMsg);
        }

        return new ApiResult<Map<String, Object>>(0, "success");
    }

    /**
     * 设置交易密码
     * @param password 交易密码
     * @param code 短信验证码
     * @param firstSetCode 绑卡成功后即设置交易密码验证码
     * @return
     */
    @RequestMapping(value = "/trading-password/set", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> tradingPasswordSet(String password, String code, String firstSetCode) {
        if (StringUtils.isEmpty(password)) {
            return new ApiResult<Map<String, Object>>(101, "交易密码不能为空");
        }

        UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();
        int userId = userIdentity.getId();

        Mybank mybankVO = new Mybank();
        mybankVO.setUid(userId);
        Mybank mybank = mybankBO.get(mybankVO);
        if (mybank == null) {
            return new ApiResult<Map<String, Object>>(201, "未绑定银行卡");
        }

        if (!StringUtils.isEmpty(firstSetCode)) {
            ShortMessage condition = new ShortMessage();
            condition.setMobile(mybank.getMobile());
            condition.setStatus(0); // 初始
            condition.setType(6);
            ShortMessage shortMessage = shortMessageBO.get(condition);
            if (shortMessage != null) {
                if (!shortMessage.getCode().equals(firstSetCode)) {
                    return new ApiResult<Map<String, Object>>(202, "设置交易密码出错，请重新设置");
                }
            } else {
                return new ApiResult<Map<String, Object>>(203, "设置交易密码出错，请重新设置");
            }
        } else {
            if (StringUtils.isEmpty(code)) {
                return new ApiResult<Map<String, Object>>(102, "验证码不能为空");
            }
            int isValidCode = shortMessageBO.isValidCode(mybank.getMobile(), code, 6);
            if (isValidCode != 0) {
                switch(isValidCode) {
                case 1000:
                    return new ApiResult<Map<String, Object>>(204, "请重新获取验证码");
                case 1001:
                    return new ApiResult<Map<String, Object>>(205, "验证码错误");
                case 1002:
                    return new ApiResult<Map<String, Object>>(206, "验证码已失效");
                }
            }
        }

        UcenterMember entity = new UcenterMember();
        entity.setTradingPassword(DigestUtils.sha1Hex(password));
        ucenterMemberBO.update(entity, userIdentity.getId());

        return new ApiResult<Map<String, Object>>(0, "success");
    }

    /**
     * 忘记/修改交易密码
     * @param cardNo 已绑定的银行卡号
     * @param code 短信验证码
     * @param password 交易密码
     * @return
     */
    @RequestMapping(value = "/trading-password/reset", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Object>> tradingPasswordReset(String cardNo, String code, String password) {
        if (StringUtils.isEmpty(cardNo)) {
            return new ApiResult<Map<String, Object>>(101, "银行卡号不能为空");
        }
        if (StringUtils.isEmpty(code)) {
            return new ApiResult<Map<String, Object>>(102, "验证码不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return new ApiResult<Map<String, Object>>(103, "密码不能为空");
        }

        UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();

        Mybank mybankVO = new Mybank();
        mybankVO.setUid(userIdentity.getId());
        Mybank mybank = mybankBO.get(mybankVO);
        if (mybank == null) {
            return new ApiResult<Map<String, Object>>(201, "未绑定银行卡");
        }
        if (!cardNo.equals(mybank.getNumber())) {
            return new ApiResult<Map<String, Object>>(202, "您输入的银行卡号和实际绑定银行卡号不匹配");
        }

        int isValidCode = shortMessageBO.isValidCode(mybank.getMobile(), code, 7);
        if (isValidCode != 0) {
            switch(isValidCode) {
            case 1000:
                return new ApiResult<Map<String, Object>>(203, "请重新获取验证码");
            case 1001:
                return new ApiResult<Map<String, Object>>(204, "验证码错误");
            case 1002:
                return new ApiResult<Map<String, Object>>(205, "验证码已失效");
            }
        }

        UcenterMember entity = new UcenterMember();
        entity.setTradingPassword(DigestUtils.sha1Hex(password));
        ucenterMemberBO.update(entity, userIdentity.getId());

        return new ApiResult<Map<String, Object>>(0, "success");
    }

    /**
     * 我的绑定银行卡信息
     * @return
     */
    @RequestMapping(value = "/bank-card/info", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Map<String, Object>> bankCardInfo() {
        UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        // 实名认证信息
        MemberCheck checkVO = new MemberCheck();
        checkVO.setUid(userIdentity.getId());
        MemberCheck memberCheck = memberCheckBO.get(checkVO);
        if (memberCheck != null) {
            data.put("name", StringUtil.maskName(memberCheck.getName()));
            String idCard = memberCheck.getIdCard();
            if (!Strings.isNullOrEmpty(idCard)) {
                data.put("idcard", StringUtil.maskIDCardNo(idCard));
            }
        }

        Mybank mybankVO = new Mybank();
        mybankVO.setUid(userIdentity.getId());

        Mybank myBank = mybankBO.get(mybankVO);
        if (myBank == null) {
            return new ApiResult<Map<String, Object>>(201, "未绑定银行卡");
        }
        Bank bank = bankBO.get(myBank.getBid());



        data.put("bid", myBank.getBid());
        data.put("number", myBank.getNumber());
        //data.put("name", myBank.getName());
        data.put("mobile", StringUtils.isEmpty(myBank.getMobile()) ? "" : myBank.getMobile());
        data.put("bankName", bank.getName());
        data.put("icon", Constants.UPYUN_BASE_URL + bank.getIcon());
        data.put("backgroundImage", StringUtils.isEmpty(bank.getBackgroundImage()) ? "" : Constants.UPYUN_BASE_URL + bank.getBackgroundImage());
        data.put("singleLimit", StringUtils.isEmpty(bank.getSingleLimit()) ? "" : bank.getSingleLimit());
        data.put("dailyAccumulativeLimit", StringUtils.isEmpty(bank.getDailyAccumulativeLimit()) ? "" : bank.getDailyAccumulativeLimit());
        data.put("monthlyAccumulativeLimit", StringUtils.isEmpty(bank.getMonthlyAccumulativeLimit()) ? "" : bank.getMonthlyAccumulativeLimit());
        data.put("conditionsOfUsage", StringUtils.isEmpty(bank.getConditionsOfUsage()) ? "" : bank.getConditionsOfUsage());

        return new ApiResult<Map<String, Object>>(0, "success", data);
    }

  
    /**
     * 注册协议
     */
    @RequestMapping(value = "/register-protocol", method = RequestMethod.GET)
    public String h5Register(HttpServletRequest request, Model model) {
        return "/user/register-protocol";
    }


    /**
     * 帮助中心
     */
    @RequestMapping(value = "/h5/help-center", method = RequestMethod.GET)
    public String h5HelpCenter() {
        
        return "/user/help-center";
    }


    /**
     * 上传用户头像
     * @param file
     * @param
     * @return
     */
	@RequestMapping(value = "/uploadUserImg")
    @ResponseBody
    public ApiResult<Map<String, Object>> uploadUserImg(MultipartFile file,@RequestParam(required = false) String fileName){
        UserIdentity userIdentity = UserIdentityUtils.getUserIdentity();
//        UserIdentity userIdentity = new UserIdentity();
//        userIdentity.setId(8388);
        if(userIdentity==null){
            return new ApiResult<Map<String, Object>>(101, "用户不能为空，请登录！");
        }
        if(file==null){
            return new ApiResult<Map<String, Object>>(102,"请上传文件");
        }
        String url=null;
        try{
        JSONObject fileJson=uploadFileToUpYun(file,"/userImg/",fileName,true);
         url= fileJson.getString("url");
        LOGGER.info("==========url"+url+"============");
        UcenterMember ucenterMember=ucenterMemberBO.get(userIdentity.getId());
        UcenterMember ucenterMemberVO=new UcenterMember();
        if(null ==ucenterMember.getAvatarPath() || "".equals(ucenterMember.getAvatarPath())){
        	int score=ucenterMember.getScore()+5;
        	ucenterMemberVO.setScore(score);
        }
        ucenterMemberVO.setAvatarPath(url);
        ucenterMemberBO.update(ucenterMemberVO,ucenterMember);
        }catch (Exception e){
            LOGGER.error("updateError",e.getMessage());
            return new ApiResult<Map<String, Object>>(103, "上传失败");
        }
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("url",url);
        return new ApiResult<Map<String, Object>>(0, "success", data);
    }


}
