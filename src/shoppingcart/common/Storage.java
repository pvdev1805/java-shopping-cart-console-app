package shoppingcart.common;

import shoppingcart.dto.*;

public class Storage {
	public static final String DATABASE_PATH = "src/shoppingcart/database/";
	
	public static Shop currentShop = null;
	
	public static Account loggedInAccount = null;
	
	public static Customer loggedInCustomer = null;
	
	public static Cart cart = null;
	
	public static Voucher appliedVoucher = null;
	
	public static String receiverEmail = "phu61153426@gmail.com"; 
}
