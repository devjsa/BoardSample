package com.example.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.board.vo.User;

@Mapper
@Repository(value = "userMapper")
public interface UserMapper {
	
	public User selectUserByEmail(User user);
	
	public String getNow();
	

}
