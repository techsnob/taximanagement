package com.techsnob.service;

import com.techsnob.entitiy.Vehicle;
import com.techsnob.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    boolean getVehicleByRcNumber(String rcNumber) {
        return vehicleRepository.findByRcNumber(rcNumber).isPresent();
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        if(!getVehicleByRcNumber(vehicle.getRcNumber())) {
            return vehicleRepository.save(vehicle);
        } else {
            throw new RuntimeException(String.format("Vehicle already exists with %s.", vehicle.getRcNumber()));
        }
    }
}
