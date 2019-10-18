package com.techsnob.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsnob.entitiy.BankAccount;
import com.techsnob.repository.BankAccountRepository;

@RestController
public class BankAccountController {
	
	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@GetMapping("/getAccountDetail/{accountId}")
	public Optional<BankAccount> getDriverDetails(@PathVariable String accountId) throws JsonProcessingException {
		return bankAccountRepository.findById(Long.valueOf(accountId));
	}
	
	@GetMapping("/accounts")
	public Iterable<BankAccount> getAllDriverDetails() throws JsonProcessingException {
		return bankAccountRepository.findAll();
	}
	
	@PostMapping(path="/insertAccount", consumes= {"application/json"}, produces= {"application/json"})
	public BankAccount insertDriverDetails(@RequestBody BankAccount bankAccount) throws JsonProcessingException {
		return bankAccountRepository.save(bankAccount);
	}
	
	@PostMapping(path = "/removeAccount", consumes = "application/json", produces = {"application/json"})
    public void removeAccount(@RequestBody String accountId) {
		if(!accountId.isEmpty()) {
			bankAccountRepository.deleteById(Long.valueOf(accountId));
		}
    }

}
