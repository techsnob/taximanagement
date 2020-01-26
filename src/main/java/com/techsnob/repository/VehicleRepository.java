package com.techsnob.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techsnob.entitiy.Vehicle;

@Repository
@Transactional
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	@Query("select new map (vehicleId as vehicleId, rcNumber as rcNumber) from Vehicle")
	List<Map<String, String>> getRcNumbersWithIds();

	@Modifying
	@Query("UPDATE Vehicle SET vehicleType=?1,rcNumber=?2 WHERE vehicleId=?3")
	void updateVehicle(String vehicleType, String rcNumber, Long vehicleId);

	Optional<Vehicle> findByRcNumber(String rcNumber);
}
