package shoppingcart.service.shop3;

import shoppingcart.dto.Account;
import shoppingcart.service.AuthenticationService;
import shoppingcart.service.LogService;

public class Shop3AuthenService extends AuthenticationService {
	@Override
	public void handleLoginSuccess(Account account) {
		LogService logService = new LogService();
    	logService.logLogin(account);
	}
}

