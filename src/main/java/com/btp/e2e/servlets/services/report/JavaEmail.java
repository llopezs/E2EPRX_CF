package com.btp.e2e.servlets.services.report;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Legacy Java Mail utility for sending error/debug emails.
 * WARNING: Hard-coded credentials removed for security. Use Spring Boot Mail config instead.
 * See HanaDataSourceConfig or application.properties for Spring Mail setup.
 */
public class JavaEmail {
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public static void Send(String subject, String body) throws AddressException,
			MessagingException {

		// Legacy implementation is commented out.
		// For production, use Spring's JavaMailSender with externalized config.
		// See: https://spring.io/guides/gs/sending-email/

//		JavaEmail javaEmail = new JavaEmail();
//		javaEmail.setMailServerProperties();
//		javaEmail.createEmailMessage(subject,body);
//		javaEmail.sendEmail();
	}

	public void setMailServerProperties() {

		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void createEmailMessage(String subject, String body) throws AddressException,
			MessagingException {
		String[] toEmails = { "devops@example.com" };
//		String emailSubject = "Java Test";
		String emailSubject = subject;
//		String emailBody = "This is an email sent by JavaMail api.";
		String emailBody = body;

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");//for a html email
		//emailMessage.setText(emailBody);// for a text email

	}

	public void sendEmail() throws AddressException, MessagingException {

		// Do NOT hardcode credentials. Use Spring properties or CF environment instead.
		String emailHost = System.getenv("MAIL_HOST") != null ? System.getenv("MAIL_HOST") : "smtp.example.com";
		String fromUser = System.getenv("MAIL_USER") != null ? System.getenv("MAIL_USER") : "noreply@example.com";
		String fromUserEmailPassword = System.getenv("MAIL_PASSWORD") != null ? System.getenv("MAIL_PASSWORD") : "";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}
}
