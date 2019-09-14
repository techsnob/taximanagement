package com.techsnob.repository;

import com.techsnob.entitiy.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AddressRepository extends CrudRepository<Address, Long> {
}
