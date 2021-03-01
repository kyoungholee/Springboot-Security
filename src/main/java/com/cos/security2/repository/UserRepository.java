package com.cos.security2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security2.model.User;


// CRUD 함수를 JPaRepository 가 들고 있음. 중요하다 !!!! 
// @Repository라는 어노테이션이 없어도 loC가 된다. 이유는 JpaRepository 를 상속했기 때문이다.... 
//자동으로  bean 등록이 된다. 
public interface UserRepository extends JpaRepository<User, Integer>{
	//findBy규칙 -> Username 문법
	//select * from user where username = 
	
	
	public User findByUsername(String username); //JPa Query methods
	

}
