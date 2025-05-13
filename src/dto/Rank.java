package dto;

public class Rank {
	private String rankId;
	private String shopId;
	private String rankName;
	private Promotion promotion;
	
	public Rank(String rankId, String shopId, String rankName, Promotion promotion) {
		this.rankId = rankId;
		this.rankName = rankName;
		this.shopId = shopId;
		this.promotion = promotion;
	}
	
	public String getRankId() {
		return rankId;
	}
	
	public String getRankName() {
		return rankName;
	}
	
	public String getShopId() {
		return shopId;
	}
	
	public Promotion getPromotion() {
		return promotion;
	}
}
