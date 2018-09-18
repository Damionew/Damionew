package com.common.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.common.feign.WeatherFeign;
import com.common.model.LoginHistory;
import com.common.model.Menu;
import com.common.service.LoginHistoryService;
import com.common.service.MenuService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:8003", maxAge = 3600)	// 跨域
@Controller
public class FeignController {
	@Autowired
	LoginHistoryService loginHistoryService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	WeatherFeign weatherFeign;
	@RequestMapping("/hia")
	public String hia() {
		return "hia";
	}
	
	@ApiOperation(value = "Auth模块登录成功后重定向请求该方法",notes = "跳转到index页面")
	@RequestMapping("/index")
	public String redirectIndex(HttpServletResponse response,Model model) {
		List<Menu> menuList = menuService.menuList();
		model.addAttribute("menuList", menuList);
		System.out.println("redirect:http://localhost:8004/index");
//		String curentUserName = UserInfoUtil.getCurUsername();
//		model.addAttribute("curentUserName", curentUserName);
		return "index";
	}
	
	@ApiOperation(value = "获取天气信息",notes ="通过和风天气接口获取并处理天气信息")
	@ResponseBody
	@RequestMapping(value = "/getWeatherInfo",method = RequestMethod.GET)
	public String locationTransform(@RequestParam("lng") String lng,@RequestParam("lat") String lat) throws IOException {
		System.out.println("lng:"+lng+"lat:"+lat);
		return weatherFeign.locationTransform(lng,lat);
	}
	
	@ApiOperation(value="返回浏览人数",notes="通过浏览记录计算")
	@ResponseBody
	@RequestMapping(value = "/countVistors",method=RequestMethod.GET)
	public String countVistors() {
		String count = loginHistoryService.selectCountVistors();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("countVistors",count);
		return jsonObject.toJSONString();
	}
}
