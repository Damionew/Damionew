package com.door.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;

/**
 * 登录控制
 * @author yinyunqi
 *
 */
@Controller
public class LoginController {
	
	
	@RequestMapping(value = "/door/login", method=RequestMethod.GET)
	@ApiOperation(value = "跳转到认证模块登录",notes = "传递本系统URL给认证模块并使之返回")
	public String login() {
		return "redirect:http://localhost:8003/login?url=http://localhost:8006/photo/index";
	}
}
