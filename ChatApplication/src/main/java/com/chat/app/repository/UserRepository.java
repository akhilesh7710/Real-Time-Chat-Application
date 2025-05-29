package com.chat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.app.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	

}
