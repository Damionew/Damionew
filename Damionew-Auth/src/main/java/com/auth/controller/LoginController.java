package com.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.auth.feign.CommonFeign;

import io.swagger.annotations.ApiOperation;

@Controller
public class LoginController {
	@Autowired
	CommonFeign commonFeign;
	/**
	 * 登录页面
	 * @return
	 */
	@ApiOperation(value = "登录")
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String loginPage(String url,HttpServletRequest request) {
		HttpSession  session = request.getSession ();
		session.setAttribute("url", url);
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
	
	@GetMapping(value = "/index")
	public String index(HttpServletRequest request) {
		HttpSession  session = request.getSession ();
		String url = (String) session.getAttribute("url");
		return "redirect:"+url;
	}
}
