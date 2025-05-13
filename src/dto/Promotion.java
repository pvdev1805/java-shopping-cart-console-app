package dto;

public class Promotion {
	private String promotionId;
	private double shippingDiscountPercentage;
	private double orderDiscount;
	
	public Promotion(String promotionId, double shippingDiscountPercentage, double orderDiscount) {
		this.promotionId = promotionId;
		this.shippingDiscountPercentage = shippingDiscountPercentage;
		this.orderDiscount = orderDiscount;
	}
	
	public String getPromotionId() {
		return promotionId;
	}
	
	public double getShippingDiscountPercentage() {
		return shippingDiscountPercentage;
	}
	
	public double getOrderDiscount() {
		return orderDiscount;
	}
}
