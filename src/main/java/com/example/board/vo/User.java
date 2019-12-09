package com.example.board.vo;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import com.example.board.basic.Constants;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("user")
public class User {
	
	private String email;
	private String username;
	private String password;
	private int failedCount;
	private boolean isKakaoLogin;
	private boolean isEnabled;
	private Date createdAt;
	private Date lockedAt;
	private Date leavedAt;
	private String authority;
	
	private String sessionState;
	
	public boolean isAdmin() {
		return this.authority.equals(Constants.ROLE_ADMIN);
	}
	
	@Builder
	public User(String email) {
		this.email = email;
		sessionState = Constants.LOGIN_SESSION_STATUS_LOGOUT;
	}
	
	
}
