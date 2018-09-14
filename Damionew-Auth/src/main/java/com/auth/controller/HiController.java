package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.feign.CommonFeign;
import com.auth.feign.ServiceHi;

@Controller
public class HiController {
	
	@Autowired
	ServiceHi serviceHi;
	@Autowired
	CommonFeign commonFeign;
	@ResponseBody
	@GetMapping(value = "/hi")
	public String sayHi() {
		return serviceHi.sayHi();
	}
	
	@GetMapping(value = "/login")
	public String loginPage() {
		System.out.println("login");
		commonFeign.transactionalTest();
		return "login";
	}
}
