package com.tripmanagement.asdc.service;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class NotificationServiceImpl implements NotificationService {


	@Override
	public void sendEmail(String message, String subject, String email) {

		String to = email;

		// Sender's email ID needs to be mentioned
		String from = "vehiclemanagement@gmail.com";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage msg = new MimeMessage(session);

			// Set From: header field of the header.
			msg.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			msg.setSubject(subject);

			// Now set the actual message
			msg.setText(message);

			// Send message
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
}





