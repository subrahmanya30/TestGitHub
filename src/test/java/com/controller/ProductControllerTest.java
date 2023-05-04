package com.controller;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.model.Product;
import com.service.ProductService;

class ProductControllerTest {

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;
	
	 @Test
	    public void testFindProductById() {
	        // Arrange
	        int productId = 123;
	        Product product = new Product(productId, "Test Product", 5, 10.0);
	        when(productService.getProductById(productId)).thenReturn(product);

	        // Act
	        Product result = productController.findProductById(productId);

	        // Assert
	        assertEquals(product, result);
	    }

	@Test
	public void testFindProductByIdWithNegativeId() {
		// Arrange
		int productId = -123;

		// Act
		productController.findProductById(productId);

		// Assert
		// expect IllegalArgumentException
	}

	@Test
	public void testFindProductByIdWithNonExistentId() {
		/*
		 * // Arrange int productId = 123;
		 * when(productService.getProductById(productId)).thenThrow(new
		 * ProductNotFoundException(productId));
		 * 
		 * // Act productController.findProductById(productId);
		 * 
		 * // Assert // expect ProductNotFoundException
		 */}
}
