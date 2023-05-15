package com.aws.api.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {
	
	public Map<String, Object> makeSuccessResponse(Object data){
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("code", "0000");
		rmap.put("rdata", data);
		return rmap;
	}
	
	public Map<String, Object> makeSuccessResponse(String code, Object data){
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("code", code);
		rmap.put("rdata", data);
		return rmap;
	}
	
	public Map<String, Object> makeFailResponse(Object data){
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("code", -9999);
		rmap.put("rdata", data);
		return rmap;
	}
}
