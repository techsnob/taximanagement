
package com.techsnob.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techsnob.entitiy.Role;
import com.techsnob.entitiy.User;
import com.techsnob.repository.RoleRepository;
import com.techsnob.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}
	
	public void saveUser(User user) {
        user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setDateCreated(new Date());
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		if(loadUserByUsername(user.getUsername()) == null) {
			userRepository.save(user);
		} else {
			throw new RuntimeException(String.format("Username '%s' already exists!", user.getUsername().toUpperCase()));
		}
    }

	public User loadUserByUsernameAndMobileNumber(String username, String mobileNumber) throws UsernameNotFoundException {
		return userRepository.findByUsernameAndMobileNumber(username, mobileNumber).orElseThrow(() -> new RuntimeException(String.format("User '%s' doesn't exists, please check your username and mobile number.", username)));
	}

	public User findUserByUsername(String username){
		return userRepository.findUserByUsername(username);
	}

	public void updatePassword(User user) {
		userRepository.updatePassword(user.getUsername(), bcryptPasswordEncoder.encode(user.getPassword()));
	}
}
