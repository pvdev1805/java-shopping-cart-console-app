package shoppingcart.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogService {
	public void saveLog(String fileName, String content) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
			writer.write(content);
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Error: Failed to save new log at: " + fileName);
		}
	}
	
	public LocalDateTime getLogTime() {
		return LocalDateTime.now();
	}
}
