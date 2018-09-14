package com.common.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginHistoryMapper {
	/** 插入用户登录历史记录 **/
	public void insertLoginHistory(Map<String,String> map);
	/** 计算登录浏览数  **/
	public String selectCountVistors();
}
