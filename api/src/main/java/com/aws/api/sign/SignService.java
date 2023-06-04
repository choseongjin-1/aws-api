package com.aws.api.sign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aws.api.jwt.JwtTokenUtil;
import com.aws.api.jwt.JwtUserDetailsService;
import com.aws.api.utils.ResponseUtils;

@Service
public class SignService {
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	SignMapper signMapper;
	
	@Autowired
	ResponseUtils responsUtils;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 로그인
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> signin(Map<String, Object> param) throws Exception {
		String id = param.get("username").toString();
		String password = param.get("password").toString();
		
		LOGGER.debug("id ==> " + id);
		LOGGER.debug("password ==> " + password);
		
		authenticate(id, password);
		
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(id);
		
		final String webToken = jwtTokenUtil.generateToken(userDetails);
		
		//token 값 사용자 정보에 업데이트.
		if(!"".equals(webToken)) {
			Map<String, Object> updateParam = new HashMap<String, Object>();
			updateParam.put("username", userDetails.getUsername());
			updateParam.put("webToken", webToken);
			signMapper.updateJwtToken(updateParam);
		}
		
        return responsUtils.makeSuccessResponse(webToken);
	}
	
	/**
	 * 회원가입
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> signup(Map<String, Object> param) throws Exception {
		param.put("password", bcryptEncoder.encode(param.get("password").toString()));
		
		int result = signMapper.signup(param);
		if (1 > result) {
			return responsUtils.makeFailResponse("");
		}
		
		return responsUtils.makeSuccessResponse("");
	}
	
	/**
	 * token 검증
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
