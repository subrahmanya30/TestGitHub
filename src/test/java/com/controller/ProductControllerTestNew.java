package com.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.chatgpt.demo.ChatGptDemoApplication;
import com.exception.InvalidProductException;
import com.model.Product;
import com.service.ProductService;

import jakarta.servlet.ServletException;

@WebMvcTest(ProductController.class)

@ContextConfiguration(classes = ChatGptDemoApplication.class)
class ProductControllerTestNew {

	@Mock
	private ProductService service;

	@InjectMocks
	private ProductController controller;

	@Autowired
	private MockMvc mockMvc;


    @Test
    public void testAddProducts() throws Exception {
        Product product = new Product(1, "Test Product", 10, 100.0);
        when(service.saveProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"Test Product\", \"quantity\": 10, \"price\": 100.0}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100.0));
    }
	
	@Test
	public void testAddProduct() throws Exception {// Arrange
		Product product = new Product(1, "Product 1", 10, 100);
		when(service.saveProduct((Product) any(Product.class))).thenReturn(product);

		String json = "{\"id\":1,\"name\":\"Product 1\",\"quantity\":10,\"price\":100}";
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(product.getId())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", is(product.getName())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.quantity", is(product.getQuantity())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price", is(product.getPrice())));

		// Assert
		verify(service, times(1)).saveProduct((Product) any(Product.class));
	}

	@Test
	public void testAddProductWithMissingFields() throws Exception {
		String json = "{\"id\": 1, \"quantity\": 10, \"price\": -100.0}";
		mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
				result -> assertEquals("Product name cannot be null", result.getResolvedException().getMessage()));
	}

	@Test
	public void testAddProductWithNegativePrice() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\": 1, \"name\": \"Test Product\", \"quantity\": 10, \"price\": -100.0}"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		
		Throwable exception = assertThrows(ServletException.class, () -> {
            // Call the method that is expected to throw the exception
            // and pass in the necessary parameters
            // e.g., product with a price of -100.0
            controller.addProduct(product);
        });

        // Check the message of the exception
        String expectedMessage = "com.exception.InvalidProductException: Invalid product price: -100.0";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
	}

}
