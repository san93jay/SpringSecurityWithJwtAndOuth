/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.SpringSecurityWithJwtAndOuth.model.User;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByusername(String username);

	boolean existsByUsername(String username);

}
