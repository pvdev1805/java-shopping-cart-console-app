package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.common.Storage;
import shoppingcart.dto.Account;
import shoppingcart.dto.CartItem;
import shoppingcart.dto.Product;
import shoppingcart.dto.Promotion;
import shoppingcart.dto.Rank;
import shoppingcart.service.shop1.Shop1CheckoutService;
import shoppingcart.service.shop2.Shop2CheckoutService;
import shoppingcart.service.shop3.Shop3CheckoutService;

public abstract class CartService {
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
	
	public CartItem findProduct(Product product) {
		ArrayList<CartItem> items = Storage.cart.getItems();
		for(CartItem item: items) {
			if(item.getProductName().equals(product.getProductName())) {
				return item;
			}
		}
		return null;
	}
	
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
	
    public abstract void handleCheckoutSuccess(Account account, double checkoutAmount);
    
    public static CartService getCartService() {
    	String shopPath = Storage.currentShop.getDbPath();
    	if(shopPath == "shop1") {
    		return new Shop1CheckoutService();
    	} else if(shopPath == "shop2") {
    		return new Shop2CheckoutService();
    	} else if(shopPath == "shop3") {
    		return new Shop3CheckoutService();
    	}
    	return null;
    }
	
	public double checkout() {
		if(Storage.cart.getItems().size() == 0) return 0;
		
		double subtotal = 0;
		int countItem = 0;
		double shippingFee;
		for(CartItem item: Storage.cart.getItems()) {
			subtotal += (item.getPrice() * item.getQuantity());
			countItem += item.getQuantity();
		}
		
		if(countItem <= 4) {
			shippingFee = 20;
		}else if(countItem <= 10) {
			shippingFee = 40;
		} else {
			shippingFee = 100;
		}
		System.out.printf("Total Amount: %.2f AUD\n", subtotal);
		System.out.printf("Shipping Fee: %.2f AUD\n", shippingFee);
		
		CustomerService customerService = new CustomerService();
		Rank rank = customerService.getCustomerRank();
		Promotion promotion = customerService.getCustomerPromotion();
		double shippingDiscountPercentage = promotion.getShippingDiscountPercentage();
		double orderDiscountPercentage = promotion.getOrderDiscount();
		System.out.println("RANK: " + rank.getRankName());
		System.out.println("PROMOTION: ");
		System.out.printf("- Shipping Discount Percentage: %.2f%% = %.2f AUD\n", shippingDiscountPercentage, shippingDiscountPercentage/100 * shippingFee);
		System.out.printf("- Order Discount Percentage: %.2f%% = %.2f AUD\n", orderDiscountPercentage, orderDiscountPercentage/100 * subtotal);
		
		// Need to implement voucher logic
		
		double checkoutAmount = shippingFee * ((100 - shippingDiscountPercentage)/100) + subtotal * (100 - orderDiscountPercentage)/100;
		
		handleCheckoutSuccess(Storage.loggedInAccount, checkoutAmount);
		
		Storage.cart.getItems().clear();
		return checkoutAmount;
	}
}
