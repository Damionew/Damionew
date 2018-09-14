package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@ApiOperation(value = "添加用户测试" ,notes = "使用事务抛出异常查看是否回滚")
	@RequestMapping(value = "/transactional",method = RequestMethod.GET)
	public void transactionalTest() throws Exception {
		try {
			userService.transactionalTest();
		} catch (Exception e) {
			try {
				userService.transactionalTest2();
			} catch (Exception e2) {
				try {
					userService.transactionalTest3();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		}
		
		
		
	}
}
