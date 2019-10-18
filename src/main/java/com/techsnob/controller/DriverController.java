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
    public Driver insertDriverDetails(@RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName,
                                      @RequestParam("phoneNumber") Long phoneNumber,
                                      @RequestParam("aadhaar") MultipartFile aadhaar,
                                      @RequestParam("license") MultipartFile license) throws IOException {
        Driver driver = new Driver(firstName, lastName, phoneNumber, aadhaar.getBytes(), aadhaar.getContentType(), license.getBytes(),  license.getContentType());
        return driverRepository.save(driver);
    }
    
    @PostMapping(path = "/updateDriver", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {"application/json"})
    public void updateDriver(@RequestParam("driverId") String driverId, @RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName,
                                      @RequestParam("phoneNumber") Long phoneNumber,
                                      @RequestParam("aadhaar") MultipartFile aadhaar,
                                      @RequestParam("license") MultipartFile license) throws IOException {
    	Driver driver = driverRepository.findById(Long.valueOf(driverId)).get();
    	driver.setDriverId(Long.valueOf(driverId));
    	driver.setFirstName(firstName);
    	driver.setLastName(lastName);
    	driver.setPhoneNumber(phoneNumber);
    	driver.setAadhaar(aadhaar.getBytes());
    	driver.setLicense(license.getBytes());
    	driver.setAadhaar_contenttype(aadhaar.getContentType());
    	driver.setLicense_contenttype(license.getContentType());
        driverRepository.save(driver);
    }
    
    @PostMapping(path = "/removedriver", consumes = "application/json", produces = {"application/json"})
    public void removeDriver(@RequestBody Driver driver) {
    	driverRepository.delete(driver);
    }

}
