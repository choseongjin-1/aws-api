package com.aws.api.comment;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
	
	/**
	 * 댓글조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectComment(Map<String, Object> param) throws Exception;
	
	/**
	 * 댓글등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int insertComment(Map<String, Object> param) throws Exception;

}
