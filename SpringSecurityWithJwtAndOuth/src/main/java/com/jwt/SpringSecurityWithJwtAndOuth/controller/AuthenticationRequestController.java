package com.jwt.SpringSecurityWithJwtAndOuth.controller;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.jwt.SpringSecurityWithJwtAndOuth.dto.UserDto;
import com.jwt.SpringSecurityWithJwtAndOuth.exception.MyException;
import com.jwt.SpringSecurityWithJwtAndOuth.exception.UsernameNotUniqueException;
import com.jwt.SpringSecurityWithJwtAndOuth.form.AuthenticationRequestForm;
import com.jwt.SpringSecurityWithJwtAndOuth.form.AuthenticationResponseForm;
import com.jwt.SpringSecurityWithJwtAndOuth.form.SignUpRequestForm;
import com.jwt.SpringSecurityWithJwtAndOuth.model.UserToken;
import com.jwt.SpringSecurityWithJwtAndOuth.security.jwt.JwtProvider;
import com.jwt.SpringSecurityWithJwtAndOuth.service.UserService;
import com.jwt.SpringSecurityWithJwtAndOuth.service.UserTokenService;
import lombok.extern.log4j.Log4j2;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@RestController
@Log4j2
@RequestMapping(value = "/api/auth")
public class AuthenticationRequestController {

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserTokenService userTokenService;
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/signIn")
	public ResponseEntity<?> signIn(@RequestBody AuthenticationRequestForm authenticationRequestForm,
			HttpServletRequest request) {
		AuthenticationResponseForm authenticationResponseForm = null;
		try {
			HttpSession session = request.getSession();
			session.setAttribute("username", authenticationRequestForm.getUsername());
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequestForm.getUsername(), authenticationRequestForm.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwtToken = jwtProvider.generateJwtToken(authentication);
			UserToken userToken = userTokenService.saveUserToken(authenticationRequestForm, request, jwtToken);
			authenticationResponseForm = new AuthenticationResponseForm(jwtToken);
			authenticationResponseForm.setName(userToken.getUser().getName());
			authenticationResponseForm.setUserName(userToken.getUser().getUsername());
			authenticationResponseForm.setUserRole(userToken.getUser().getRole().getName().name());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("/signIn");
		}
		log.info("/signIn , token send successfully");
		return ResponseEntity.ok(authenticationResponseForm);
	}

	@PostMapping(value = "/signUp")
	public @ResponseBody ResponseEntity<UserDto> createUserDetails(@Valid @RequestBody SignUpRequestForm signUpRequestForm,
			BindingResult result) throws ParseException, MyException, UsernameNotUniqueException {
            UserDto userDetails = userService.createUser(signUpRequestForm);	
		    log.info("Successfully created user with id: "+userDetails.getId());
		    return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}
}
