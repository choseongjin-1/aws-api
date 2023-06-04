package com.aws.api.user;

public interface UserMapper {
	
	/**
	 * 아이디 중복체크
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int checkDuplicateId(String userId) throws Exception;

}
