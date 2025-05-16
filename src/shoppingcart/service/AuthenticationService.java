package shoppingcart.service;

import shoppingcart.dto.*;
import shoppingcart.common.*;

import java.util.ArrayList;
import java.util.UUID;

public class AuthenticationService {
	CustomerService customerService = new CustomerService();
	AccountService accountService = new AccountService();
	
	public boolean login(String username, String password) {
		ArrayList<Customer> customers = customerService.getCustomers();
		ArrayList<Account> accounts = accountService.getAccounts();
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                Storage.loggedInAccount = account;

                for (Customer customer : customers) {
                    if (customer.getAccountId().equals(account.getAccountId())) {
                        Storage.loggedInCustomer = customer;
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
        String accountId = UUID.randomUUID().toString();
        String customerId = UUID.randomUUID().toString();

        // Create new object for new account
        Account newAccount = new Account(accountId, username, password);

        // Initialize the first role
        ArrayList<Rank> ranks = shopData.getRanks();
        String defaultRankId = ranks.isEmpty() ? "" : ranks.get(0).getRankId();

        Customer newCustomer = new Customer(customerId, customerName, accountId, defaultRankId);

        // Add account to accountList of shopData
        accounts.add(newAccount);
        customers.add(newCustomer);

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
}
