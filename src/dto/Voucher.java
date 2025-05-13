package dto;

public class Voucher {
	private String id;
	private String shopId;
	private String code;
	private double discount;
	private DiscountType discountType;
	private boolean active;
	private int customerLimit;
	private int customerUsage;
	
	public Voucher(String id, String shopId, String code, double discount, DiscountType discountType, boolean active, int customerLimit, int customerUsage) {
		this.id = id;
		this.shopId = shopId;
		this.code = code;
		this.discount = discount;
		this.discountType = discountType;
		this.active = active;
		this.customerLimit = customerLimit;
		this.customerUsage = customerUsage;
	}
	
	public String getId() {
		return id;
	}
	
	public String getShopId() {
		return shopId;
	}
	
	public String getCode() {
		return code;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public DiscountType getDiscountType() {
		return discountType;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public int getCustomerLimit() {
		return customerLimit;
	}
	
	public int getCustomerUsage() {
		return customerUsage;
	}
}
