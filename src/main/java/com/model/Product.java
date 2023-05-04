package com.model;

import com.exception.InvalidProductException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor	
@NoArgsConstructor
public class Product {
	private int id;
	private String name;
	private int quantity;
	private double price;

	public Product(int id, String name, int quantity, double price) {
		if (id < 0) {
			throw new InvalidProductException("Invalid product id: " + id);
		}
		if (name == null) {
			throw new InvalidProductException("Product name cannot be null");
		}
		if (quantity < 0) {
			throw new InvalidProductException("Invalid product quantity: " + quantity);
		}
		if (price < 0.0 || price == 0.0) {
			throw new InvalidProductException("Invalid product price: " + price);
		}

		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
}
