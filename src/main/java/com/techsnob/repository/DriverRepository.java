package com.techsnob.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techsnob.entitiy.Driver;

@Repository
@Transactional
public interface DriverRepository extends CrudRepository<Driver, Long> {

}
