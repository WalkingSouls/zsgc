package com.zsgc.core.dao;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.UcenterMember;

public interface UcenterMemberTempDAO extends DAO<UcenterMember, java.lang.Integer> {

	Integer updateProOrTaks(UcenterMember ucenterMember);

	Integer updatePro(UcenterMember ucenterMember);

	Integer updatetask(UcenterMember ucenterMember);
}
