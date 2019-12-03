package com.techsnob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techsnob.entitiy.User;
import com.techsnob.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registration")
    public ResponseEntity<Object> register(@RequestBody User user) {
		userService.saveUser(user);
		return new ResponseEntity<Object>(user.getUsername(), HttpStatus.OK);
    }
	
	@GetMapping("/loggeduser")
    public ResponseEntity<Object> loggeduser() {
		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), HttpStatus.OK);
		
    }

}
