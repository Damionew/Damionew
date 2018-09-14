package com.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.exception.TestException;
import com.common.mapper.UserMapper;
import com.common.model.User;


@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	public void test() {
		userMapper.insertTest();
	}
	
	// 抛出RuntimeException异常事务回滚
	@Transactional
	public void transactionalTest() throws Exception {
		User user = new User();
		user.setUserid("test1");
		userMapper.insertUser(user);
//		throw new RuntimeException();
		throw new TestException("这是TestException");
	}
	
	// 抛出Exception异常事务不回滚
	@Transactional
	public void transactionalTest2() throws Exception {
		User user = new User();
		user.setUserid("test2");
		userMapper.insertUser(user);
		throw new Exception("这是TestException2");
	}
	
	// 抛出Exception异常事务回滚
	@Transactional(rollbackFor=Exception.class)
	public void transactionalTest3() throws Exception {
		User user = new User();
		user.setUserid("test3");
		userMapper.insertUser(user);
		throw new Exception("这是TestException3");
	}
}
