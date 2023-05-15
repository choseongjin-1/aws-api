package com.aws.api.utils;

import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
	
	/**
	 * 인증번호 6자리
	 * @return
	 */
	public static int generateAuthNo() {
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        return generator.nextInt(1000000) % 1000000;
    }

}
