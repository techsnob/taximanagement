package com.techsnob.controller;

import com.techsnob.entitiy.User;
import com.techsnob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/registration", 
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> register(@RequestBody User user) {
		userService.saveUser(user);
		return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }
	
	@GetMapping("/loggeduser")
    public ResponseEntity<Object> loggeduser() {
		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), HttpStatus.OK);
		
    }

	@PostMapping("/confirmuser")
	public ResponseEntity<Object> confirmuser(@RequestBody User user) {
		return new ResponseEntity<>(userService.loadUserByUsernameAndMobileNumber(user.getUsername(), user.getMobileNumber()) != null, HttpStatus.OK);
	}

	@PostMapping("/updatePassword")
	public ResponseEntity<Object> updatePassword(@RequestBody User user) {
		//User user1 = userService.findUserByUsername(user.getUsername());
		userService.updatePassword(user);
		return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
	}

	@GetMapping(value="/login-error")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String errorMessage = null;
		if (session != null) {
			AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (ex != null) {
				errorMessage = ex.getMessage();
			}
		}
		return errorMessage != null ? errorMessage : "User not found!";
	}

}
