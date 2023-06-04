package com.aws.api.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	
	@Autowired(required=true) 
	private HttpServletRequest req;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = -7547062164533053307L;

	public static final long JWT_TOKEN_VALIDITY = 24 * 30 * 60 * 60; // 30일

	@Value("${jwt.secret}")
	private String secret;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public String getUserId(HttpServletRequest req) {
		
		String username  = "";
		String requestTokenHeader = req.getHeader("Authorization");
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			String jwtToken = requestTokenHeader.substring(7);
			try {
				username = getUsernameFromToken(jwtToken);
				LOGGER.debug("0.  username===>"+username);
			} catch (IllegalArgumentException e) {
				LOGGER.debug("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				LOGGER.debug("JWT Token has expired");
			}
		} else {
			LOGGER.warn("JWT Token does not begin with Bearer String");
		}
		
		return username;
	}
	
	public String getUserId() {
		
		String username  = "";
		String requestTokenHeader = req.getHeader("Authorization");
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			String jwtToken = requestTokenHeader.substring(7);
			try {
				username = getUsernameFromToken(jwtToken);
				LOGGER.debug("0.  username===>"+username);
			} catch (IllegalArgumentException e) {
				LOGGER.debug("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				LOGGER.debug("JWT Token has expired");
			}
		} else {
			LOGGER.warn("JWT Token does not begin with Bearer String");
		}
		
		return username;
	}
}
