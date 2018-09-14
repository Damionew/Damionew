/**
 * @author yinyunqi
 * @datetime 2018年6月23日
 * @Content 
 */
package com.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.common.model.Menu;
import com.common.service.LoginHistoryService;
//import com.common.utils.UserInfoUtil;
import com.common.service.MenuService;
import com.common.utils.UserInfoUtil;

import io.swagger.annotations.ApiOperation;

@Controller
public class IndexController {
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	LoginHistoryService loginHistoryService;
	
	@ApiOperation(value="跳转Index页面，并返回菜单和用户名",notes="")
	@RequestMapping(value= {"/index","/"},method = RequestMethod.GET)
	public String indexPage(Model model) {
		// 菜单（二级）
		List<Menu> menuList = menuService.menuList();
		model.addAttribute("menuList", menuList);
		String curentUserName = UserInfoUtil.getCurUsername();
		model.addAttribute("curentUserName", curentUserName);
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("menuList",menuList);
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
