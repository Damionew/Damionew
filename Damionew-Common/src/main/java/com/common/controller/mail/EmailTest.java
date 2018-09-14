package com.common.controller.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.service.mail.EmailService;

import io.swagger.annotations.ApiOperation;

@RestController
public class EmailTest {
	
//	@Autowired
//	JavaMailSender javaMailSender;
	@Autowired
	EmailService emailService;
	/** 邮件测试  **/
	@ApiOperation(value = "邮件测试",notes = "邮件测试")
	@RequestMapping(value = "/mailTest",method = RequestMethod.GET)
	public void mailTest() {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom("yinyunqi@163.com");
//		message.setTo("kk140680909@163.com");
//		message.setSubject("主题：简单邮件");
//	    message.setText("测试邮件内容");
//	    javaMailSender.send(message);
		emailService.sendEmail();
	}
}
