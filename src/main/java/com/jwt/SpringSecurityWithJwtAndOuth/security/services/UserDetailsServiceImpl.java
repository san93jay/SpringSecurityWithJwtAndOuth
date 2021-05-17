/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.security.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.SpringSecurityWithJwtAndOuth.model.User;
import com.jwt.SpringSecurityWithJwtAndOuth.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository UserRepository;
	@Autowired
	HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=null;
		try {
			user=UserRepository.findByusername(username);
			if(user.isPresent()) {
				User usersession=user.get();
		        session.setAttribute("userRole", usersession.getRole().getName().name());
		        session.setAttribute("userId", usersession.getId());
			}
		    user.orElseThrow(()-> new UsernameNotFoundException("Username not found:"+username));
			
		}catch (Exception e) {
			log.error("/UserDetailsServiceImpl : "+e);
		}		
		return user.map(UserPrincipal::new).get();
	}

}
