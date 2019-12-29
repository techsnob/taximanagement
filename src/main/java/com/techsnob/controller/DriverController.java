package com.techsnob.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techsnob.entitiy.Driver;
import com.techsnob.repository.DriverRepository;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DriverController {

    private DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping("/getDriverDetails/{driverId}")
    public Optional<Driver> getDriverDetails(@PathVariable Long driverId) {
        return driverRepository.findById(driverId);
    }

    @GetMapping("/drivers")
    public Iterable<Driver> getAllDriverDetails() {
        return driverRepository.findAll();
    }

    @PostMapping(path = "/insertDriver", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {"application/json"})
    public Driver insertDriverDetails(@RequestParam("driverId") String driverId,
                                      @RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName,
                                      @RequestParam("phoneNumber") Long phoneNumber,
                                      @RequestParam("aadhaar") MultipartFile aadhaar,
                                      @RequestParam("license") MultipartFile license) throws IOException {
        Driver driver = new Driver(firstName, lastName, phoneNumber, aadhaar.getBytes(), aadhaar.getContentType(), license.getBytes(),  license.getContentType());
        if(!driverId.isEmpty()){
            driver = driverRepository.findById(Long.parseLong(driverId)).get();
            driver.setDriverId(Long.parseLong(driverId));
        }
        return driverRepository.save(driver);
    }
    
    @PostMapping(path = "/updateDriver", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {"application/json"})
    public void updateDriver(@RequestBody Driver driver) throws IOException {
        driverRepository.updateDriver(driver.getFirstName(), driver.getLastName(), driver.getPhoneNumber(), driver.getDriverId());
    }
    
    @PostMapping(path = "/removedriver", consumes = "application/json", produces = {"application/json"})
    public void removeDriver(@RequestBody Driver driver) {
    	driverRepository.delete(driver);
    }

}
