/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.security.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jwt.SpringSecurityWithJwtAndOuth.model.User;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String password;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrincipal( User user) {
		this.id=user.getId();
		this.name=user.getName();
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.authorities=Arrays.stream(user.getRole().getName().name().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	public UserPrincipal() {
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	public Long getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}

}
