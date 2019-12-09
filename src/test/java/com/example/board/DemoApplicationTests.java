package com.example.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DemoApplicationTests {
	
	@Autowired
	UserService userService;

	@Test
	void getNow() {
		
		log.info(userService.getNow());
	}

}
