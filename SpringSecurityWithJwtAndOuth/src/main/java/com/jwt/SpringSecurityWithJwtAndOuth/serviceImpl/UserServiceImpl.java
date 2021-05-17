/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.serviceImpl;

import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jwt.SpringSecurityWithJwtAndOuth.dto.UserDto;
import com.jwt.SpringSecurityWithJwtAndOuth.exception.MyException;
import com.jwt.SpringSecurityWithJwtAndOuth.exception.UsernameNotUniqueException;
import com.jwt.SpringSecurityWithJwtAndOuth.form.SignUpRequestForm;
import com.jwt.SpringSecurityWithJwtAndOuth.model.Role;
import com.jwt.SpringSecurityWithJwtAndOuth.model.User;
import com.jwt.SpringSecurityWithJwtAndOuth.repository.RoleRepository;
import com.jwt.SpringSecurityWithJwtAndOuth.repository.UserRepository;
import com.jwt.SpringSecurityWithJwtAndOuth.service.UserService;

import lombok.extern.log4j.Log4j2;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@Service
@Log4j2
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private MessageSource messageSource;
	private Locale locale;


	@Override
	public UserDto createUser(SignUpRequestForm signUpRequestForm) throws UsernameNotUniqueException {
		User user = null;
		UserDto userDetails = null;

		if (userRepository.existsByUsername(signUpRequestForm.getUsername())) {
			throw new UsernameNotUniqueException(signUpRequestForm.getUsername());
		}
		user = modelMapper.map(signUpRequestForm, User.class);
		String loginUserRole = (String) session.getAttribute("userRole");
		

		try {
			String role = "ROLE_".concat(signUpRequestForm.getRole());
			Role strRole = new Role();
			if(loginUserRole!=null) {
				if (loginUserRole.equals("ROLE_CLIENT_ADMIN")) {
					
					Role sadminRole = roleRepository.findByName(role).get();
					strRole.setId(sadminRole.getId());
					strRole.setName(sadminRole.getName());
					
				} else if (loginUserRole.equals("ROLE_CLIENT_USER") && !role.equals("ROLE_CLIENT_ADMIN")) {
					
					Role cAdminRole = roleRepository.findByName(role).get();
					strRole.setId(cAdminRole.getId());
					strRole.setName(cAdminRole.getName());
				} else {
					throw new MyException(messageSource.getMessage("createUser.notAllowed", new String[0], locale));
				}
			}else {
				Role cAdminRole = roleRepository.findByName(role).get();
				strRole.setId(cAdminRole.getId());
				strRole.setName(cAdminRole.getName());
			}
			user.setPassword(encoder.encode(signUpRequestForm.getPassword()));
			user.setRole(strRole);
			user = userRepository.save(user);
			userDetails = modelMapper.map(user, UserDto.class);
			
		} catch (DataAccessException e) {
			throw new MyException(e.getCause().getCause().getMessage());
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
		log.info("User Create Successfully!");
		return userDetails;
	}

}
