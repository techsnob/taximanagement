package com.techsnob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsnob.entitiy.User;
import com.techsnob.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registration")
    public void register(User user) {
		userService.saveUser(user);;
    }

}
