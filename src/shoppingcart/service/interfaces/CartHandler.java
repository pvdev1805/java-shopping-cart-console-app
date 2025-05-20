package shoppingcart.service.interfaces;

import shoppingcart.dto.CartItem;
import shoppingcart.dto.Product;

public interface CartHandler {
	void addToCart(Product product, int quantity);
	CartItem findProduct(Product product);
	void showCart();
}
