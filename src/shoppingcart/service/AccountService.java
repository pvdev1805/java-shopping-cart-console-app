package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.dto.Account;

public class AccountService {
	DatabaseService dbService = new DatabaseService();
	
	public ArrayList<Account> getAccounts(){
		return dbService.loadAccounts();
	}
}
