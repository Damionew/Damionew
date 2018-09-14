package com.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.UserService;

import io.swagger.annotations.ApiOperation;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	
	/**
	 * 登录页面
	 * @return
	 */
	@ApiOperation(value = "登录")
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	@ApiOperation(value = "退出登录")
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "login";
	}
	
}
