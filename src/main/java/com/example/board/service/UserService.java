package com.example.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.mapper.UserMapper;
import com.example.board.vo.User;

@Transactional
@Service(value = "userServcice")
public class UserService {

	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	public String getNow() {
		
		return userMapper.getNow();
	}
	
	public User selectUserByEmail(User user) {
		
		return userMapper.selectUserByEmail(user);
	}
	
	
	
}
