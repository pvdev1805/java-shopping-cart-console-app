package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.dto.Customer;

public class CustomerService {
	DatabaseService dbService = new DatabaseService();
	
	public ArrayList<Customer> getCustomers(){
		return dbService.loadCustomers();
	}
}
