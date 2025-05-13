package dto;

public class Account {
	private String accountId;
	private String shopId;
	private String username;
	private String password;
	
	public Account(String accountId, String shopId, String username, String password) {
		this.accountId = accountId;
		this.shopId = shopId;
		this.username = username;
		this.password = password;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public String getShopId() {
		return shopId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
