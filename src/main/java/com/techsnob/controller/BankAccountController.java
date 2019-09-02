package com.techsnob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsnob.entitiy.BankAccount;
import com.techsnob.repository.BankAccountRepository;

@RestController
public class BankAccountController {
	
	@Autowired
	private BankAccountRepository bankAccountRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	@GetMapping("/getAccountDetail/{accountId}")
	public String getDriverDetails(@PathVariable String accountId) throws JsonProcessingException {
		return objectMapper.writeValueAsString(bankAccountRepository.findById(accountId));
	}
	
	@GetMapping("/getAllAccountDetails")
	public String getAllDriverDetails() throws JsonProcessingException {
		return objectMapper.writeValueAsString(bankAccountRepository.findAll());
	}
	
	@PostMapping(path="/insertAccountDetails", consumes= {"application/json"}, produces= {"application/json"})
	public String insertDriverDetails(@RequestBody BankAccount bankAccount) throws JsonProcessingException {
		return objectMapper.writeValueAsString(bankAccountRepository.save(bankAccount));
	}

}
