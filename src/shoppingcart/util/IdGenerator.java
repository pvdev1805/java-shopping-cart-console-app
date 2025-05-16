package shoppingcart.util;

public class IdGenerator {
	public static String generateRandomNumber(int length) {
		String characters = "0123456789";
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < length; i++) {
			int randomIndex = (int)(Math.random() * characters.length());
			result.append(characters.charAt(randomIndex));
		}
		
		return result.toString();
	}
	
	public static String generateId(String prefix, int length) {
		return prefix + generateRandomNumber(length);
	}
}
