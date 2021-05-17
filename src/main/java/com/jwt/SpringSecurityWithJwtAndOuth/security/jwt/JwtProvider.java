/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.security.jwt;

import java.security.SignatureException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.jwt.SpringSecurityWithJwtAndOuth.security.services.UserPrincipal;
import io.jsonwebtoken.*;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@Component
public class JwtProvider {

	@Value("${taskmanager.jwtSecret}")
	private String jwtSecretKey;

	@Value("${taskmanager.jwtExpiration}")
	private int jwtExpirationToken;

	public String generateJwtToken(Authentication authentication) {

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationToken * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecretKey).compact();
	}

	public boolean validateJwtToken(String authToken) throws SignatureException {
		try {
			Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			e.printStackTrace();
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
	}
}
