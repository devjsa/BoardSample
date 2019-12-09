package com.example.board.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.board.basic.Constants;
import com.example.board.security.AuthFailureHandler;
import com.example.board.security.AuthProvider;
import com.example.board.security.AuthSuccessHandler;

@Configuration
@EnableGlobalAuthentication
@EnableWebSecurity
@ComponentScan(basePackages = {"com.example.*"})
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    AuthProvider authProvider;
    
    @Autowired
    AuthFailureHandler authFailureHandler;
 
    @Autowired
    AuthSuccessHandler authSuccessHandler;
 
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 허용되어야 할 경로들
        web.ignoring().antMatchers("/resources/**", 
                                   "/static/**", 
                                   "/webjars/**", 
                                   "/kakao/**",
                                   "/kauth/**",
                                   "/oauth2/**",
                                   "/user/password/find",
                                   "/user/join",
                                   "/user/email",
                                   "/user/send/temppw",
                                   "/user/findpw",
                                   "/user/cert/check",
                                   "/getLanguage/**",
                                   "/getMessage"); 
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.authorizeRequests()
            .antMatchers( "/login", "/error**").permitAll()
            .antMatchers("/**").access(Constants.ROLE_USER)
            .antMatchers("/**").access(Constants.ROLE_ADMIN)
            .antMatchers("/admin/**").access(Constants.ROLE_ADMIN)
            .antMatchers("/**").authenticated()
        .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .loginProcessingUrl("/loginProc")
            .failureHandler(authFailureHandler)
            .successHandler(authSuccessHandler)
            .usernameParameter("email")
            .passwordParameter("password")
        .and()    
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
        .and()
            .csrf()
        .and()
            .authenticationProvider(authProvider);
    }

	
}
