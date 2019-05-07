package com.zsgc.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsgc.api.dto.ApiResult;
import com.zsgc.api.dto.CommentProjectAccept;
import com.zsgc.core.bo.CommentProjectBO;
import com.zsgc.core.model.UserExtend;

@Controller
@RequestMapping("/comment")
public class CommentProjectController {
	@Autowired
	private CommentProjectBO commentProjectBO;
	//进入打分界面
	@RequestMapping(value = "/openComment/{nowPId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<Map<String,Object>> openComment(@PathVariable int nowPId) {
		UserExtend ue = new UserExtend();
		ue.setNowPId(nowPId);
		Map<String, Object> openComment = commentProjectBO.openComment(ue); 
		if (openComment.get("ref").equals("0")) {
			return  new ApiResult<>(0, "当前是第一次修改",openComment);
		}else if (openComment.get("ref").equals("1")) {
			return  new ApiResult<>(0, "当前是第二次修改" ,openComment);
		}else if (openComment.get("ref").equals("2")) {
			return  new ApiResult<>(1, "超过修改次数",openComment);
		}
		return null;
	}
	//打分
	@RequestMapping(value = "/commentProject", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<Object> commentProject(@RequestBody CommentProjectAccept cpa) {
		UserExtend ue = new UserExtend();
		ue.setNowPId(cpa.getpId());
		ue.setuId(cpa.getuId());
		int tpye = commentProjectBO.commentProject(ue, cpa.getScore(), cpa.getContent());
		if (tpye==2) {
			return  new ApiResult<>(2, "超过修改次数");
		}else if (tpye==1) {
			return  new ApiResult<>(0, "success");
		}else if (tpye==0) {
			return  new ApiResult<>(1, "更新失败");
		}
		return null;
	}
	
}
