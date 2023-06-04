package com.aws.api.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aws.api.common.exception.UnauthorizedException;
import com.aws.api.jwt.JwtTokenUtil;
import com.aws.api.jwt.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				LOGGER.debug("0.  username===>"+username);
			} catch (IllegalArgumentException e) {
				LOGGER.debug("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				LOGGER.debug("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		
		LOGGER.debug("=======================================");
		LOGGER.debug("1.  SecurityContextHolder.getContext().getAuthentication()===>"+SecurityContextHolder.getContext().getAuthentication());
		LOGGER.debug("=======================================");
		
		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				UserDetails userDetails = jwtUserDetailsService.selectUserByToken(jwtToken);
				
				// if token is valid configure Spring Security to manually set
				// authentication
				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
					
					LOGGER.debug("=======================================");
					LOGGER.debug("jwt validateToken ok");
					LOGGER.debug("=======================================");
					
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					LOGGER.debug("=======================================");
					LOGGER.debug("usernamePasswordAuthenticationToken"+usernamePasswordAuthenticationToken.getName());
					LOGGER.debug("usernamePasswordAuthenticationToken"+usernamePasswordAuthenticationToken.getAuthorities());
					LOGGER.debug("=======================================");
					
					// After setting the Authentication in the context, we specify
					// that the current user is authenticated. So it passes the
					// Spring Security Configurations successfully.
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
					LOGGER.debug("=======================================");
					LOGGER.debug("2. SecurityContextHolder.getContext().getAuthentication()===>"+SecurityContextHolder.getContext().getAuthentication());
					LOGGER.debug("=======================================");
				}
			}catch (Unauthorized e) {
				System.out.println("Unauthorized..."+e.toString());
			}catch (UnauthorizedException e) {
				System.out.println("UnauthorizedException..."+e.toString());
				
				response.setHeader("WWW-Authenticate", "Basic realm=\"Access to user information\"");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(e.toString());
		        
			}catch (Exception e) {
				System.out.println("Exception..."+e.toString());
			}
		}
		chain.doFilter(request, response);
	}
}
