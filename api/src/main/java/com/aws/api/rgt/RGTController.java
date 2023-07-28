package com.aws.api.rgt;

import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rgt")
public class RGTController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RGTService rgtService;
	
	/**
	 * 데이터 조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/selectRgt")
	public Map<String, Object> selectRgt() throws Exception {
		return rgtService.selectRgt();
	}
	
	/**
	 * db에 데이터 추가
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/insertRgt")
	public Map<String, Object> insertRgt(@RequestBody Map<String, Object> param) throws Exception {
		return rgtService.insertRgt(param);
	}
	
	/**
	 * 중복제거
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/deleteRgt")
	public Map<String, Object> deleteRgt() throws Exception {
		return rgtService.deleteRgt();
	}
	
	/**
	 * 구글로그인
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/googleLogin")
	public String googleLogin(@RequestBody Map<String, Object> param) throws Exception {
		String authCode = param.get("code").toString();
		String redirectUri = param.get("redirectUri").toString();
		
		return rgtService.googleLogin(authCode, redirectUri);
	}
	
}
