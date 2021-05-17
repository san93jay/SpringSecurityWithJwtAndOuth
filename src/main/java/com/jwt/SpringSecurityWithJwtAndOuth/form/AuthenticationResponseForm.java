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
public class AuthenticationResponseForm {
	
	private String accessToken;
	private String type = "Bearer";
	private String userName;
	private String userRole;
	private String name;
	public AuthenticationResponseForm(String accessToken){
		this.accessToken=accessToken;
	}
}
