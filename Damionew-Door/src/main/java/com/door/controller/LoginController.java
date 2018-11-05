package com.door.controller;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.aip.face.AipFace;
import com.door.util.FaceUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 登录控制
 * @author yinyunqi
 *
 */
@Controller
public class LoginController {
	
	
	@RequestMapping(value = "/door/login", method=RequestMethod.GET)
	@ApiOperation(value = "跳转到认证模块登录",notes = "传递本系统URL给认证模块并使之返回")
	public String login() {
		return "redirect:http://localhost:8003/login?url=http://localhost:8006/photo/index";
	}
	
	@RequestMapping(value = "/faceLogin", method=RequestMethod.GET)
	@ApiOperation(value = "摄像头登录页面",notes = "")
	public String faceLogin() {
		return "faceLogin";
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/valify", method=RequestMethod.POST)
	@ApiOperation(value = "登录验证",notes = "上传人脸图片Base64码进行比对")
	public String loginValify(@RequestParam("faceImg")String faceImg) {
	   FaceUtil face = new FaceUtil();
	   AipFace client = face.initFace();
		 // 传入可选参数调用接口
       HashMap<String, String> options = new HashMap<String, String>();
       String image = faceImg;
       String imageType = "BASE64";
       String groupId = "test_group";
       // 人脸搜索
       JSONObject res = client.search(image, imageType, groupId, options);
       com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
       if (res.get("result").equals(null)) {
    	   object.put("result",res.get("error_msg"));
       }else {
    	   JSONObject result = res.getJSONObject("result");
   	    JSONArray user_list = result.getJSONArray("user_list");
   	    JSONObject user_info = user_list.getJSONObject(0);
   	    String user_name = user_info.getString("user_info");
   	    Object scoreString =  user_info.get("score");
          if (scoreString instanceof Integer) {
          	object.put("user_name", user_name);
          	object.put("score", 100);
   		}else if (scoreString instanceof Double) {
   			Double score = Double.valueOf(scoreString.toString());;
   	        if(score > 90) {
   	        	object.put("user_name", user_name);
   	        	object.put("score", score);
   	        }else {
   	        	object.put("user_name", user_name);
   	        	object.put("score", score);
   	        }
   		}
       }
		return object.toJSONString();
	}
}
