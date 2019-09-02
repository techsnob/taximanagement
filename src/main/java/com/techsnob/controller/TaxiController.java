/*package com.techsnob.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techsnob.entitiy.Vehicle;
import com.techsnob.repository.TaxiRepository;

@RestController
public class TaxiController {
	
	private TaxiRepository taxiRepository;

	@GetMapping("/getTaxiDetails/{taxiId}")
	public Optional<Vehicle> getTaxiDetails(@PathVariable String taxiId) {
		return taxiRepository.findById(taxiId);
	}
	
	@GetMapping("/getAllTaxiDetails")
	public Iterable<Vehicle> getAllTaxiDetails() {
		return taxiRepository.findAll();
	}
	
	@PostMapping("/insertTaxiDetails")
	public Vehicle insertTaxiDetails(@RequestBody Vehicle taxi) {
		return taxiRepository.save(taxi);
	}
	
	
}
*/