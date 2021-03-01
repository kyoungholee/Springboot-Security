package com.cos.security2.config;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity // <활성화> 스프링 필터 체인에 등록 
@EnableGlobalMethodSecurity(securedEnabled = true)
//secured 어노테이션 활성화 
//preAuthorize 어노테이션 활성화 

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	//해당 메서드의 리턴되는 값을 IoC로 등록해준다.
	//쉽게 말해서 쓰고 싶을때 쓰게 해준다.  bean을 적으면 !! 
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //비활성화 
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated() //인증 
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/login") //login 주소가 호출이되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
			.defaultSuccessUrl("/");
		
	}
}
