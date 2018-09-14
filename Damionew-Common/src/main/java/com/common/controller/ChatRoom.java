package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.utils.UserInfoUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 聊天室
 * @author yinyunqi
 *
 */
@Controller
public class ChatRoom {
	/**聊天室页面**/
	@ApiOperation(value = "聊天室",notes = "返回当前用户名并跳转到聊天室页面")
	@RequestMapping(value = "/chatroom",method = RequestMethod.GET)
	public String chatRoomPage(Model model) {
		String username = UserInfoUtil.getCurUsername();
		model.addAttribute("username", username);
		return "chatroom";
	}
	
	
}
