package com.jwt.SpringSecurityWithJwtAndOuth.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@Data
public class SignUpRequestForm {
	@NotBlank
	@Size(min = 3, max = 50)
	private String name;

	@NotBlank
	@Size(min = 3, max = 50)
	@Email
	private String username;

	private String role;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	private String active;
	private String contact;
	private String address;
	private String country;

}
