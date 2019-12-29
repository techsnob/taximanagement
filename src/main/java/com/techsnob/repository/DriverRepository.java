package com.techsnob.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techsnob.entitiy.Driver;

@Repository
@Transactional
public interface DriverRepository extends CrudRepository<Driver, Long> {
    @Modifying
    @Query("UPDATE Driver SET firstName=?1, lastName=?2, phoneNumber=?3 WHERE driverId=?4")
    void updateDriver(String firstName, String lastName, Long phoneNumber, Long driverId);
}
