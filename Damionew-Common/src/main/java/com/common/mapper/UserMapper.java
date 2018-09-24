package com.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.common.model.User;

@Mapper
public interface UserMapper {
	public void insertTest();
	public void insertUser(User user);
	public User findUserByUserName(String username);
	public List<User> queryUser();
	public int addUser(User user);
	public int deleteUser(String userid);
	public User queryUserById(String userid);
	public int updateUserById(User user);
}
