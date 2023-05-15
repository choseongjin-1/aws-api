package com.aws.api.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aws.api.utils.ResponseUtils;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
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
		
		Map<String, Object> result = userMapper.signin(param);
		
		if (ObjectUtils.isEmpty(result)) {
			responsUtils.makeFailResponse("");
		}
		
        return responsUtils.makeSuccessResponse(result);
	}
	
	/**
	 * 회원가입
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> signup(Map<String, Object> param) throws Exception {
		
		int result = userMapper.signup(param);
		if (1 > result) {
			return responsUtils.makeFailResponse("");
		}
		
		return responsUtils.makeSuccessResponse("");
	}
	
	/**
	 * 아이디 중복체크
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> checkDuplicateId(String userId) throws Exception {
		
		int result = userMapper.checkDuplicateId(userId);
		if (1 > result) {
			return responsUtils.makeFailResponse("");
		}
		
		return responsUtils.makeSuccessResponse("");
	}

}
