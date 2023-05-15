package com.aws.api.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	@Autowired
    private JavaMailSender sender;
	
	public String sendEmail(String toAddress) {
        
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String authNumber = String.valueOf(CommonUtil.generateAuthNo());
        
        try {
            helper.setTo(toAddress);
            helper.setSubject("이메일 인증번호 입니다.");
            helper.setText("인증번호는 [" + authNumber + "] 입니다.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return "";
        }

        sender.send(message);
        return authNumber;
        
    }
	
}
