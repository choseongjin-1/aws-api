package com.aws.api.jwt;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aws.api.common.exception.UnauthorizedException;
import com.aws.api.sign.SignMapper;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	SignMapper signMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Map<String, Object> member = null;
		
		try {
			
			member = signMapper.signin(username);
			
			if(member == null) {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return new User(
				member.get("userId").toString()
				,member.get("password").toString()
				,new ArrayList<>()
				);
	}
	
	public UserDetails selectUserByToken(String token) throws UsernameNotFoundException {
		
		Map<String, Object> member = null;
		
		try {
			
			member = signMapper.selectUserByToken(token);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(member == null) {
			throw new UnauthorizedException("사용 할 수 없는 토큰입니다.");
		} else {
			return new User((String)member.get("userId"), (String)member.get("userPwd"), new ArrayList<>());
		}
		
	}

}
