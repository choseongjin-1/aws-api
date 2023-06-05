package com.aws.api.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aws.api.utils.CommonUtil;

@Service
public class MainService {
	
	public List<Map<String, Object>> getList() {
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("no", 1);
		resultMap.put("subject", "게시판데이터1");
		resultMap.put("content", "게시판데이터1본문");
		resultMap.put("id", "seongjin");
		resultMap.put("regDt", CommonUtil.getToday());
		resultList.add(resultMap);
        return resultList;
        
	}

}
