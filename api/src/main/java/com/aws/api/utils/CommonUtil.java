package com.aws.api.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
	/**
	 * 오늘날짜
	 * @return
	 */
	public static String getToday() {
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
	}

}
