package com.tripmanagement.asdc.service;
import com.tripmanagement.asdc.stringsAndConstants.ServiceStringMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Component;

@Component
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private JavaMailSender javaMailSender;

	public boolean sendEmail(String message, String subject, String email) {
		if(email==null||subject==null||message==null)
		return false;
		try{
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(ServiceStringMessages.EMAIL_SENDER);
		msg.setTo(email);
		msg.setSubject(subject);
		msg.setText(message);
		javaMailSender.send(msg);
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
