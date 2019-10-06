package com.techsnob.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techsnob.entitiy.Vehicle;

@Repository
@Transactional
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	@Query("select new map (vehicleId as vehicleId, rcNumber as rcNumber) from Vehicle")
	List<Map<String, String>> getRcNumbersWithIds();
}
