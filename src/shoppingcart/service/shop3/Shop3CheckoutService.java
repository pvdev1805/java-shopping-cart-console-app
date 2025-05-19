package shoppingcart.service.shop3;

import shoppingcart.dto.Account;
import shoppingcart.service.CartService;
import shoppingcart.service.LogService;

public class Shop3CheckoutService extends CartService {
	@Override
	public void handleCheckoutSuccess(Account account, double checkoutAmount) {
    	LogService logService = new LogService();
    	logService.logCheckout(account, checkoutAmount);
    }
}
