package dto;

public class CartItem extends Product{
	private String id;
	private int quantity;
	
	public CartItem(String productId, String productName, double price) {
		super(productId, productName, price);
	}
	
	public CartItem(String id, Product product, int quantity) {
		super(product.productId, product.productName, product.price);
		this.id = id;
		this.quantity = quantity;
	}
	
	public String getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
