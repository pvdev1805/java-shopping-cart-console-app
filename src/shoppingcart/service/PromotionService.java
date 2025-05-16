package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.dto.Promotion;

public class PromotionService {
	DatabaseService dbService = new DatabaseService();
	
	ArrayList<Promotion> getPromotions(){
		return dbService.loadPromotions();
	}
}
