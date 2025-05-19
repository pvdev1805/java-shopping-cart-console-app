package shoppingcart.service;

import shoppingcart.dto.*;
import shoppingcart.service.shop1.Shop1AuthenService;
import shoppingcart.service.shop2.Shop2AuthenService;
import shoppingcart.service.shop3.Shop3AuthenService;
import shoppingcart.common.*;
import shoppingcart.util.*;

import java.util.ArrayList;

public abstract class AuthenticationService {
	DatabaseService dbService = new DatabaseService();
	CustomerService customerService = new CustomerService();
	AccountService accountService = new AccountService();
	RankService rankService = new RankService();
	
	public boolean login(String username, String password) {
		ArrayList<Customer> customers = customerService.getCustomers();
		ArrayList<Account> accounts = accountService.getAccounts();
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                // Storage.loggedInAccount = account;

                for (Customer customer : customers) {
                    if (customer.getAccountId().equals(account.getAccountId())) {
                        Storage.loggedInCustomer = customer;
                        Storage.loggedInAccount = account;
                        handleLoginSuccess(account);
                        return true; // Login successfully
                    }
                }
            }
        }
        return false; // No valid account has been found
    }
	
	public boolean register(String username, String password, String customerName) {
		ArrayList<Customer> customers = customerService.getCustomers();
		ArrayList<Account> accounts = accountService.getAccounts();
		
		// Check whether username is existed or not
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return false; // username has already existed
            }
        }

        // Create a new unique ID
        String accountId = IdGenerator.generateId("A", 3);
        String customerId = IdGenerator.generateId("C", 3);

        // Create new object for new account
        Account newAccount = new Account(accountId, username, password);

        // Initialize the first role
        ArrayList<Rank> ranks = rankService.getRanks();
        String defaultRankId = ranks.isEmpty() ? "" : ranks.get(0).getRankId();

        Customer newCustomer = new Customer(customerId, customerName, accountId, defaultRankId);

        // Add new account and customer to data
        accounts.add(newAccount);
        customers.add(newCustomer);
        
        // Add new account to the correct accounts.txt file of the correct shop
        dbService.saveAccount(newAccount);
        
        // Add new customer to the correct customers.txt file of the correct shop
        dbService.saveCustomer(newCustomer);
        
        return true;
    }
	
	public void logout() {
        Storage.loggedInAccount = null;
        Storage.loggedInCustomer = null;
    }

    public boolean isLoggedIn() {
        return Storage.loggedInAccount != null;
    }

    public Account getLoggedInAccount() {
        return Storage.loggedInAccount;
    }

    public Customer getLoggedInCustomer() {
        return Storage.loggedInCustomer;
    }
    
    public abstract void handleLoginSuccess(Account account);
    
    public static AuthenticationService getAuthenService() {
    	String shopPath = Storage.currentShop.getDbPath();
    	if(shopPath.equals("shop1")) {
    		return new Shop1AuthenService();
    	} else if(shopPath.equals("shop2")) {
    		return new Shop2AuthenService();
    	} else if(shopPath.equals("shop3")) {
    		return new Shop3AuthenService();
    	}
    	return null;
    }
}
