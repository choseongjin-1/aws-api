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
		param.put("validYn", "Y");
		
		int result = mainMapper.insertList(param);
		if (result < 1) {
			return responseUtils.makeFailResponse(param);
		}
		return responseUtils.makeSuccessResponse("등록이 완료되었습니다.");
	}

}
