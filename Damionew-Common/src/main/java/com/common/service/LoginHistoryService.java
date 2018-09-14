package com.common.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.mapper.LoginHistoryMapper;

@Service
public class LoginHistoryService {
	@Autowired
	LoginHistoryMapper loginHistoryMapper;
	public void insertLoginHistory(Map<String, String> map) {
//		map.put("username", UserInfoUtil.getCurUsername());
//		map.put("date", DateUtil.dateFormate1());
		loginHistoryMapper.insertLoginHistory(map);
	}
	public String selectCountVistors() {
		String count = loginHistoryMapper.selectCountVistors();
		return count;
	}
}
