package com.common.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.feign.WeatherFeign;
import com.common.model.Menu;
import com.common.service.MenuService;

import io.swagger.annotations.ApiOperation;


@Controller
public class FeignController {

	@Autowired
	MenuService menuService;
	
	@Autowired
	WeatherFeign weatherFeign;
	@RequestMapping("/hia")
	public String hia() {
		return "hia";
	}
	
	@ApiOperation(value = "Auth模块登录成功后请求该方法",notes = "跳转到index页面")
	@RequestMapping("/commonIndex")
	public String commonIndex(Model model) {
		List<Menu> menuList = menuService.menuList();
		model.addAttribute("menuList", menuList);
//		String curentUserName = UserInfoUtil.getCurUsername();
//		model.addAttribute("curentUserName", curentUserName);
		return "index";
	}
	
	@ApiOperation(value = "获取天气信息",notes ="通过和风天气接口获取并处理天气信息")
	@ResponseBody
	@RequestMapping(value = "/getWeatherInfo",method = RequestMethod.GET)
	public String locationTransform(HttpServletRequest request) throws IOException {
		return weatherFeign.locationTransform(request);
	}
}
