package com.zsgc.core.bo;

import com.calanger.common.bo.BO;
import com.zsgc.core.model.MemberCheck;
import com.zsgc.core.model.UcenterMember;

public interface UcenterMemberBO extends BO<UcenterMember, java.lang.Integer> {
    void loginSuccessUpdate(UcenterMember ucenterMember, UcenterMember ucenterMemberVO);
    void register(UcenterMember ucenterMember);
    void register(UcenterMember ucenterMember, MemberCheck memberCheck); 
    String getInvitationCode(); // 邀请码
}
