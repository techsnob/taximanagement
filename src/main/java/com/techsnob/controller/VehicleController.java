package com.techsnob.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techsnob.entitiy.Vehicle;
import com.techsnob.repository.VehicleRepository;

@RestController
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;

	@GetMapping("/getvehicle/{vehicleId}")
	public Optional<Vehicle> getVehicleDetails(@PathVariable Long vehicleId) {
		return vehicleRepository.findById(vehicleId);
	}
	
	@GetMapping("/vehicles")
	public Iterable<Vehicle> getVehicleDetails() {
		return vehicleRepository.findAll();
	}
	
	@GetMapping("/vehicleRCNumbers")
	public List<Map<String, String>> getvehicleRCNumbers() {
		return vehicleRepository.getRcNumbersWithIds();
	}
	
	@PostMapping(path="/putvehicle", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {"application/json"})
	public Vehicle putVehicle(@RequestParam("vehicleId") String vehicleId, @RequestParam("rcNumber") String rcNumber,
            @RequestParam("vehicleType") String vehicleType,
            @RequestParam("rcFile") MultipartFile rcFile,
            @RequestParam("fitness") MultipartFile fitness,
            @RequestParam("insurance") MultipartFile insurance,
            @RequestParam("taxsheet") MultipartFile taxsheet) throws IOException {
		Vehicle vehicle = new Vehicle(vehicleType, rcNumber, rcFile.getBytes(), rcFile.getContentType(), fitness.getBytes(), fitness.getContentType(), insurance.getBytes(), insurance.getContentType(), taxsheet.getBytes(), taxsheet.getContentType());
		if(!vehicleId.isEmpty()) {
			vehicle = vehicleRepository.findById(Long.valueOf(vehicleId)).get();
			vehicle.setVehicleId(Long.valueOf(vehicleId));
		}
		return vehicleRepository.save(vehicle);
	}
	
	@PostMapping(path = "/removevehicle", consumes = "application/json", produces = {"application/json"})
    public void removeDriver(@RequestBody String vehicleId) {
		if(!vehicleId.isEmpty()) {
		vehicleRepository.deleteById(Long.valueOf(vehicleId));
		}
    }
	
	
}
