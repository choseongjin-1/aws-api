package com.aws.api.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aws.api.utils.EmailUtil;
import com.aws.api.utils.ResponseUtils;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	ResponseUtils responsUtils;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 아이디 중복체크
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> checkDuplicateId(String userId) throws Exception {
		
		int result = userMapper.checkDuplicateId(userId);
		if (0 < result) {
			return responsUtils.makeFailResponse("");
		}
		
		return responsUtils.makeSuccessResponse("");
	}
	
	/**
	 * 이메일 인증
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> autificateEmail(String email) throws Exception {
		
		String authNumber = emailUtil.sendEmail(email);
		if (ObjectUtils.isEmpty(authNumber)) {
			return responsUtils.makeFailResponse("");
		}
		
		return responsUtils.makeSuccessResponse(authNumber);
	}

}
