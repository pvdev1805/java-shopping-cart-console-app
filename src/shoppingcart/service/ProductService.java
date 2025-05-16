package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.dto.Product;
import shoppingcart.dto.Shop;

public class ProductService {
	DatabaseService dbService = new DatabaseService();
	
	public ArrayList<Product> getProducts(){
		return dbService.loadProducts();
	}
	
	public void showProductList() {
		ArrayList<Product> products = getProducts();
		System.out.println("\n============================== PRODUCT LIST ==============================");
		for(int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			System.out.printf("%2d - %s : %.2f AUD\n", i+1, product.getProductName(), product.getPrice());
		}
	}
}
