/**
 * @author yinyunqi
 * @datetime 2018年9月21日
 * @Content 
 */
package com.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.common.model.Menu;
import com.common.service.LoginHistoryService;
import com.common.service.MenuService;

import io.swagger.annotations.ApiOperation;

@Controller
public class IndexController {
	
	@Autowired
	LoginHistoryService loginHistoryService;
	
	@Autowired
	MenuService menuService;
	
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
