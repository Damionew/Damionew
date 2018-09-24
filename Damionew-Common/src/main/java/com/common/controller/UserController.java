/**
 * @author yinyunqi
 * @datetime 2018年9月21日
 * @Content 
 */
package com.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.common.model.User;
import com.common.service.UserService;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.ApiOperation;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/user/index")
	@ApiOperation(value = "返回用户页面", notes = "")
	public String userIndex() {
		return "user/UserIndex";
	}

	@ResponseBody
	@RequestMapping("/user/userQuery")
	@ApiOperation(value = "查询用户信息",notes = "查询所有用户")
	public String userQuery(@RequestParam int pageNumber, int pageSize) {
		int total  = userService.queryUser().size();
		PageHelper.startPage(pageNumber, pageSize);
		List<User> userList =  userService.queryUser();
		JSONObject object = new JSONObject();
		object.put("rows", userList);
		object.put("total", total);
		return object.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/user/addUser")
	@ApiOperation(value = "添加用户信息",notes = "通过查询页面添加")
	public String addUser(@RequestParam String username,String password,String nickname,String description,String icon) {
		int cnt = userService.addUser(username, password, nickname, description, icon);
		System.out.println(cnt);
		JSONObject object = new JSONObject();
		object.put("result", cnt);
		return object.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/user/deleteUser")
	@ApiOperation(value = "删除用户信息",notes = "通过查询页面删除")
	public String deleteUser(@RequestParam String userid) {
		int cnt = userService.deleteUser(userid);
		JSONObject object = new JSONObject();
		object.put("result", cnt);
		return object.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/user/queryUserById")
	@ApiOperation(value = "根据userid查询用户信息",notes = "通过查询页面查询单个用户")
	public String queryUserById(@RequestParam String userid) {
		User user = userService.queryUserById(userid);
		JSONObject object = new JSONObject();
		object.put("result", user);
		return object.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/user/updateUserById")
	@ApiOperation(value = "更新用户信息",notes = "通过查询页面更新")
	public String updateUserById(@RequestParam String userid,String username,String password,String nickname,String description,String icon) {
		int cnt = userService.updateUserById(userid,username, password, nickname, description, icon);
		System.out.println(cnt);
		JSONObject object = new JSONObject();
		object.put("result", cnt);
		return object.toJSONString();
	}
}
