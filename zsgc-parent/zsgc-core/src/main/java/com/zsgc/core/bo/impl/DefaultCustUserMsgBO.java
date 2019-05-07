package com.zsgc.core.bo.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zsgc.core.bo.CustUserMsgBO;
import com.zsgc.core.dao.CustUserMsgDAO;
import com.zsgc.core.model.CustUserMsg;
import com.zsgc.core.utils.PageBean;

@Service("custUserMsgBO")
public class DefaultCustUserMsgBO implements CustUserMsgBO {
	private final static byte MSG_STATE_UNREAD = 0;
	private final static byte MSG_STATE_READ = 1;
	private final static byte MSG_STATE_ALL = 2;
	@Autowired
	private CustUserMsgDAO custUserMsgDAO;

	@Override
	public PageBean<CustUserMsg> getAllMsg(int uid,int index ,int pageNum,int pageSize) {
		PageBean<CustUserMsg> page = new PageBean<>(pageNum, pageSize);
		CustUserMsg custUserMsg = new CustUserMsg();
		custUserMsg.setUserId(uid);
		if (index==MSG_STATE_UNREAD) {
			custUserMsg.setMsgStatus(MSG_STATE_UNREAD);
			page.setTotalRecord(custUserMsgDAO.selAllCount(custUserMsg));
			page.totalPage();
			return custUserMsgDAO.selAllMsgByOrderC(custUserMsg,page);
		}else if (index==MSG_STATE_ALL) {
			page.setTotalRecord(custUserMsgDAO.selAllCount(custUserMsg));
			page.totalPage();
			return custUserMsgDAO.selAllMsgByOrderC(custUserMsg,page);
		}else if (index==MSG_STATE_READ) {
			custUserMsg.setMsgStatus(MSG_STATE_READ);
			page.setTotalRecord(custUserMsgDAO.selAllCount(custUserMsg));
			page.totalPage();
			return custUserMsgDAO.selAllMsgByOrderR(custUserMsg,page);
		}
		return null;
	}

	@Override
	@Transactional
	public int changeAllMsg(CustUserMsg custUserMsg) {
		int index = 0;
		try {
			index = custUserMsgDAO.updateAllMsg(custUserMsg);
		} catch (Exception e) {
			index = -1;
		}
		if (index!=-1) {
			return 1;
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}

	@Override
	public int changeOneMsg(CustUserMsg custUserMsg) {
		int index = 0;
		try {
			index = custUserMsgDAO.updateOneMsg(custUserMsg);
		} catch (Exception e) {
			index = -1;
		}
		if (index!=-1) {
			return 1;
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}

}
