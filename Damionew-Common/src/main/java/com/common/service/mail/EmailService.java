package com.common.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.utils.DateUtil;

@Service
public class EmailService {
	
	Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	public synchronized void sendEmail() {
		
		Thread sendEmailThread = new Thread(new Runnable() {
			@Override
			public void run() {
					System.out.println(DateUtil.dateFormate1());
			}
		});
		sendEmailThread.start();
		System.out.println(DateUtil.dateFormate1()+"线程开始休眠");
		try {
			sendEmailThread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
