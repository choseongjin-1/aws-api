package com.aws.api.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * 로그인
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/signin")
    public Map<String, Object> signin(@RequestBody Map<String, Object> param) throws Exception {
		return userService.signin(param);
    }
	
	/**
	 * 회원가입
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/signup")
    public Map<String, Object> signup(@RequestBody Map<String, Object> param) throws Exception {
		return userService.signup(param);
    }
	
	/**
	 * 아이디 중복체크
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/checkDuplicateId/{userId}")
    public Map<String, Object> checkDuplicateId(@PathVariable String userId) throws Exception {
		return userService.checkDuplicateId(userId);
    }

}
