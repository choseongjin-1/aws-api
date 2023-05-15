package com.aws.api.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
    public Map<String, Object> login(@RequestBody Map<String, Object> param) throws Exception {
		return userService.login(param);
    }

}
