package com.door.service;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.baidu.aip.face.AipFace;

/**
 * 人脸识别业务逻辑
 */

@Service
public class FaceService {
	
	public static final String APP_ID = "14482860";
	public static final String API_KEY  = "EBQ8L5ZtOf4gO7sSHOM27YGv";
	public static final String SECRET_KEY = "OTWEfVZyjT3lRb9WuUn5yaYAGO1TNnny";
	//初始化一个AipFace，单例使用，避免重复获取access_token
	public static final AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
	
	/**
	 * 获取用户人脸列表
	 * @throws JSONException 
	 */
	public void getUserFace() throws JSONException {
		// 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    String userId = "user1";
	    String groupId = "group1";
	    // 获取用户人脸列表
	    JSONObject res = client.faceGetlist(userId, groupId, options);
	    System.out.println(res.toString(2));
	}
	
	/**
	 * 获取用户信息
	 * @throws JSONException 
	 */
	public String getUserInfo(String userId,String groupId) throws JSONException {
		// 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    // 用户信息查询
	    JSONObject res = client.getUser(userId, groupId, options);
	    // 返回error_msg代表状态
	    String error_msg = (String) res.get("error_msg");
	    if(error_msg.equals("SUCCESS")) {
	    	/*
	    	 * 返回json串如下
	    	 */
	    	JSONObject result = res.getJSONObject("result");
	    	JSONArray user_list = result.getJSONArray("user_list");
	    	JSONObject user_info2 = user_list.getJSONObject(0);
	    	String user_info = user_info2.getString("user_info");
	    	return user_info;
	    }else {
	    	String user_info ="";
	    	return user_info;
	    }

	}
	
	/**
	 * 获取用户列表
	 * @throws JSONException 
	 */
	public JSONArray getUser(String groupId) throws JSONException {
		 // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("start", "0");
	    options.put("length", "50");
	    // 获取用户列表
	    JSONObject res = client.getGroupUsers(groupId, options);
	    // 返回error_msg代表状态
	    String error_msg = (String) res.get("error_msg");
	    if(error_msg.equals("SUCCESS")) {
	    	/*
	    	 * 返回json串如下
	    	 */
	    	JSONObject result = res.getJSONObject("result");
	    	JSONArray user_id_list = result.getJSONArray("user_id_list");
	    	return user_id_list;
	    }else {
	    	JSONArray user_id_list = new JSONArray();
	    	return user_id_list;
	    }
	}
	
	/**
	 * 获取组列表
	 * @throws JSONException 
	 */
	public JSONArray getgroup() throws JSONException {
		// 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("start", "0");
	    options.put("length", "50");
	    // 组列表查询
	    JSONObject res = client.getGroupList(options);
	    String error_msg = (String) res.get("error_msg");
	    if(error_msg.equals("SUCCESS")) {
	    	/*
	    	 * 返回json串如下
	    	 * {"result":{"group_id_list":["group1","test_group"]},
	    	 * "log_id":1515457525550,"error_msg":"SUCCESS","cached":0,"error_code":0,
	    	 * "timestamp":1540024885}
	    	 */
	    	JSONObject result = res.getJSONObject("result");
	    	JSONArray group_id_list = result.getJSONArray("group_id_list");
	    	return group_id_list;
	    }else {
	    	JSONArray group_id_list = new JSONArray();
	    	return group_id_list;
	    }
	}
	
	public String regFace(String imageUrl,String userId,String groupId,String userInfo) throws JSONException {
		// 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("user_info", userInfo);
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
	    
	    String image = imageUrl;
	    String imageType = "URL";
	    
	    // 人脸注册
	    JSONObject res = client.addUser(image, imageType, groupId, userId, options);
	    String error_msg = (String) res.get("error_msg");
	    if(error_msg.equals("SUCCESS")) {
	    	/*
	    	 * 返回json串如下
	    	 * {"result":{"group_id_list":["group1","test_group"]},
	    	 * "log_id":1515457525550,"error_msg":"SUCCESS","cached":0,"error_code":0,
	    	 * "timestamp":1540024885}
	    	 */
	    	return "success";
	    }else {
	    	return "error";
	    }
	}
}
