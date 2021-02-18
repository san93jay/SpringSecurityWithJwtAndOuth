/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.exception;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
public class UsernameNotUniqueException extends Exception{
	private static final long serialVersionUID = 1L;

	public UsernameNotUniqueException(String username) {
        super(String.format("Username Already Registered with: "+username));
	}

}
