package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.dto.Rank;

public class RankService {
	DatabaseService dbService = new DatabaseService();
	
	public ArrayList<Rank> getRanks(){
		return dbService.loadRanks();
	}
}
