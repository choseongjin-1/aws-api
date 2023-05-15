package com.aws.api.user;

import java.util.Map;

public interface UserMapper {
	
	public Map<String, Object> signin(Map<String, Object> param) throws Exception;
	
	public int signup(Map<String, Object> param) throws Exception;
	
	public int checkDuplicateId(String userId) throws Exception;

}
