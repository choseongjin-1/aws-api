package com.aws.api.user;

import java.util.Map;

public interface UserMapper {
	
	/**
	 * 로그인
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> signin(Map<String, Object> param) throws Exception;
	
	/**
	 * 회원가입
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int signup(Map<String, Object> param) throws Exception;
	
	/**
	 * 아이디 중복체크
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int checkDuplicateId(String userId) throws Exception;

}
