package com.zsgc.core.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsgc.core.bo.DictCacheBO;
import com.zsgc.core.bo.JPushBO;
import com.zsgc.core.bo.MsgBO;
import com.zsgc.core.dao.CustUserMsgDAO;
import com.zsgc.core.dao.UcenterMemberDAO;
import com.zsgc.core.model.CustUserMsg;
import com.zsgc.core.model.UserExtend;
import com.zsgc.core.model.WarehouseProjects;
import com.zsgc.core.utils.ParserUtils;

@Service("msgBO")
public class DefaultMsgBO implements MsgBO {
	@Autowired
	private DictCacheBO dictCacheBO;

	@Autowired
	private CustUserMsgDAO custUserMsgDAO;

	@Autowired
	private UcenterMemberDAO ucenterMemberDAO;
	
	@Autowired
	private JPushBO jPushBO;

	private final static String PROJECT_MSG = "projectsMsg";

	private final static String MSG_KEY_PROJECT_COMMENT_1 = "project.comment.1";

	private final static String MSG_KEY_PROJECT_COMMENT_2 = "project.comment.2";

	private final static String MSG_CLIENT = "all";

	private final static byte MSG_TYPE_PROJECT_COMMENT_1 = 2;

	private final static byte MSG_TYPE_PROJECT_COMMENT_2 = 5;
	
	private final static String MSG_KEY_PROJECT_DELETE = "project.delete";

	private final static String MSG_KEY_PROJECT_RECOVERY = "project.recovery";

	private final static String MSG_KEY_PROJECT_COLLECTION = "project.collection";

	private final static byte UNREAD = 0;

	private final static byte MSG_TYPE_PROJECT_DELETE = 3;

	private final static byte MSG_TYPE_PROJECT_CHANGE = 4;

	private final static byte MSG_TYPE_PROJECT_COLLECTION = 1;
	
	private final static byte MSG_PUSH_STATUS = 1;

