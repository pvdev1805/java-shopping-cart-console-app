package dto;

public class Rank {
	private String rankId;
	private String shopId;
	private String rankName;
	private String promotionId;
	
	public Rank(String rankId, String shopId, String rankName, String promotionId) {
		this.rankId = rankId;
		this.rankName = rankName;
		this.shopId = shopId;
		this.promotionId = promotionId;
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
	
	public String getPromotionId() {
		return promotionId;
	}
}
