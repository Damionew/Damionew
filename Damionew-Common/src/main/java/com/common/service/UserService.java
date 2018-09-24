/**
 * @author yinyunqi
 * @datetime 2018年9月23日
 * @Content 
 */
package com.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.mapper.UserMapper;
import com.common.model.User;
import com.common.utils.DateUtil;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public List<User> queryUser() {
		return userMapper.queryUser();
	}
	
	public int addUser(String username,String password,String nickname,String description,String icon) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setNickname(nickname);
		newUser.setDescription(description);
		newUser.setIcon(icon);
		String createDate = DateUtil.dateFormate2();
		newUser.setCreateDate(createDate);
		int cnt = userMapper.addUser(newUser);
		return cnt;
	}
	
	public int deleteUser(String userid) {
		int cnt = userMapper.deleteUser(userid);
		return cnt;
	}
	
	public User queryUserById(String userid) {
		return userMapper.queryUserById(userid);
	}
	
	public int updateUserById(String userid,String username,String password,String nickname,String description,String icon) {
		User newUser = new User();
		newUser.setUserid(userid);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setNickname(nickname);
		newUser.setDescription(description);
		newUser.setIcon(icon);
		String createDate = DateUtil.dateFormate2();
		newUser.setCreateDate(createDate);
		int cnt = userMapper.updateUserById(newUser);
		return cnt;
	}
}
