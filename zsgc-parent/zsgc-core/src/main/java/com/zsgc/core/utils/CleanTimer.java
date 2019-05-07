package com.zsgc.core.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.zsgc.core.dao.UcenterMemberTempDAO;
import com.zsgc.core.model.UcenterMember;
/**
 * 重置数据定时器
 * @author lyd
 *
 */
public class CleanTimer implements InitializingBean{
	@Autowired
	private UcenterMemberTempDAO ucenterMemberTempDAO;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0); 
		calendar.set(Calendar.MINUTE,0); 
		calendar.set(Calendar.SECOND,0); 
		Date time = calendar.getTime();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new CleanTimeTask(), time, 1000 * 60 * 60 * 24);
	}
	private class CleanTimeTask extends TimerTask{

		@Override
		public void run() {
			UcenterMember uceMem = new UcenterMember();
			ucenterMemberTempDAO.updateProOrTaks(uceMem);
		}
		
	}
}
