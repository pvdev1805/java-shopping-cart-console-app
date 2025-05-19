package shoppingcart.service.shop2;

import shoppingcart.dto.Account;
import shoppingcart.service.AuthenticationService;

public class Shop2AuthenService extends AuthenticationService {
	@Override
	public void handleLoginSuccess(Account account) {
		String username = account.getUsername();
		System.out.println("You have logged in successfully! Welcome, " + username + " !");
	}
}

