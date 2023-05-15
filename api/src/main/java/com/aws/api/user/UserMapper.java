package com.aws.api.user;

import java.util.Map;

public interface UserMapper {
	
	public Map<String, Object> login(Map<String, Object> param) throws Exception;

}
