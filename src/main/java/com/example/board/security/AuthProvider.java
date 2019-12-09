package com.example.board.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.board.basic.Constants;
import com.example.board.service.UserService;
import com.example.board.vo.EnumLoginType;
import com.example.board.vo.User;
import com.example.board.vo.UserAuth;

@Component("authProvider")
public class AuthProvider implements AuthenticationProvider {

	
	@Autowired
	UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		
		String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        User user = userService.selectUserByEmail(User.builder().email(email).build());
        
        PasswordEncoding encoder = new PasswordEncoding();
        if (null == user || !encoder.matches(password, user.getPassword()) ) {
            return null;
        }
        
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        
        // 로그인한 계정에게 권한 부여
        if (user.isAdmin()) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(Constants.ROLE_ADMIN));
        } else {
            grantedAuthorityList.add(new SimpleGrantedAuthority(Constants.ROLE_USER));
        }
 
        // 로그인 성공시 로그인 사용자 정보 반환
        return new UserAuth(email, password, grantedAuthorityList, user, EnumLoginType.NORMAL);

	}

	@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

	
}
