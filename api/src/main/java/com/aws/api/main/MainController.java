package com.aws.api.main;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class MainController {
	
	@Autowired
	MainService mainService;
	
	
	/**
	 * 리스트 가져오기
	 * @return
	 */
	@GetMapping("/selectList/{keyword}")
    public List<Map<String, Object>> selectList(@PathVariable String keyword) throws Exception {
		return mainService.selectList(keyword);
    }
	
	/**
	 * 리스트 등록
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/insertList")
	public Map<String, Object> insertList(@RequestBody Map<String, Object> param) throws Exception {
		return mainService.insertList(param);
	}
	
	/**
	 * 리스트 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/deleteList")
	public Map<String, Object> deleteList(@RequestBody Map<String, Object> param) throws Exception {
		return mainService.deleteList(param);
	}
	
}
