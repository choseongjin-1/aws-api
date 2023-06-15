package com.aws.api.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.api.jwt.JwtTokenUtil;
import com.aws.api.utils.ResponseUtils;

@Service
public class MainService {
	
	@Autowired
	ResponseUtils responseUtils;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	MainMapper mainMapper;
	
	/**
	 * 리스트 가져오기
	 * @return
	 */
	public List<Map<String, Object>> selectList(String keyword) throws Exception {
        return mainMapper.selectList(keyword);
	}
	
	/**
	 * 리스트 등록
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertList(Map<String, Object> param) throws Exception {
		param.put("regId", jwtTokenUtil.getUserId());
		
		int result = mainMapper.insertList(param);
		if (result < 1) {
			return responseUtils.makeFailResponse(param);
		}
		return responseUtils.makeSuccessResponse("등록이 완료되었습니다.");
		
	}
	
	/**
	 * 리스트 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateList(Map<String, Object> param) throws Exception {
		int result = mainMapper.updateList(param);
		if (result < 1) {
			return responseUtils.makeFailResponse(param);
		}
		return responseUtils.makeSuccessResponse("수정이 완료되었습니다.");
		
	}
	
	
	/**
	 * 리스트 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> deleteList(Map<String, Object> param) throws Exception {
		
		int result = mainMapper.deleteList(param);
		if (result < 1) {
			return responseUtils.makeFailResponse(param);
		}
		return responseUtils.makeSuccessResponse("삭제가 완료되었습니다.");
	}

}
