/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.SpringSecurityWithJwtAndOuth.security.services.UserDetailsServiceImpl;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String username = null;
		String jwt = null;
		final String authorizationHeader = request.getHeader("Authorization");
		try {
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwt = authorizationHeader.replace("Bearer ", "");
				username = jwtProvider.getUserNameFromJwtToken(jwt);
			}
			if (jwt != null && jwtProvider.validateJwtToken(jwt)
					&& SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		filterChain.doFilter(request, response);
	}

}
