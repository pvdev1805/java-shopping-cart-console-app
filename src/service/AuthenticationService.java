package service;

import dto.*;

import java.util.ArrayList;
import java.util.UUID;

public class AuthenticationService {
	private ShopData shopData;
	
	private Account loggedInAccount = null;
	private Customer loggedInCustomer = null;
	
	public AuthenticationService(ShopData shopdata) {
		this.shopData = shopdata;
	}
	
	public boolean login(String username, String password) {
        for (Account account : shopData.getAccounts()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                this.loggedInAccount = account;

                for (Customer customer : shopData.getCustomers()) {
                    if (customer.getAccountId().equals(account.getAccountId())) {
                        this.loggedInCustomer = customer;
                        return true; // Login successfully
                    }
                }
            }
        }
        return false; // No valid account has been found
    }
	
	public boolean register(String username, String password, String customerName) {
		// Check whether username is existed or not
        for (Account acc : shopData.getAccounts()) {
            if (acc.getUsername().equals(username)) {
                return false; // username has already existed
            }
        }

        // Create a new unique ID
        String accountId = UUID.randomUUID().toString();
        String customerId = UUID.randomUUID().toString();

        // Create new object for new account
        Account newAccount = new Account(accountId, username, password);

        // Initialize the first role
        ArrayList<Rank> ranks = shopData.getRanks();
        String defaultRankId = ranks.isEmpty() ? "" : ranks.get(0).getRankId();

        Customer newCustomer = new Customer(customerId, customerName, accountId, defaultRankId);

        // Add account to accountList of shopData
        shopData.getAccounts().add(newAccount);
        shopData.getCustomers().add(newCustomer);

        return true;
    }
	
	public void logout() {
        this.loggedInAccount = null;
        this.loggedInCustomer = null;
    }

    public boolean isLoggedIn() {
        return loggedInAccount != null;
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }
}
