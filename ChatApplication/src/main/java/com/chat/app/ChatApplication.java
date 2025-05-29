package com.chat.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.chat.app.entity.User;
import com.chat.app.repository.UserRepository;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	//http://localhost:8080/login

	@Bean
	CommandLineRunner run(UserRepository userRepository, PasswordEncoder encoder) {
	    return args -> {
	        if (!userRepository.existsById("admin")) {
	            User user = new User();
	            user.setUsername("admin");
	            user.setPassword(encoder.encode("admin"));
	            user.setRole("ADMIN");
	            userRepository.save(user);
	        }
	    };
	}
}
