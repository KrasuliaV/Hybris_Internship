package com.ciklum.Hybris_Internship.service.impl;

import com.ciklum.Hybris_Internship.model.Order;
import com.ciklum.Hybris_Internship.model.Product;
import com.ciklum.Hybris_Internship.repository.ProductRepository;
import com.ciklum.Hybris_Internship.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ProductServiceImplTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    static Product product;

    @BeforeAll
    static void init(){
        product = new Product();
        product.setId(3L);
        product.setName("Banana");
    }

    @Test
    void createTest() {
        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(product);

        Product actual = productService.create(product);

        assertEquals(product, actual);

        verify(productRepository, times(1)).saveAndFlush(product);
    }

    @Test
    void createTestWithNullEntity() {
        assertThrows(NullPointerException.class, () -> productService.create(null));
    }

    @Test
    void readByIdTest() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        Product actual = productService.readById(1L);

        assertEquals(product, actual);
        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    void updateTest() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(product);

        Product actual = productService.update(product);

        assertEquals(product, actual);
        verify(productRepository, times(1)).findById(3L);
        verify(productRepository, times(1)).saveAndFlush(product);
    }

    @Test
    void updateTestWithNullEntity() {
        assertThrows(NullPointerException.class, () -> productService.update(null));
    }

    @Test
    void deleteTest() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(any(Product.class));

        productService.delete(3l);

        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void getAllTest() {
        List<Product> expected = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(expected);

        List<Product> actual = productService.getAll();

        assertEquals(expected.size(), actual.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void deleteAll() {
        doNothing().when(productRepository).deleteAll();

        productService.deleteAll();

        verify(productRepository, times(1)).deleteAll();
    }

    @Test
    void getByName() {
        when(productRepository.getProductByName(anyString())).thenReturn(product);

        Product actual = productService.getByName("Banana");

        assertEquals(product, actual);
        assertEquals(product.getName(), actual.getName());
        verify(productRepository, times(1)).getProductByName(anyString());
    }

    @Test
    void getListOrderedProducts() {
        productService.getListOrderedProducts();

        verify(productRepository, times(1)).getListOrderedProducts();
    }
}