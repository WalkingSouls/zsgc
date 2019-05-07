package com.zsgc.core.bo.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zsgc.core.bo.JPushBO;
import com.zsgc.core.dao.CustUserMsgDAO;
import com.zsgc.core.dao.TaskMsgDAO;
import com.zsgc.core.model.CustUserMsg;
import com.zsgc.core.model.TaskMsg;
import com.zsgc.core.utils.JPushUtils;

@Service("jPushBO")
public class DefaultJPushBO implements InitializingBean, JPushBO {
	@Autowired
	private CustUserMsgDAO custUserMsgDAO;
	
	@Autowired
	private TaskMsgDAO taskMsgDAO;
	
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	private ExecutorService executorService = Executors.newFixedThreadPool(20);

	private List<Map<Long, PushBean>> list = new ArrayList<>();
	// 推送间隔 必须被60整除
	private final int MIN_TIME_INTERVAL = 1;

	private int delay;
	private int index;

	// 初始化
	private void init() {
		for (int i = 0; i < (60 / MIN_TIME_INTERVAL); i++) {
			list.add(new ConcurrentHashMap<>());
		}
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("mm:ss");
		String time = df.format(date);
		String[] split = time.split(":");
		index = (Integer.valueOf(split[0]) / MIN_TIME_INTERVAL + 1) % (60 / MIN_TIME_INTERVAL);
		delay = 60 * MIN_TIME_INTERVAL
				- (Integer.valueOf(split[0]) % MIN_TIME_INTERVAL * 60 + (Integer.valueOf(split[1])));
		List<CustUserMsg> selAllMsgPush = custUserMsgDAO.selAllMsgPush(1);
		if (selAllMsgPush != null) {
			for (CustUserMsg custUserMsg : selAllMsgPush) {
				insContentPushCache(custUserMsg);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
		executor.scheduleAtFixedRate(() -> {
			Map<Long, PushBean> map = list.get(index);
			Date nowDate = new Date();
			Iterator<Long> it = map.keySet().iterator();
			while (it.hasNext()) {
				PushBean pushBean = map.get(it.next());
				if (pushBean.getPushNum() == 0) {
					// System.out.println("推送:" + pushBean.getContent());
					push(String.valueOf(pushBean.getUid()), pushBean.getContent());
					// 发送推送
					if (pushBean.getPushEndAt().getTime() - nowDate.getTime() > 60 * MIN_TIME_INTERVAL*1000) {
						pushBean.setPushNum(24 - 1);
					} else {
						// 修改数据库
						upMysql(pushBean.getId(),new Date());
						it.remove();
					}
				} else {
					pushBean.setPushNum(pushBean.getPushNum() - 1);
				}
			}
			index = (index + 1) % (60 / MIN_TIME_INTERVAL);
		} , delay, MIN_TIME_INTERVAL * 60, TimeUnit.SECONDS);

	}

	// 定时推送插入缓存
	private void insContentPushCache(CustUserMsg c) {
		executorService.execute(() -> {
			try {
				Date msgPushEndAt = c.getMsgPushEndAt();
				Date msgPushStartAt = c.getMsgPushStartAt();
				Date nowTime = new Date();
				Long id = c.getId();
				DateFormat df1 = new SimpleDateFormat("HH:mm");
				String formatP = df1.format(msgPushStartAt);
				String[] splitP = formatP.split(":");
				String formatN = df1.format(nowTime);
				String[] splitN = formatN.split(":");
				int pushNum = (Integer.valueOf(splitP[0]) - Integer.valueOf(splitN[0])) * 60
						+ (Integer.valueOf(splitP[1]) - Integer.valueOf(splitN[1])) / 60;
				if (msgPushEndAt.getTime() - nowTime.getTime() < 0) {
					return;
				}
				PushBean p = new PushBean();
				p.setUid(c.getUserId());
				p.setId(id);
				p.setPushNum(pushNum);
				p.setContent(c.getMsgContent());
				p.setPushEndAt(msgPushEndAt);
				int changePMap = (Integer.valueOf(splitP[1])) / MIN_TIME_INTERVAL;
				list.get(changePMap).put(id, p);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
	}
	//删除缓存
	private void delContentPushCache(CustUserMsg c){
				Date msgPushStartAt = c.getMsgPushStartAt();
				Long id = c.getId();
				DateFormat df1 = new SimpleDateFormat("mm");
				String formatP = df1.format(msgPushStartAt);
				int changePMap = (Integer.valueOf(formatP)) / MIN_TIME_INTERVAL;
				if (list.get(changePMap).containsKey(id)) {
					list.get(changePMap).remove(id);
				}
	}
	
	// 更新mysql
	@Transactional
	public void upMysql(long id,Date date) {
		executorService.execute(() -> {
			try {
				custUserMsgDAO.updateMsgPush(id,date);
				taskMsgDAO.updateByMid(id,date);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		});
	}

	// 直接推送
	@Override
	public void ContentPush(CustUserMsg c) {
		executorService.execute(() -> {
			try {
				// System.out.println("推送:"+c.getUserId()+"aaa" +
				// c.getMsgContent());
				push(c.getUserId().toString(), c.getMsgContent());
				upMysql(c.getId(),new Date());
			} catch (Exception e) {

			}
		});
	}

	// 插入mysql
	@Override
	@Transactional
	public void insMysqlAndCache(CustUserMsg msg,int tId) {
		executorService.execute(() -> {
			try {
				custUserMsgDAO.insertOne(msg);
				TaskMsg tm = new TaskMsg();
				Date date = new Date();
				tm.setmId(msg.getId());
				tm.settId(tId);
				tm.setCreatAt(date);
				tm.setUpdateAt(date);
				taskMsgDAO.insertOne(tm);
				insContentPushCache(msg);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		});
	}

	// 推送
	private void push(String alias1, String ALERT) {
		executorService.execute(() -> {
			try {
				JPushUtils.sendAlias(alias1, ALERT);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
	}
	
	@Override
	@Transactional
	public void updateMysqlAndCache(CustUserMsg msg,int tId) {
		executorService.execute(() -> {
			try {
				delMsg(tId);
				insMysqlAndCache(msg,tId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		});
	
	}
	
	@Override
	@Transactional
	public void delMsg(int tId) {
		executorService.execute(() -> {
			try {
				long id = taskMsgDAO.selMsgByTid(tId);
				delContentPushCache(custUserMsgDAO.selByid(id));
				custUserMsgDAO.delByid(id);
				taskMsgDAO.delByMid(id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		});
	}
	
	private class PushBean {
		private long id;
		private long pushNum;
		private String content;
		private Date pushEndAt;
		private int uid;

		public int getUid() {
			return uid;
		}

		public void setUid(int uid) {
			this.uid = uid;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getPushNum() {
			return pushNum;
		}

		public void setPushNum(long pushNum) {
			this.pushNum = pushNum;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getPushEndAt() {
			return pushEndAt;
		}

		public void setPushEndAt(Date pushEndAt) {
			this.pushEndAt = pushEndAt;
		}
	}

	@PreDestroy
	public void shutDown() {
		executor.shutdown();
		executorService.shutdown();
	}
}
