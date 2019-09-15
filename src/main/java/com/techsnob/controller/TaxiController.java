package com.techsnob.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techsnob.entitiy.Vehicle;
import com.techsnob.repository.VehicleRepository;

@RestController
public class TaxiController {
	
	private VehicleRepository vehicleRepository;

	@GetMapping("/getTaxiDetails/{taxiId}")
	public Optional<Vehicle> getTaxiDetails(@PathVariable String taxiId) {
		return vehicleRepository.findById(taxiId);
	}
	
	@GetMapping("/vehicles")
	public Iterable<Vehicle> getAllTaxiDetails() {
		return vehicleRepository.findAll();
	}
	
	@PostMapping("/insertTaxiDetails")
	public Vehicle insertTaxiDetails(@RequestBody Vehicle taxi) {
		return vehicleRepository.save(taxi);
	}
	
	
}
