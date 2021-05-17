/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.SpringSecurityWithJwtAndOuth.model.UserToken;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

}
