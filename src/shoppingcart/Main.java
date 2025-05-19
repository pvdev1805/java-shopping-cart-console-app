package shoppingcart;

import java.util.ArrayList;
import java.util.Scanner;

import shoppingcart.common.Storage;
import shoppingcart.dto.*;
import shoppingcart.service.*;
import shoppingcart.util.IdGenerator;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static Shop selectedShop = null;
	private static CartService cartService = null;
	private static AuthenticationService authenticationService = null;
	
	public static void main(String[] args) {
		selectedShop = selectShop();
		Storage.currentShop = selectedShop;
		
		authenticationService = AuthenticationService.getAuthenService();
		
		cartService = CartService.getCartService();
		
		boolean isLoggedIn = false;
		
		do {
			isLoggedIn = doLogin();
		} while (!isLoggedIn);
		
		runSession();
		
		System.out.println("\nThe shopping app has been terminated.");
		System.out.println("Thank you for shopping at our shop! See you next time!");
	}
	
	private static Shop selectShop() {
		ShopService shopService = new ShopService();
		ArrayList<Shop> shops = shopService.getAllShops();
		
		boolean isValidShop = false;
		
		do {
			shopService.showShops();
			
			System.out.println();
			System.out.print("Select your shop: ");
			int option = sc.nextInt();
			sc.nextLine();
			
			if(option < 1 || option > shops.size()) {
				System.out.println("\nInvalid option! Please select the valid shop!");
			} else {
				Shop result = shops.get(option - 1);
				System.out.printf("\n------------- Welcome to %s ! -------------\n", result.getName());
				isValidShop = true;
				return result;
			}
		} while (!isValidShop);
		
		return null;
	}
	
	private static boolean doLogin() {
		
		System.out.println("\n============================== LOGIN ==============================");
		System.out.print("--> Username: ");
		String username = sc.nextLine();
		System.out.print("--> Password: ");
		String password = sc.nextLine();
		
		boolean isValidLogin = authenticationService.login(username, password);
		
		if(isValidLogin) {
			System.out.println("\nLogin successfully! Welcome, " + Storage.loggedInCustomer.getName() + " !\n");
			
			String newCartId = IdGenerator.generateId("CT", 4); 
			Storage.cart = new Cart(newCartId);
		} else {
			System.out.println("\nInvalid username or password!");
		}
		
		return isValidLogin;
	}
	
	private static void doCheckout() {
		if(Storage.cart.getItems().size() == 0) {
			System.out.println("\nYour cart is empty. There is nothing to checkout!");
			return;
		}
		
		double checkoutAmount = cartService.checkout();
		System.out.printf("\nCheckout: %.2f AUD \n", checkoutAmount);
		
		cartService.checkout();
		
		System.out.println("\nYour order has been paid successfully! Thank you!");
	}
	
	private static void runSession() {
		final int VIEW_PRODUCT_LIST = 1;
		final int VIEW_CART = 2;
		final int VIEW_RANK_AND_PROMOTION = 3;
		final int CHECKOUT = 4;
		final int LOGOUT = 0;
		
		ProductService productService = new ProductService();
		CustomerService customerService = new CustomerService();
		
		int option;
		System.out.println("\n------- Welcome, " + Storage.loggedInCustomer.getName() + " -------\n");
		
		do {
			printMainMenu();
			
			System.out.print("Select your option (0 - 4): ");
			
			option = sc.nextInt();
			sc.nextLine();
			
			switch (option) {
				case VIEW_PRODUCT_LIST:
					productService.showProductList();
					System.out.print("\nSelect product number (or press 0 to cancel): ");
					
					int productIndex = sc.nextInt() - 1;
					sc.nextLine();
					
					if(productIndex == -1) {
						System.out.println("\nCancelled\n");
						break;
					}
					
					ArrayList<Product> products = productService.getProducts();
					if(productIndex >= 0 && productIndex < products.size()) {
						System.out.print("--> Quantity: ");
						int quantity = sc.nextInt();
						sc.nextLine();
						cartService.addToCart(products.get(productIndex), quantity);
						
						System.out.println("\nAdded to cart successfully!");
					} else {
						System.out.println("\nInvalid option!");
					}
					break;
				case VIEW_CART:
					cartService.showCart();
					break;
				case VIEW_RANK_AND_PROMOTION:
					customerService.showCustomerRankAndPromotion();
					break;
				case CHECKOUT:
					doCheckout();
					break;
				case LOGOUT:
					option = 0;
					authenticationService.logout();
					System.out.println("\nLogging out...\n");
					break;
			default:
				System.out.println("\nInvalid option! Please enter a valid option (0 - 4).");
				break;
			}
			
		} while (option != 0);
	}
	
	private static void printMainMenu() {
		System.out.println("\n============================== MAIN MENU ==============================");
		System.out.println("1. View Product List");
		System.out.println("2. View Cart");
		System.out.println("3. View Rank & Promotion");
		System.out.println("4. Checkout");
		System.out.println("0. Logout\n");
	}
}
