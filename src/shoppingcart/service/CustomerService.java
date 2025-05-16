package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.common.Storage;
import shoppingcart.dto.Customer;
import shoppingcart.dto.Promotion;
import shoppingcart.dto.Rank;

public class CustomerService {
	DatabaseService dbService = new DatabaseService();
	
	public ArrayList<Customer> getCustomers(){
		return dbService.loadCustomers();
	}
	
	public Rank getCustomerRank() {
		String customerRankId = Storage.loggedInCustomer.getRankId();
		ArrayList<Rank> ranks = dbService.loadRanks();
		for(Rank rank: ranks) {
			if(rank.getRankId().equals(customerRankId)) {
				return rank;
			}
		}
		return null;
	}
	
	public Promotion getCustomerPromotion() {
		Rank customerRank = getCustomerRank();
		String appliedPromotionId = customerRank.getPromotionId();
		ArrayList<Promotion> promotions = dbService.loadPromotions();
		for(Promotion promotion: promotions) {
			if(promotion.getPromotionId().equals(appliedPromotionId)) {
				return promotion;
			}
		}
		return null;
	}
	
	public void showCustomerRankAndPromotion() {
		System.out.println("\n============================== RANK & PROMOTION ==============================");
		String rankName = getCustomerRank().getRankName();
		Promotion customerPromotion = getCustomerPromotion();
		double shippingDiscountPercentage = customerPromotion.getShippingDiscountPercentage();
		double orderDiscountPercentage = customerPromotion.getOrderDiscount();
		System.out.println("RANK: " + rankName);
		System.out.println("PROMOTION: ");
		System.out.printf("- Shipping Discount Percentage: %.2f%%\n", shippingDiscountPercentage);
		System.out.printf("- Order Discount Percentage: %.2f%%\n", orderDiscountPercentage);
	}
}
