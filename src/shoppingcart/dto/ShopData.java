package shoppingcart.dto;

import java.util.ArrayList;

public class ShopData {
	private Shop shop;
	private ArrayList<Account> accounts;
	private ArrayList<Customer> customers;
	private ArrayList<Product> products;
	private ArrayList<Promotion> promotions;
	private ArrayList<Rank> ranks;
	private ArrayList<Voucher> vouchers;

	public ShopData(Shop shop, ArrayList<Account> accounts, ArrayList<Customer> customers, ArrayList<Product> products,
			ArrayList<Promotion> promotions, ArrayList<Rank> ranks, ArrayList<Voucher> vouchers) {
		this.shop = shop;
		this.accounts = accounts;
		this.customers = customers;
		this.products = products;
		this.promotions = promotions;
		this.ranks = ranks;
		this.vouchers = vouchers;
	}
	
	public Shop getShop() {
		return shop;
	}
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public ArrayList<Rank> getRanks() {
		return ranks;
	}
	
	public ArrayList<Promotion> getPromotions() {
		return promotions;
	}
	
	public ArrayList<Voucher> getVouchers() {
		return vouchers;
	}
}
