package shoppingcart.service.shop1;

import shoppingcart.dto.Account;
import shoppingcart.service.AuthenticationService;
import shoppingcart.service.EmailService;

public class Shop1AuthenService extends AuthenticationService {
	@Override
	public void handleLoginSuccess(Account account) {
		EmailService emailService = new EmailService();
		String toEmail = "phu61153426@gmail.com";
		String subject = "Test email";
		String content = "This is a test!";
		emailService.sendEmail(toEmail, subject, content);
	}
}
