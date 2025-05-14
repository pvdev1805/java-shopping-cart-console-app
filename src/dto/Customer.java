package dto;

public class Customer {
	private String customerId;
	private String name;
	private String accountId;
	private String rankId;
	private String cartId;
	
	public Customer(String customerId, String name, String accountId, String rankId, String cartId) {
		this.customerId = customerId;
		this.name = name;
		this.accountId = accountId;
		this.rankId = rankId;
		this.cartId = cartId;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRankId() {
		return rankId;
	}
	
	public String getCartId() {
		return cartId;
	}
	
	public String getAccountId() {
		return accountId;
	}
}
