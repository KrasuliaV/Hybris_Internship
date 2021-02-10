package com.ciklum.Hybris_Internship.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private static Product product;

    @BeforeAll
    static void init() {
        product = new Product();
    }

    @Test
    void testSetAndGetId() {
        long id = 1L;

        product.setId(id);

        assertEquals(id, product.getId());
    }

    @Test
    void testSetAndGetName() {
        String name = "New name";
        product.setName(name);

        assertEquals(name, product.getName());
    }

    @Test
    void testSetAndGetPrice() {
        double price = 500.0;

        product.setPrice(price);

        assertEquals(price, product.getPrice());
    }

    @Test
    void testSetAndGetStatus() {
        product.setStatus(ProductStatus.IN_STOCK);

        assertEquals(ProductStatus.IN_STOCK, product.getStatus());
    }

    @Test
    void testSetAndGetDateTime() {
        LocalDateTime time = LocalDateTime.now();

        product.setDateTime(time);

        assertEquals(time, product.getDateTime());
    }

    @Test
    void testSetAndGetOrderItemList() {
        OrderItem orderItem = new OrderItem();
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);

        product.setOrderItemList(orderItemList);

        assertEquals(orderItemList.size(), product.getOrderItemList().size());
    }

    @Test
    void testToString() {
        LocalDateTime time = LocalDateTime.now();
        String productToString = "Product{id=1, name='New name', price=500.0, status=IN_STOCK, dateTime=" +
                time + '}';
        product.setId(1L);
        product.setName("New name");
        product.setPrice(500);
        product.setStatus(ProductStatus.IN_STOCK);
        product.setDateTime(time);
        assertEquals(productToString, product.toString());
    }
}