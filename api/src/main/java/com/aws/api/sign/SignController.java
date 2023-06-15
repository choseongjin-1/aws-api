package com.aws.api.sign;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class SignController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SignService signService;
	
	/**
	 * 로그인
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/signin")
    public Map<String, Object> signin(@RequestBody Map<String, Object> param) throws Exception {
		return signService.signin(param);
    }
	
	/**
	 * 회원가입
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/signup")
    public Map<String, Object> signup(@RequestBody Map<String, Object> param) throws Exception {
		return signService.signup(param);
    }
	
	/**
	 * 자동로그인에 대응하기위한 토큰 유저정보조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/selectUserByToken")
	public Map<String, Object> selectUserByToken(@RequestHeader Map<String, String> param) throws Exception {
		return signService.selectUserByToken(param);
	}

}
