/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.service;

import javax.servlet.http.HttpServletRequest;

import com.jwt.SpringSecurityWithJwtAndOuth.form.AuthenticationRequestForm;
import com.jwt.SpringSecurityWithJwtAndOuth.model.UserToken;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
public interface UserTokenService {
	UserToken saveUserToken(AuthenticationRequestForm authenticationRequestForm, HttpServletRequest request, String userToken);

}
