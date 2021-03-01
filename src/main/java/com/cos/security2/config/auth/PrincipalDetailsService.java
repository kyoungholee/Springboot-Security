package com.cos.security2.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security2.model.User;
import com.cos.security2.repository.UserRepository;


// 시큐리티 설정에서 loginProcessionUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService타입으로 IoC되어 있는 loadUserByUsername 함수가 실행된다. 
@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	//시큐리티 session(Authentication(UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		
		return null;
	}

}
