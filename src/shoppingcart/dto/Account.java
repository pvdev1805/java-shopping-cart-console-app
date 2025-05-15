package shoppingcart.dto;

public class Account {
	private String accountId;
	private String username;
	private String password;
	
	public Account(String accountId, String username, String password) {
		this.accountId = accountId;
		this.username = username;
		this.password = password;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
