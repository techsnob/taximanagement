package com.techsnob.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techsnob.entitiy.Vehicle;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
