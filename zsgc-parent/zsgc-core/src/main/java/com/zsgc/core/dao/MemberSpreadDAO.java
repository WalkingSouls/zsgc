package com.zsgc.core.dao;

import com.calanger.common.dao.DAO;
import com.zsgc.core.model.MemberSpread;

public interface MemberSpreadDAO extends DAO<MemberSpread, java.lang.Integer> {
    /**
     * 增加访问量
     * @param id
     * @return
     */
    int addViewNumber(int id);
    /**
     * 增加访问量【独立IP数】
     * @param id
     * @return
     */
    int addIpNumber(int id);
    int addUserNumber(int id);
}
