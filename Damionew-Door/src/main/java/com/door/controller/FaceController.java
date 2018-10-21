package com.door.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.face.AipFace;
import com.door.service.FaceService;
import com.door.util.FaceUtil;
import com.thoughtworks.xstream.core.util.Base64Encoder;

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
	
	@RequestMapping("/face")
	public String FaceIndex() {
		return "face";
	}
	
	@RequestMapping("/faceIndex")
	public String FaceIndex2(Model model) {
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
	public String faceRegIndex(Model model) {
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
	public String FaceRegUrl(String imageUrl,String userId,String groupId,String userInfo) {
		String result = faceService.regFace(imageUrl, userId, groupId, userInfo);
        com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
        object.put("result", result);
 		return object.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/face/search/url")
	@ApiOperation(value = "通过网络图片链接人脸搜索" , notes = "传入网络图片链接与人脸库对比，匹配率超过90%可视为匹配")
	public String FaceSearchUrl(String face_url) {
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
        Double score = (Double) user_info.get("score");
        if(score > 90) {
        	object.put("user_name", user_name);
        }else {
        	object.put("user_name", "库中匹配度不足");
        }
        object.put("user_name", user_name);
 		return object.toJSONString();
	} 
	
	@ResponseBody
	@RequestMapping(value = "/face/search/file",headers="content-type=multipart/*" ,method=RequestMethod.POST)
	@ApiOperation(value = "通过上传图片人脸搜索" , notes = "上传图片后转成base64与人脸库对比，匹配率超过90%可视为匹配")
	public String FaceSearchFile(@RequestParam("file") MultipartFile file) {
		// 转二进制
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 转码
		String encoder = new Base64Encoder().encode(bytes);
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
	    Double score = (Double) user_info.get("score");
	    if(score > 90) {
	       object.put("user_name", user_name);
	    }else {
	       object.put("user_name", "库中匹配度不足");
	    }
 		return object.toJSONString();
	} 
	
}
