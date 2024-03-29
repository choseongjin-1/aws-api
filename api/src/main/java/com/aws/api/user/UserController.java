package com.aws.api.user;

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
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * 아이디 중복체크
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/checkDuplicateId/{userId}")
    public Map<String, Object> checkDuplicateId(@PathVariable String userId) throws Exception {
		return userService.checkDuplicateId(userId);
    }
	
	/**
	 * 이메일 인증
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/autificateEmail/{email}")
	public Map<String, Object> autificateEmail(@PathVariable String email) throws Exception {
		return userService.autificateEmail(email);
	}

}
