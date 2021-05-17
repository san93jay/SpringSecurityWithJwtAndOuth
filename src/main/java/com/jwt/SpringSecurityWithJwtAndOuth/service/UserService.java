/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.service;

import com.jwt.SpringSecurityWithJwtAndOuth.dto.UserDto;
import com.jwt.SpringSecurityWithJwtAndOuth.exception.UsernameNotUniqueException;
import com.jwt.SpringSecurityWithJwtAndOuth.form.SignUpRequestForm;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
public interface UserService {
	public UserDto createUser(SignUpRequestForm signUpRequestForm) throws UsernameNotUniqueException;

}
