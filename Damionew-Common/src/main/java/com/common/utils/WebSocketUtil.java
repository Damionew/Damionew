package com.common.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

/**
 * WebSocket工具类
 * @author yinyunqi
 *
 */
public final class WebSocketUtil {
	//模拟存储websocket session的使用
	public static final Map<String, Session> LIVING_SESSIONS_CACHE = new ConcurrentHashMap<>();
	
	public static void sendMessageAll(String message) {
		LIVING_SESSIONS_CACHE.forEach((sessionId,session) -> sendMessage(session, message));
	}
	// 发送消息给指定用户
	public static void sendMessage(Session session,String message) {
		if (session == null) {
			return;
		}
		final RemoteEndpoint.Basic basic = session.getBasicRemote();
		if (basic == null) {
			return;
		}
		try {
			basic.sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
