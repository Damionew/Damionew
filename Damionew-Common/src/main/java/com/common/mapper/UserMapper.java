package com.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.common.model.User;

@Mapper
public interface UserMapper {
	public void insertTest();
	public void insertUser(User user);
	public User findUserByUserName(String username);
	public List<Map<String, String>> findUserRoleByUserName(String username);
}
