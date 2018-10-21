package com.door.util;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

//@Component
public class FaceUtil {
	// 设置
	public static final String APP_ID = "14482860";
	public static final String APP_KEY = "EBQ8L5ZtOf4gO7sSHOM27YGv";
	public static final String SECRET_KEY = "OTWEfVZyjT3lRb9WuUn5yaYAGO1TNnny";
	
	/**
	 * 百度AI人脸识别
	 */
	public AipFace initFace() {
		// 初始化ApiFace
		AipFace client  = new AipFace(APP_ID, APP_KEY, SECRET_KEY);
		return client;
	}
	
	/**
	 * 百度AI人脸注册
	 */
	public String FaceReg(AipFace client) {
		// 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("user_info", "user's info");
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
		String image = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=282609871,2046421540&fm=58&bpow=2048&bpoh=2745";
		String imageType = "URL";
	    String groupId = "group1";
	    String userId = "user1";
	    // 人脸注册
	    JSONObject res = client.addUser(image, imageType, groupId, userId, options);
	    System.out.println(res.toString(2));
		return "";
	}
}
