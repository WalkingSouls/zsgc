package com.zsgc.core.bo;

import java.util.Map;

import com.zsgc.core.model.UserExtend;

public interface CommentProjectBO {
	Map<String,Object> openComment(UserExtend ue);
	
	int commentProject(UserExtend ue,double score,String content);
}
