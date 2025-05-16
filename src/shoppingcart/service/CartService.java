package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.common.Storage;
import shoppingcart.dto.CartItem;
import shoppingcart.dto.Product;

public class CartService {
	// addToCart
	public void addToCart(Product product, int quantity) {
		CartItem item = findProduct(product);
		
		if(item != null) {
			// Add more quantity to an existing product in cart
			int currentQuantity = item.getQuantity();
			item.setQuantity(currentQuantity + quantity);
		} else {
			// Add a new product to cart
			CartItem newItem = new CartItem(String.valueOf(Storage.cart.getItems().size()), product, quantity);
			Storage.cart.getItems().add(newItem);
		}
	}
	
	// findProduct
	public CartItem findProduct(Product product) {
		ArrayList<CartItem> items = Storage.cart.getItems();
		for(CartItem item: items) {
			if(item.getProductName().equals(product.getProductName())) {
				// log test
				System.out.println("Name: " + item.getProductName());
				return item;
			}
		}
		return null;
	}
	
	// showCart
	public void showCart() {
		ArrayList<CartItem> items = Storage.cart.getItems();
		
		System.out.println("\n========================== SHOPPING CART ==========================");
		if(items.isEmpty()) {
			System.out.println("Your cart is empty.");
			return;
		}
		
		for(int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			String productName = item.getProductName();
			double price = item.getPrice();
			int quantity = item.getQuantity();
			double totalPrice = price * quantity;
			System.out.printf("%2d. %s: %.2f AUD x %d = %.2f AUD\n", (i+1), productName, price, quantity, totalPrice);
		}
	}
	
	// clearCart
}
