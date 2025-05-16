package shoppingcart.service;

import shoppingcart.dto.*;
import shoppingcart.common.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

public class DatabaseService {
	
	private ArrayList<String[]> readTextFile(String filePath){
		ArrayList<String[]> lines = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			reader.readLine(); // skip line for headers
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				lines.add(parts);
			}
			
		} catch (IOException e) {
			System.out.println("Error: Problem(s) with reading file at: " + filePath);
			e.printStackTrace();
		}
		
		if(lines.isEmpty()) {
			System.out.println("Warning: File is empty or missing: " + filePath);
		}
		
		return lines;
	}
	
	public <T> ArrayList<T> loadData(String filePath, Function<String[], T> mapper){
		ArrayList<T> result = new ArrayList<>();
		for(String[] parts: readTextFile(filePath)) {
			try {
				T item = mapper.apply(parts);
				if(item != null) {
					result.add(item);
				}
			} catch (Exception e) {
				System.out.println("Error: Could not mapping line in file: " + filePath);
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList<Shop> loadShops(){
		String shopsPath = Storage.DATABASE_PATH + "shops.txt";
		return loadData(shopsPath, parts -> {
			String id = parts[0].trim();
			String name = parts[1].trim();
			String dbPath = parts[2].trim();
			return new Shop(id, name, dbPath);
		});
	}
	
	public ArrayList<Account> loadAccounts(){
		String currentShopPath = Storage.DATABASE_PATH + Storage.currentShop.getDbPath() + "/";
		String accountsPath = currentShopPath + "accounts.txt";
		return loadData(accountsPath, parts -> {
			String accountId = parts[0].trim();
    		String username = parts[1].trim();
    		String password = parts[2].trim();
    		return new Account(accountId, username, password);
		});
	}
	
	public ArrayList<Customer> loadCustomers(){
		String currentShopPath = Storage.DATABASE_PATH + Storage.currentShop.getDbPath() + "/";
		String customersPath =  currentShopPath + "customers.txt";
		return loadData(customersPath, parts -> {
			String customerId = parts[0].trim();
    		String accountId = parts[1].trim();
    		String rankId = parts[2].trim();
    		String name = parts[3].trim();
    		return new Customer(customerId, accountId, rankId, name);
		});
	}
	
	public ArrayList<Product> loadProducts(){
		String currentShopPath = Storage.DATABASE_PATH + Storage.currentShop.getDbPath() + "/";
		String productsPath =  currentShopPath + "products.txt";
		return loadData(productsPath, parts -> {
			String productId = parts[0].trim();
    		String productName = parts[1].trim();
    		double price = Double.parseDouble(parts[2].trim());
    		return new Product(productId, productName, price);
		});
	}
	
	public ArrayList<Promotion> loadPromotions(){
		String currentShopPath = Storage.DATABASE_PATH + Storage.currentShop.getDbPath() + "/";
		String promotionsPath =  currentShopPath + "promotions.txt";
		return loadData(promotionsPath, parts -> {
			String promotionId = parts[0].trim();
    		double shippingDiscountPercentage = Double.parseDouble(parts[1].trim());
    		double orderDiscount = Double.parseDouble(parts[2].trim());
    		return new Promotion(promotionId, shippingDiscountPercentage, orderDiscount);
		});
	}
	
	public ArrayList<Rank> loadRanks(){
		String currentShopPath = Storage.DATABASE_PATH + Storage.currentShop.getDbPath() + "/";
		String ranksPath =  currentShopPath + "ranks.txt";
		return loadData(ranksPath, parts -> {
			String rankId = parts[0].trim();
            String rankName = parts[1].trim();
            String promotionId = parts[2].trim();
    		return new Rank(rankId, rankName, promotionId);
		});
	}
	
	public ArrayList<Voucher> loadVouchers(){
		String currentShopPath = Storage.DATABASE_PATH + Storage.currentShop.getDbPath() + "/";
		String vouchersPath =  currentShopPath + "vouchers.txt";
		return loadData(vouchersPath, parts -> {
			String id = parts[0].trim();
    		String code = parts[1].trim();
            double discount = Double.parseDouble(parts[2].trim());
            DiscountType discountType = DiscountType.valueOf(parts[3].trim().toUpperCase());
            boolean active = Boolean.parseBoolean(parts[4].trim());
            int customerLimit = Integer.parseInt(parts[5].trim());
            int customerUsage = Integer.parseInt(parts[6].trim());
    		return new Voucher(id, code, discount, discountType, active, customerLimit, customerUsage);
		});
	}
	
	public ShopData loadShopDataAll() {
        // Load accounts
        ArrayList<Account> accounts = loadAccounts();
        
        // Load customers
        ArrayList<Customer> customers = loadCustomers();
        
        // Load products
        ArrayList<Product> products = loadProducts();
        
        // Load promotions
        ArrayList<Promotion> promotions = loadPromotions();
        
        // Load ranks
        ArrayList<Rank> ranks = loadRanks();
        
        // Load vouchers
        ArrayList<Voucher> vouchers = loadVouchers();
        
        return new ShopData(Storage.currentShop, accounts, customers, products, promotions, ranks, vouchers);
	}
	
	// After user register a new account (or update their account info), save it 
	public void saveAccount(Account account) {
		String currentShopPath = Storage.DATABASE_PATH + Storage.currentShop.getDbPath() + "/";
		String accountsPath = currentShopPath + "accounts.txt";
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(accountsPath, true))) {
			writer.write(String.join(",", account.getAccountId(), account.getUsername(), account.getPassword()));
			writer.newLine();
		} catch (Exception e) {
			System.out.println("Error: Failed to save the new account.");
		}
	}
	
	// After register a new account, save info of that new user into Customers database
	public void saveCustomer(Customer customer) {
		String currentShopPath = Storage.DATABASE_PATH + Storage.currentShop.getDbPath() + "/";
		String customersPath =  currentShopPath + "customers.txt";
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(customersPath, true))) {
			writer.write(String.join(",", customer.getCustomerId(), customer.getName(), customer.getAccountId(), customer.getRankId()));
			writer.newLine();
		} catch (Exception e) {
			System.out.println("Error: Failed to save the new account.");
		}
	}
}
