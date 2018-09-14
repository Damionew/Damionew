package com.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public  class UserInfoUtil {
	public static String getCurUsername() {
		String username = "";
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			username = "机器人sa";
		}else {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
			username = userDetails.getUsername();
		}
		
		return username;
	}
}
