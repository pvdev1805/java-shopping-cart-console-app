package shoppingcart.service.shop2;

import shoppingcart.dto.Account;
import shoppingcart.service.CartService;

public class Shop2CheckoutService extends CartService {
	@Override
	public void handleCheckoutSuccess(Account account, double checkoutAmount) {
    	String username = account.getUsername();
		System.out.printf("%s has checkout successfully and paid total: %.2f AUD\n", username, checkoutAmount);
    }
}
