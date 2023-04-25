package com.aws.api.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public Map<String, Object> login(String userId, String password) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		String resultCode = "-9999";
		
		if ("csj".equals(userId) && "1".equals(password)) {
			resultCode = "0000";
		}
		result.put("resultCode", resultCode);
        return result;
	}

}
