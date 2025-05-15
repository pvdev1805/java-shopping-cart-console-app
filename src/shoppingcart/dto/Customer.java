package shoppingcart.dto;

public class Customer {
	private String customerId;
	private String name;
	private String accountId;
	private String rankId;
	
	public Customer(String customerId, String name, String accountId, String rankId) {
		this.customerId = customerId;
		this.name = name;
		this.accountId = accountId;
		this.rankId = rankId;
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
	
	public String getAccountId() {
		return accountId;
	}
}
