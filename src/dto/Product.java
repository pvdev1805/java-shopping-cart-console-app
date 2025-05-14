package dto;

public class Product {
	protected String productId;
	protected String productName;
	protected double price;
	
	public Product(String productId, String productName, double price) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}