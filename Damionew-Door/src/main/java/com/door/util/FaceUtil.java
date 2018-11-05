package com.door.util;

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
	
}
