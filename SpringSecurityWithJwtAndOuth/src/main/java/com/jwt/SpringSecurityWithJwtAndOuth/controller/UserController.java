/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@RestController
@RequestMapping(value="/api/user")
@Log4j2
public class UserController {
	
	@GetMapping(value = "/hello")
	public String helloGetRequest() {
		log.info("Get User send successfully");
		return "Hello get User";
	}

}
