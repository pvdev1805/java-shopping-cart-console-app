package shoppingcart.dto;

import java.util.ArrayList;

public class Cart {
	private String id;
	private ArrayList<CartItem> items;
	
	public Cart(String id) {
		this.id = id;
		this.items = new ArrayList<>();
	}
	
	public Cart(String id, ArrayList<CartItem> items) {
		this.id = id;
		this.items = items;
	}
	
	public String getId() {
		return id;
	}
	
	public ArrayList<CartItem> getItems(){
		 return this.items;
	}
	
	public void setItems(ArrayList<CartItem> newItems) {
		this.items = newItems;
	}
}
