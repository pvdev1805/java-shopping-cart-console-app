package dto;

public class Order {
	private String id;
	private String shopId;
	private String customerId;
	private String cartId;
	private double shippingCost;
	private double totalAmount;
	private int estimatedDeliveryHours;
	private String voucherId;
	
	public Order(String id, String shopId, String customerId, String cartId, double shippingCost, double totalAmount, int estimatedDeliveryHours, String voucherId) {
		this.id = id;
		this.shopId = shopId;
		this.customerId = customerId;
		this.cartId = cartId;
		this.shippingCost = shippingCost;
		this.totalAmount = totalAmount;
		this.estimatedDeliveryHours = estimatedDeliveryHours;
		this.voucherId = voucherId;
	}
	
	public String getId() {
		return id;
	}
	
	public String getShopId() {
		return shopId;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public String getCartId() {
		return cartId;
	}
	
	public double getShippingCost() {
		return shippingCost;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public int getEstimatedDeliveryHours() {
		return estimatedDeliveryHours;
	}
	
	public String getVoucherId() {
		return voucherId;
	}
}
