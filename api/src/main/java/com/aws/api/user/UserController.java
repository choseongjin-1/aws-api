package com.aws.api.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users/{userId}/{password}")
    public Map<String, Object> login(@PathVariable String userId, @PathVariable String password) {
		return userService.login(userId, password);
    }

}
