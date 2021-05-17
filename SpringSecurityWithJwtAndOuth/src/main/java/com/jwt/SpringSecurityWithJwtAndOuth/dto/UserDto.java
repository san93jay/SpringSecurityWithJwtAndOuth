/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.dto;

import com.jwt.SpringSecurityWithJwtAndOuth.model.Role;

import lombok.Data;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@Data
public class UserDto {
	    private Long id;
	    private String name;
		private String username;
		private Role role;
	    private String active;
		private String contact;
		private String address;
		private String country;
		private String password;

}
