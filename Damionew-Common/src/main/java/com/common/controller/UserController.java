/**
 * @author yinyunqi
 * @datetime 2018年9月21日
 * @Content 
 */
package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiOperation;

@Controller
public class UserController {

	@GetMapping("/user/index")
	@ApiOperation(value = "返回用户页面", notes = "")
	public String userIndex() {
		return "user/UserIndex";
	}

	@ResponseBody
	@RequestMapping("/query")
	public String memberQuery(@RequestParam int pageNumber, int pageSize) {
//		String familyAccount = ((User) SecurityUtils.getSubject().getPrincipal()).getFamilyaccount();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("familyAccount", familyAccount);
//		int total = memberService.memberQuery(map).size();
//		PageHelper.startPage(pageNumber, pageSize);
//		List<Member> memberList = memberService.memberQuery(map);
		JSONObject object = new JSONObject();
//		object.put("rows", memberList);
//		object.put("total", total);
		return object.toJSONString();
	}
}
