package shoppingcart.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import shoppingcart.common.Storage;
import shoppingcart.dto.Account;
import shoppingcart.util.IdGenerator;

public class LogService {
	public void appendRecord(String filePath, String record, String header) {
		File file = new File(filePath);
		
		try {
			if(!file.exists()) {
				try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
					System.out.println("A log file has been created at " + filePath);
					writer.write(header);
					writer.newLine();
					writer.write(record);
					writer.newLine();
					System.out.println("New record has been added successfully to log file at " + filePath);
				}
				return;
			} 
			
			// If logs.txt file has already existed
			try(BufferedReader reader = new BufferedReader(new FileReader(file))){
				String firstLine = reader.readLine();
				
				// if header is not available or incorrect
				if(firstLine == null || !firstLine.equals(header)) {
					File tempFile = new File(file.getAbsolutePath() + ".tmp");
					
					try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                        writer.write(header);
                        writer.newLine();

                        // Rewrite content from the old file to the tempFile
                        String line;
                        while ((line = reader.readLine()) != null) {
                            writer.write(line);
                            writer.newLine();
                        }

                        writer.write(record);
                        writer.newLine();
                    }
					
					// delete old file and replace the old file by the tempFile
					file.delete();
					tempFile.renameTo(file);
				} else {
					// If header has already been correct --> append the new record
					try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                        writer.write(record);
                        writer.newLine();
                    }
				}
				
			}
			
		} catch (IOException e) {
			System.out.println("Error: Failed to write log file at " + filePath);
		}
	}
	
	public int countLogRecords(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) return 0;
		int cnt = 0;
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String firstLine = reader.readLine();
			if(firstLine == null) return 0;
			String line;
			while((line = reader.readLine()) != null) {
				cnt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: Failed to count records at " + filePath);
		}
		
		return cnt;
	}
	
	public void logLogin(Account account) {
		String filePath = Storage.DATABASE_PATH + Storage.currentShop.getDbPath() + "/loginLogs.txt";
		int totalRecords = countLogRecords(filePath);
		String logId = IdGenerator.generateOrderedId("L", 3, totalRecords + 1);
		String accountUsername = account.getUsername();
		String action = "login";
		String createdAt = getLogTime();
		String newRecord = String.join(",", logId, accountUsername, action, createdAt);
		
		String header = "logId,accountUsername,action,createdAt";
		appendRecord(filePath, newRecord, header);
	}
	
	public void logCheckout(Account account, double checkoutAmount) {
		String filePath = Storage.DATABASE_PATH + Storage.currentShop.getDbPath() + "/checkoutLogs.txt";
		int totalRecords = countLogRecords(filePath);
		String logId = IdGenerator.generateOrderedId("L", 3, totalRecords + 1);
		String accountUsername = account.getUsername();
		String action = "checkout";
		String createdAt = getLogTime();
		String newRecord = String.join(",", logId, accountUsername, action, String.valueOf(checkoutAmount) , createdAt);
		
		String header = "logId,accountUsername,action,total,createdAt";
		appendRecord(filePath, newRecord, header);
	}
	
	public String getLogTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.now().format(formatter);
	}
}
