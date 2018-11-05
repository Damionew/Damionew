package com.door.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Encoder;
import com.baidu.aip.face.AipFace;
import com.door.service.FaceService;
import com.door.util.FaceUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 百度AI人脸识别
 * @author yinyunqi
 *
 */
@Controller
public class FaceController {
	@Autowired
	FaceService faceService;
	
	@RequestMapping("/faceSearch")
	public String FaceIndex() {
		return "faceSearch";
	}
	
	@RequestMapping("/faceIndex")
	public String FaceIndex2(Model model) throws JSONException {
		JSONArray group_id_list = faceService.getgroup();
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		for(int i = 0;i < group_id_list.length();i++) {
			// 遍历用户组并获取组中user
			String groupId = group_id_list.get(i).toString();
			JSONArray user_id_list = faceService.getUser(groupId);
			for(int j = 0;j < user_id_list.length();j++) {
				// 通过user_id和group_id定位获取用户信息
				String userId = user_id_list.get(j).toString();
				String userInfo = faceService.getUserInfo(userId, groupId);
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("groupId", groupId);
				map.put("userId", userId);
				map.put("userInfo", userInfo);
				result.add(map);
			}
		}
		model.addAttribute("result",result);
		return "faceIndex";
	}
	
	@RequestMapping("/faceReg")
	public String faceRegIndex(Model model) throws JSONException {
		JSONArray group_id_list = faceService.getgroup();
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		for(int i = 0;i < group_id_list.length();i++) {
			// 遍历用户组并获取组中user
			String groupId = group_id_list.get(i).toString();
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("groupId", groupId);
			result.add(map);
		}
		model.addAttribute("result",result);
		return "faceReg";
	}
	
	@ResponseBody
	@RequestMapping("/face/reg/url")
	@ApiOperation(value = "通过网络图片链接人脸注册" , notes = "传入网络图片链接")
	public String FaceRegUrl(@RequestParam("imageUrl") String imageUrl,@RequestParam("userId") String userId,@RequestParam("groupId") String groupId,@RequestParam("userInfo") String userInfo) throws JSONException {
		String result = faceService.regFace(imageUrl, userId, groupId, userInfo);
        com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
        object.put("result", result);
 		return object.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/face/reg/file",headers="content-type=multipart/*" ,method=RequestMethod.POST)
	@ApiOperation(value = "通过上传图片人脸注册" , notes = "上传图片后转成base64到人脸库")
	public String FaceRegFile(@RequestParam("file") MultipartFile file,String imageUrl,String userId,String groupId,String userInfo) throws JSONException {
		// 转二进制
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 转码
		String encoder = new BASE64Encoder().encode(bytes);
		FaceUtil face = new FaceUtil();
		AipFace client = face.initFace();
		 // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", userInfo);
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
        String image = encoder;
        String imageType = "BASE64";
        // 人脸搜索
        JSONObject res = client.addUser(image, imageType, groupId, userId, options);
        com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
        String error_msg = (String) res.get("error_msg");
	    if(error_msg.equals("SUCCESS")) {
	    	/*
	    	 * 返回json串如下
	    	 * {"result":{"group_id_list":["group1","test_group"]},
	    	 * "log_id":1515457525550,"error_msg":"SUCCESS","cached":0,"error_code":0,
	    	 * "timestamp":1540024885}
	    	 */
	    	object.put("result", "SUCCESS");
	    }else {
	    	object.put("result", "ERROR");
	    }
 		return object.toJSONString();
	} 
	
	@ResponseBody
	@RequestMapping("/face/search/url")
	@ApiOperation(value = "通过网络图片链接人脸搜索" , notes = "传入网络图片链接与人脸库对比，匹配率超过90%可视为匹配")
	public String FaceSearchUrl(String face_url) throws JSONException {
		FaceUtil face = new FaceUtil();
		AipFace client = face.initFace();
		 // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        String image = face_url;
        String imageType = "URL";
        String groupId = "test_group";
        // 人脸搜索
        JSONObject res = client.search(image, imageType, groupId, options);
        JSONObject result = res.getJSONObject("result");
        JSONArray user_list = result.getJSONArray("user_list");
        JSONObject user_info = user_list.getJSONObject(0);
        String user_name = user_info.getString("user_info");
        com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
        
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
 		return object.toJSONString();
	} 
	
	@ResponseBody
	@RequestMapping(value = "/face/search/file",headers="content-type=multipart/*" ,method=RequestMethod.POST)
	@ApiOperation(value = "通过上传图片人脸搜索" , notes = "上传图片后转成base64与人脸库对比，匹配率超过90%可视为匹配")
	public String FaceSearchFile(@RequestParam("file") MultipartFile file) throws JSONException {
		// 转二进制
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 转码
		String encoder = new BASE64Encoder().encode(bytes);
		FaceUtil face = new FaceUtil();
		AipFace client = face.initFace();
		 // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        String image = encoder;
        String imageType = "BASE64";
        String groupId = "test_group";
        // 人脸搜索
        JSONObject res = client.search(image, imageType, groupId, options);
        com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
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
 		return object.toJSONString();
	} 
	
}
