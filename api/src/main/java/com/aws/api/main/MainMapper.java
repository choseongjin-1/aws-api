package com.aws.api.main;

import java.util.List;
import java.util.Map;

public interface MainMapper {
	
	/**
	 * 리스트 조회
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectList(String keyword) throws Exception;
	
	/**
	 * 리스트 등록
	 * @param parma
	 * @return
	 * @throws Exception
	 */
	public int insertList(Map<String, Object> parma) throws Exception;

}
