package shoppingcart.dto;

public class Shop {
	private String id;
	private String name;
	private String dbPath;
	
	public Shop(String id, String name, String dbPath) {
		this.id = id;
		this.name = name;
		this.dbPath = dbPath;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDbPath() {
		return dbPath;
	}
}
