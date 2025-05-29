package com.chat.app.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chat.app.entity.User;
import com.chat.app.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User u = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user: " + username));
   return new org.springframework.security.core.userdetails.User(
       u.getUsername(),
       u.getPassword(),
       Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + u.getRole()))
   );
	}

}
