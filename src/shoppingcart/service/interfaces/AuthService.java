package shoppingcart.service.interfaces;

import shoppingcart.dto.Account;

public interface AuthService {
	boolean login(String username, String password);
	boolean register(String username, String password, String customerName);
	void logout();
	void handleLoginSuccess(Account account);
}
