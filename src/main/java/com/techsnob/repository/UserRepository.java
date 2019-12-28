package com.techsnob.repository;

import com.techsnob.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{
	UserDetails findByUsername(String username);
	User findUserByUsername(String username);
	Optional<User> findByUsernameAndMobileNumber(String username, String MobileNumber);

	@Modifying
	@Query("UPDATE User SET password=?2 WHERE username=?1")
	void updatePassword(String username, String password);
}
