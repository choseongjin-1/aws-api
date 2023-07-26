package com.aws.api.rgt;

import java.util.List;
import java.util.Map;

public interface RGTMapper {
	
	/**
	 * 데이터 조회
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectRgt() throws Exception;
	
	/**
	 * db에 데이터 추가
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int insertRgt(Map<String, Object> param) throws Exception;
	
	/**
	 * 중복제거
	 * @return
	 * @throws Exception
	 */
	public int deleteRgt() throws Exception;
	
	/**
	 * 이름 업데이트
	 * @return
	 * @throws Exception
	 */
	public int updateRgt() throws Exception;

}
