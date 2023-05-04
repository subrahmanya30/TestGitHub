package com.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.model.Product;
import com.repository.ProductRepository;

public class ProductServiceTest {

	@Mock
	private ProductRepository repository;

	@InjectMocks
	private ProductService service;

	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	@Test
	public void testSaveProduct() {
		Product product = new Product(1, "Test Product", 10, 100.0);
		when(repository.save(product)).thenReturn(product);

		Product savedProduct = service.saveProduct(product);

		verify(repository).save(product);
		assert savedProduct.getId()==1;
		assert savedProduct.getName().equals("Test Product");
		assert savedProduct.getQuantity() == 10;
		assert savedProduct.getPrice() == 100.0;
	}

	@Test
	public void testSaveProductWithNullId() {
        Product product = new Product(1, "Test Product", 10, 100.0);
        when(repository.save(product)).thenReturn(product);

        Product savedProduct = service.saveProduct(product);

        verify(repository).save(product);
        assert savedProduct.getId()==(1);
        assert savedProduct.getName().equals("Test Product");
        assert savedProduct.getQuantity() == 10;
        assert savedProduct.getPrice() == 100.0;}

}
