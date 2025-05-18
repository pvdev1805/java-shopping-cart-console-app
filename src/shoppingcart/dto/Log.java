package shoppingcart.dto;

import java.time.LocalDateTime;

public class Log {
	private String logId;
	private String accountUsername;
	private String action;
	private LocalDateTime createdAt;
	
	public String getLogId() {
		return logId;
	}
	
	public String getAccountUsername() {
		return accountUsername;
	}
	
	public String getAction() {
		return action;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
