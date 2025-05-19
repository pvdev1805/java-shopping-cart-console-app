package shoppingcart.service.shop1;

import java.text.DecimalFormat;

import shoppingcart.dto.Account;
import shoppingcart.service.CartService;
import shoppingcart.service.EmailService;

public class Shop1CheckoutService extends CartService {
	@Override
	public void handleCheckoutSuccess(Account account, double checkoutAmount) {
    	EmailService emailService = new EmailService();
    	DecimalFormat dfZero = new DecimalFormat("0.00");
    	String total = dfZero.format(checkoutAmount);
    	String toEmail = "phu61153426@gmail.com";
    	String subject = "Checkout success at shop 1!";
    	String content = "You have paid in total: " + total + " !";
    	emailService.sendEmail(toEmail, subject, content);
    }
}
