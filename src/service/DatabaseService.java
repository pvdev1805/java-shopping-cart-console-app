package service;

import dto.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;



public class DatabaseService {
	private static final String DATABASE_PATH = "src/database/";
	
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
	
	private <T> ArrayList<T> loadData(String filePath, Function<String[], T> mapper){
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
		ArrayList<Shop> shops = new ArrayList<Shop>();
		String filePath = DATABASE_PATH + "shops.txt";
		
		ArrayList<String[]> lines = readTextFile(filePath);
		for(String[] parts: lines) {
			if(parts.length == 2) {
				String id = parts[0].trim();
				String name = parts[1].trim();
				shops.add(new Shop(id, name));
			}
		}
		return shops;
	}
	
	public ShopData loadShopData(Shop shop) {
		String folderPath = DATABASE_PATH + shop.getName() + "/";
        
        // Load accounts
        ArrayList<Account> accounts = loadData(folderPath + "accounts.txt", parts -> {
        	if(parts.length == 3) {
        		String accountId = parts[0].trim();
        		String username = parts[1].trim();
        		String password = parts[2].trim();
        		return new Account(accountId, username, password);
        	}
        	return null;
        });
        
        // Load customers
        ArrayList<Customer> customers = loadData(folderPath + "customers.txt", parts -> {
        	if(parts.length == 4) {
        		String customerId = parts[0].trim();
        		String name = parts[1].trim();
        		String accountId = parts[2].trim();
        		String rankId = parts[3].trim();
        		return new Customer(customerId, name, accountId, rankId);
        	}
        	return null;
        });
        
        // Load products
        ArrayList<Product> products = loadData(folderPath + "product.txt", parts -> {
        	if(parts.length == 3) {
        		String productId = parts[0].trim();
        		String productName = parts[1].trim();
        		double price = Double.parseDouble(parts[2].trim());
        		return new Product(productId, productName, price);
        	}
        	return null;
        });
        
        // Load promotions
        ArrayList<Promotion> promotions = loadData(folderPath + "promotion.txt", parts -> {
        	if(parts.length == 3) {
        		String promotionId = parts[0].trim();
        		double shippingDiscountPercentage = Double.parseDouble(parts[1].trim());
        		double orderDiscount = Double.parseDouble(parts[2].trim());
        		return new Promotion(promotionId, shippingDiscountPercentage, orderDiscount);
        	}
        	return null;
        });
        
        // Load ranks
        ArrayList<Rank> ranks = loadData(folderPath + "ranks.txt", parts -> {
        	if(parts.length == 3) {
        		String rankId = parts[0].trim();
                String rankName = parts[1].trim();
                String promotionId = parts[2].trim();
        		return new Rank(rankId, rankName, promotionId);
        	}
        	return null;
        });
        for(String[] parts : readTextFile(folderPath + "ranks.txt")){
        	if(parts.length == 3) {
        		String rankId = parts[0].trim();
                String rankName = parts[1].trim();
                String promotionId = parts[2].trim();
                ranks.add(new Rank(rankId, rankName, promotionId));
        	}
        }
        
        // Load vouchers
        for(String[] parts : readTextFile(folderPath + "vouchers.txt")){
        	if(parts.length == 7) {
        		String id = parts[0].trim();
        		String code = parts[1].trim();
                double discount = Double.parseDouble(parts[2].trim());
                DiscountType discountType = DiscountType.valueOf(parts[3].trim().toUpperCase());
                boolean active = Boolean.parseBoolean(parts[4].trim());
                int customerLimit = Integer.parseInt(parts[5].trim());
                int customerUsage = Integer.parseInt(parts[6].trim());
                vouchers.add(new Voucher(id, code, discount, discountType, active, customerLimit, customerUsage));
        	}
        }
        
        return new ShopData(shop, accounts, customers, products, promotions, ranks, vouchers);
	}
}
