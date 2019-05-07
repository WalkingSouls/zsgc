package com.zsgc.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsgc.api.dto.ApiResult;
import com.zsgc.core.bo.CustUserMsgBO;
import com.zsgc.core.model.CustUserMsg;
import com.zsgc.core.utils.PageBean;

@Controller
@RequestMapping("/usermsg")
public class CustUserMsgController {
	@Autowired
	private CustUserMsgBO custUserMsgBO;
	//获取通知
	@RequestMapping(value = "/getNotice/{uId}/{state}/{pageNum}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Object> getMsg(@PathVariable() int uId, @PathVariable() int state, @PathVariable() int pageNum,
			@PathVariable() int pageSize) {
		System.out.println("state"+state);
		 PageBean<CustUserMsg> allMsg = custUserMsgBO.getAllMsg(uId, state,pageNum,pageSize);
		if (allMsg.getList()!= null&& allMsg.getList().size()>0) {
			return new ApiResult<>(0, "success",allMsg);
		}
		return new ApiResult<>(1, "数据为空");
		
	}
	//更新全部通知
	@RequestMapping(value = "/upDateAllNotice/{uId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Object> changeAllMsg(@PathVariable() int uId){
		CustUserMsg custUserMsg = new CustUserMsg();
		custUserMsg.setUserId(uId);
		custUserMsg.setMsgStatus((byte)1);
		custUserMsg.setMsgReadAt(new Date());
		int index = custUserMsgBO.changeAllMsg(custUserMsg);
		if (index == 1) {
			return new ApiResult<>(0, "success");
		}
		return new ApiResult<>(1, "更新失败");
	}
	
	//更新一个通知
	@RequestMapping(value = "/upDateNotice/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Object> changeOneMsg(@PathVariable() int id){
		CustUserMsg custUserMsg = new CustUserMsg();
		custUserMsg.setId((long)id);
		custUserMsg.setMsgStatus((byte)1);
		custUserMsg.setMsgReadAt(new Date());
		int index = custUserMsgBO.changeOneMsg(custUserMsg);
		if (index == 1) {
			return new ApiResult<>(0, "success");
		}
		return new ApiResult<>(1, "更新失败");
	}
}
