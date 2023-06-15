package com.aws.api.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aws.api.jwt.JwtTokenUtil;
import com.aws.api.utils.ResponseUtils;

@Service
public class CommentService {
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	ResponseUtils responseUtils;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	/**
	 * 리스트 댓글조회
	 * @param userSrno
	 * @param listSrno
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectComment(String userSrno, String listSrno) throws Exception {
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("userSrno", userSrno);
		reqMap.put("listSrno", listSrno);
		
		List<Map<String, Object>> result = commentMapper.selectComment(reqMap);
		if (ObjectUtils.isEmpty(result)) {
			return responseUtils.makeFailResponse(reqMap);
		}
		return responseUtils.makeSuccessResponse(result);
    }
	
	/**
	 * 댓글 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertComment(Map<String, Object> param) throws Exception {
		param.put("regId", jwtTokenUtil.getUserId());
		
		int result = commentMapper.insertComment(param);
		if (result < 1) {
			return responseUtils.makeFailResponse(param);
		}
		return responseUtils.makeSuccessResponse(param);
	}

}
