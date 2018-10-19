package com.door.controller;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.face.AipFace;
import com.door.util.FaceUtil;
import com.thoughtworks.xstream.core.util.Base64Encoder;

/**
 * 百度AI人脸识别
 * @author yinyunqi
 *
 */
@Controller
public class FaceController {
	
	@RequestMapping("/face")
	public String FaceIndex() {
		return "face";
	}
	
	@ResponseBody
	@RequestMapping("/face/search/url")
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
        object.put("user_name", user_name);
 		return object.toJSONString();
	} 
	
	@ResponseBody
	@RequestMapping(value = "/face/search/file",method=RequestMethod.POST)
	public String FaceSearchFile(@RequestParam("file") MultipartFile file) {
		// 转二进制
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
        JSONObject result = res.getJSONObject("result");
        JSONArray user_list = result.getJSONArray("user_list");
        JSONObject user_info = user_list.getJSONObject(0);
        String user_name = user_info.getString("user_info");
        com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
        object.put("user_name", user_name);
 		return object.toJSONString();
	} 
	
}
