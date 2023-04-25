package com.aws.api.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class MainController {

	@GetMapping("/users/{userId}/{password}")
    public Map<String, Object> login(@PathVariable String userId, @PathVariable String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		String resultCode = "-9999";
		
		if ("csj".equals(userId) && "1".equals(password)) {
			resultCode = "0000";
		}
		result.put("resultCode", resultCode);
        return result;
    }
}
