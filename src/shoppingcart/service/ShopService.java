package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.dto.Shop;

public class ShopService {
	DatabaseService dbService = new DatabaseService();
	
	public ArrayList<Shop> getAllShops(){
		return dbService.loadShops();
	}
	
	public void showShops() {
		ArrayList<Shop> shops = getAllShops();
		System.out.println("\n============================== SHOP LIST ==============================");
		for(int i = 0; i < shops.size(); i++) {
			Shop shop = shops.get(i);
			System.out.printf("%2d - %s\n", i+1, shop.getName());
		}
	}
}
