/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.form;

import lombok.Data;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@Data
public class AuthenticationRequestForm {
	private String username;
	private String password;
}
