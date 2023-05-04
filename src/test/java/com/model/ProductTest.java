package com.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.exception.InvalidProductException;

public class ProductTest {

	@Test
	void testProductWithValidValues() {
		// Arrange
		int id = 1;
		String name = "Test Product";
		int quantity = 10;
		double price = 20.0;

		// Act
		Product product = new Product(id, name, quantity, price);

		// Assert
		assertEquals(id, product.getId());
		assertEquals(name, product.getName());
		assertEquals(quantity, product.getQuantity());
		assertEquals(price, product.getPrice());
	}

	@Test
	void testProductWithInvalidId() {
		// Arrange
		int id = -1;
		String name = "Test Product";
		int quantity = 10;
		double price = 20.0;

		// Act and Assert
		assertThrows(InvalidProductException.class, () -> new Product(id, name, quantity, price));
	}

	@Test
	void testProductWithInvalidName() {
		// Arrange
		int id = 1;
		String name = null;
		int quantity = 10;
		double price = 20.0;

		// Act and Assert
		assertThrows(InvalidProductException.class, () -> new Product(id, name, quantity, price));
	}

	@Test
	void testProductWithInvalidQuantity() {
		// Arrange
		int id = 1;
		String name = "Test Product";
		int quantity = -1;
		double price = 20.0;

		// Act and Assert
		assertThrows(InvalidProductException.class, () -> new Product(id, name, quantity, price));
	}

	@Test
	void testProductWithInvalidPrice() {
		// Arrange
		int id = 1;
		String name = "Test Product";
		int quantity = 10;
		double price = -1.0;

		// Act and Assert
		assertThrows(InvalidProductException.class, () -> new Product(id, name, quantity, price));
	}

}
