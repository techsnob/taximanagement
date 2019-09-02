package com.techsnob.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techsnob.entitiy.Driver;
import com.techsnob.repository.DriverRepository;

@RestController
public class DriverController {
	
	@Autowired
	private DriverRepository driverRepository;
	
	@GetMapping("/getDriverDetails/{driverId}")
	public Optional<Driver> getDriverDetails(@PathVariable Long driverId) {
		return driverRepository.findById(driverId);
	}
	
	@GetMapping("/getAllDriverDetails")
	public Iterable<Driver> getAllDriverDetails() {
		return driverRepository.findAll();
	}
	
	@PostMapping(path="/insertDriverDetails", consumes= {"application/json"}, produces= {"application/json"})
	public Driver insertDriverDetails(@RequestBody Driver driver) {
		return driverRepository.save(driver);
	}

}
