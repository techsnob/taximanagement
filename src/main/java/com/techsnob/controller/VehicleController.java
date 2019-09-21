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
public class VehicleController {
	
	private VehicleRepository vehicleRepository;

	@GetMapping("/getvehicle/{vehicleId}")
	public Optional<Vehicle> getVehicleDetails(@PathVariable String vehicleId) {
		return vehicleRepository.findById(vehicleId);
	}
	
	@GetMapping("/vehicles")
	public Iterable<Vehicle> getVehicleDetails() {
		return vehicleRepository.findAll();
	}
	
	@PostMapping("/putvehicle")
	public Vehicle putVehicle(@RequestBody Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	
}
