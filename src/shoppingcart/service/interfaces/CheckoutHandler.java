package shoppingcart.service.interfaces;

import shoppingcart.dto.Account;

public interface CheckoutHandler {
	double checkout();
	void handleCheckoutSuccess(Account account, double checkoutAmount);
}
