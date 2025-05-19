package shoppingcart.service;

import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailService {
	String fromEmail = "phu61153426@gmail.com";
	
	// String toEmail = "phu61153426@gmail.com";
	
	String username = "phu61153426@gmail.com";
	String appPassword = "wxqg oyac qzov ejud";
	
	// Sending email from host having a specific IP address
	String sendingHost = "smtp.gmail.com";
	
	public void sendEmail(String toEmail, String subject, String content) {
		// Configure mail server Gmail
		Properties props = new Properties();
		// Enable SMTP Authentication
		props.put("mail.smtp.auth", "true");
		
		// Enable STARTTLE
		props.put("mail.smtp.starttls.enable", "true");
		
		// Sending from host
		props.put("mail.smtp.host", sendingHost);
		
		props.put("mail.smtp.port", "587");
		
		// Create a mail session object with authentication
		Session session = Session.getInstance(props, new Authenticator() {
			
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, appPassword);
            }
		});
		
		try {
			// Create a new email message
			Message message = new MimeMessage(session);
			
			// set fromEmail field
			message.setFrom(new InternetAddress(fromEmail));
			
			// set toEmail field
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			
			// set email subject field
			message.setSubject(subject);
			
			// set the content of the email message
			message.setText(content);
			
			// send the email message
            Transport.send(message);
            
            System.out.println("Well done! Email sent successfully to: " + toEmail);
			
		} catch (MessagingException e) {
			throw new RuntimeException("Error: Failed to send email: " + e.getMessage());
		}
	}
}
