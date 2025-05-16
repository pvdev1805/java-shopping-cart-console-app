package shoppingcart.common;

import shoppingcart.dto.*;

public class Storage {
	public static final String DATABASE_PATH = "src/shoppingcart/database/";
	
	public static Shop currentShop = null;
	
	public static Account loggedInAccount = null;
	
	public static Customer loggedInCustomer = null;
	
	public static Cart cart = null;
}
