package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dto.*;

public class DatabaseService {
	private static final String DATABASE_PATH = "src/database/";
	
	private ArrayList<String[]> readTextFile(String filePath){
		ArrayList<String[]> lines = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			reader.readLine(); // skip line for headers
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				lines.add(parts);
			}
			
		} catch (IOException e) {
			System.out.println("Error: Problem(s) with reading file at: " + filePath);
			e.printStackTrace();
		}
		
		return lines;
	}
	
	public ArrayList<Shop> loadShops(){
		ArrayList<Shop> shops = new ArrayList<Shop>();
		String filePath = DATABASE_PATH + "shops.txt";
		
		ArrayList<String[]> lines = readTextFile(filePath);
		for(String[] parts: lines) {
			if(parts.length == 2) {
				String id = parts[0].trim();
				String name = parts[1].trim();
				shops.add(new Shop(id, name));
			}
		}
		return shops;
	}
}
