/**
 * @author yinyunqi
 * @datetime 2018年4月10日
 * @Content 
 */
package com.auth.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.mapper.UserMapper;
import com.auth.model.User;

@Service
public class CustomUserService implements UserDetailsService{
	@Autowired
	UserMapper userMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userMapper.findUserByUserName(username);  
	        if (user == null) {  
	        	
	            throw new UsernameNotFoundException("用户名："+username+"不存在");  
	        }  
	        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();  
	        List<Map<String, String>> roleList = userMapper.findUserRoleByUserName(username);  
	          
	        for (Map<String, String> role : roleList) {  
	            GrantedAuthority authority = new SimpleGrantedAuthority(role.get("rolecode"));  
	            authorities.add(authority);  
	        }  
	        user.setAuthorities(authorities);  
	        return user;  
	    }  
	}

