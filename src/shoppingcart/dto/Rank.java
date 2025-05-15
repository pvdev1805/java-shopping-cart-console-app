package shoppingcart.dto;

public class Rank {
	private String rankId;
	private String rankName;
	private String promotionId;
	
	public Rank(String rankId, String rankName, String promotionId) {
		this.rankId = rankId;
		this.rankName = rankName;
		this.promotionId = promotionId;
	}
	
	public String getRankId() {
		return rankId;
	}
	
	public String getRankName() {
		return rankName;
	}
	
	public String getPromotionId() {
		return promotionId;
	}
}
