/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jwt.SpringSecurityWithJwtAndOuth.model.Role;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query(value = "SELECT * FROM roles WHERE name=:roleName", nativeQuery = true)
	Optional<Role> findByName(@Param("roleName") String roleName);

}
