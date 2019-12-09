package com.example.board.vo;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserAuth extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 7191704674217583221L;
	
	private String userEmail;
	private User user;
	private EnumLoginType loginType;
	
	public UserAuth(String email, String password, List<GrantedAuthority> grantedAuthorityList, User user, EnumLoginType loginType) {
	    super(email, password, grantedAuthorityList);
	    this.user = user;
	    this.loginType = loginType;
	    this.userEmail = email;
	}
	
}