	// 异步发通知
	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	@Override
	public void setOneMsg(CustUserMsg msg) {
		executorService.execute(() -> {
			try {
				if (msg != null) {
					custUserMsgDAO.insertOne(msg);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		});
	}

	@Override
	public void setMoreMsg(List<CustUserMsg> msg_list) {
		executorService.execute(() -> {
			try {
				if (msg_list != null && msg_list.size() > 0) {
					custUserMsgDAO.insertMore(msg_list);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
	}

	// 异步打分通知
	@Override
	public void commentProjectsMsg(WarehouseProjects wp, int new_uid, int type, double score, double new_score) {
		executorService.execute(() -> {
			try {
				CustUserMsg msg = new CustUserMsg();
				msg.setUserId(wp.getuId());
				msg.setMsgStatus(UNREAD);
				msg.setMsgClient(MSG_CLIENT);
				String msgValues = null;
				String msgContent = null;
				if (type == 1) {
					msgValues = dictCacheBO.getValue(PROJECT_MSG, MSG_KEY_PROJECT_COMMENT_1);
					msgContent = ParserUtils.parse1(msgValues, ucenterMemberDAO.get(new_uid).getName(),
							wp.getProjectName(), score, new_score);
					msg.setMsgType(MSG_TYPE_PROJECT_COMMENT_1);
				} else {
					msgValues = dictCacheBO.getValue(PROJECT_MSG, MSG_KEY_PROJECT_COMMENT_2);
					msgContent = ParserUtils.parse1(msgValues, ucenterMemberDAO.get(new_uid).getName(),
							wp.getProjectName(), score, new_score);
					msg.setMsgType(MSG_TYPE_PROJECT_COMMENT_2);
				}
				msg.setMsgContent(msgContent);
				Date date = new Date();
				msg.setCreatedAt(date);
				msg.setUpdatedAt(date);
				msg.setMsgPushStatus(MSG_PUSH_STATUS);
				msg.setMsgPushStartAt(date);
				msg.setMsgPushEndAt(date);
				custUserMsgDAO.insertOne(msg);
				jPushBO.ContentPush(msg);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		});
	}
	
	
	// 异步删除项目通知
	@Override
	public void delProjectsMsg(WarehouseProjects wp, String delCause,List<UserExtend> selAllByPid) {
		executorService.execute(() -> {
			try {
				List<CustUserMsg> msg_list = new ArrayList<>();
				for (UserExtend userExtend : selAllByPid) {
					if (userExtend.getId() != userExtend.getNowPId()) {
						CustUserMsg msg = new CustUserMsg();
						msg.setUserId(userExtend.getuId());
						msg.setMsgStatus(UNREAD);
						msg.setMsgClient(MSG_CLIENT);
						String msgValues = dictCacheBO.getValue(PROJECT_MSG, MSG_KEY_PROJECT_DELETE);
						String msgContent = ParserUtils.parse1(msgValues, wp.getProjectName(),
								ucenterMemberDAO.get(wp.getuId()).getName(), delCause);
						msg.setMsgContent(msgContent);
						msg.setMsgType(MSG_TYPE_PROJECT_DELETE);
						Date date = new Date();
						msg.setCreatedAt(date);
						msg.setUpdatedAt(date);
						msg.setMsgPushStatus(MSG_PUSH_STATUS);
						msg.setMsgPushStartAt(date);
						msg.setMsgPushEndAt(date);
						msg_list.add(msg);
						jPushBO.ContentPush(msg);
					}
				}
				if (msg_list != null && msg_list.size() > 0) {
					custUserMsgDAO.insertMore(msg_list);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		
	}

	// 异步回收到私有仓通知
	@Override
	public void changeProjectsMsg(WarehouseProjects wp,List<UserExtend> selAllByPid) {
		executorService.execute(() -> {
			try {
				List<CustUserMsg> msg_list = new ArrayList<>();
				for (UserExtend userExtend : selAllByPid) {
					if (userExtend.getId() != userExtend.getNowPId()) {
						CustUserMsg msg = new CustUserMsg();
						msg.setUserId(userExtend.getuId());
						msg.setMsgStatus(UNREAD);
						msg.setMsgClient(MSG_CLIENT);
						String msgValues = dictCacheBO.getValue(PROJECT_MSG, MSG_KEY_PROJECT_RECOVERY);
						String msgContent = ParserUtils.parse1(msgValues, wp.getProjectName(),
								ucenterMemberDAO.get(wp.getuId()).getName());
						msg.setMsgContent(msgContent);
						msg.setMsgType(MSG_TYPE_PROJECT_CHANGE);
						Date date = new Date();
						msg.setCreatedAt(date);
						msg.setUpdatedAt(date);
						msg.setMsgPushStatus(MSG_PUSH_STATUS);
						msg.setMsgPushStartAt(date);
						msg.setMsgPushEndAt(date);
						msg_list.add(msg);
						jPushBO.ContentPush(msg);
					}
				}
				if (msg_list != null && msg_list.size() > 0) {
					custUserMsgDAO.insertMore(msg_list);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
	}

	// 项目采集异步通知
	@Override
	public void collectionProjectsMsg(WarehouseProjects wp, int old_uid) {
		executorService.execute(() -> {
			try {
				CustUserMsg msg = new CustUserMsg();
				msg.setUserId(old_uid);
				msg.setMsgStatus(UNREAD);
				msg.setMsgClient(MSG_CLIENT);
				String msgValues = dictCacheBO.getValue(PROJECT_MSG, MSG_KEY_PROJECT_COLLECTION);
				String msgContent = ParserUtils.parse1(msgValues, ucenterMemberDAO.get(wp.getuId()).getName(),
						wp.getProjectName());
				msg.setMsgContent(msgContent);
				msg.setMsgType(MSG_TYPE_PROJECT_COLLECTION);
				Date date = new Date();
				msg.setCreatedAt(date);
				msg.setUpdatedAt(date);
				msg.setMsgPushStatus(MSG_PUSH_STATUS);
				msg.setMsgPushStartAt(date);
				msg.setMsgPushEndAt(date);
				custUserMsgDAO.insertOne(msg);
				jPushBO.ContentPush(msg);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		});
	}
	
	@PreDestroy
	public void shutDown() {
		executorService.shutdown();
	}
}
