package com.techsnob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techsnob.entitiy.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{
	UserDetails findByUsername(String username);
}
