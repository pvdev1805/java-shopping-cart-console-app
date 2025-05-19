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
	
	public static String generateOrderedId(String prefix, int length, int orderNumber) {
		if (prefix == null) {
            throw new IllegalArgumentException("Prefix cannot be null");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        if (orderNumber < 0) {
            throw new IllegalArgumentException("Order number cannot be negative");
        }
        
        String numberStr = String.valueOf(orderNumber);
        if (numberStr.length() > length) {
            numberStr = numberStr.substring(numberStr.length() - length);
        }
		
		String paddedFormat = "%" + length + "s";
		String padded = String.format(paddedFormat, String.valueOf(orderNumber)).replace(" ", "0");
		return prefix + padded;
	}
}
