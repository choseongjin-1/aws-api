package com.aws.api.sign;

import java.util.Map;

public interface SignMapper {
	
	/**
	 * 로그인
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> signin(String username) throws Exception;
	
	/**
	 * jwt token 업데이트
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateJwtToken(Map<String, Object> param) throws Exception;
	
	/**
	 * 토큰으로 사용자정보 조회
	 * @param webToken
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectUserByToken(String webToken) throws Exception;
	
	/**
	 * 회원가입
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int signup(Map<String, Object> param) throws Exception;
	
	/**
	 * 회원정보 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateUser(Map<String, String> param) throws Exception;

}
