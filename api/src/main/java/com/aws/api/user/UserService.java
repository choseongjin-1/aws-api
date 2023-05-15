package com.aws.api.user;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserService {
	
	@Autowired
	UserMapper usermapper;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	public Map<String, Object> login(Map<String, Object> param) throws Exception {
		Map<String, Object> result = usermapper.login(param);
		LOGGER.debug("result ==> " + result);
		String resultCode = "-9999";
		
		if (!ObjectUtils.isEmpty(result)) {
			resultCode = "0000";
		}
		
		if (ObjectUtils.isEmpty(result)) {
			resultCode = "1000";
		}
		
		result.put("resultCode", resultCode);
        return result;
	}

}
