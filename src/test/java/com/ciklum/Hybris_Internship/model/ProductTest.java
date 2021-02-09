package com.ciklum.Hybris_Internship.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void setId() {

    }

    @Test
    void getName() {
        String name = "New name";
        Product product = new Product();
        product.setName(name);

        assertEquals(name, product.getName());
    }

    @Test
    void setName() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void setPrice() {
    }

    @Test
    void getStatus() {
    }

    @Test
    void setStatus() {
    }

    @Test
    void getDateTime() {
    }

    @Test
    void setDateTime() {
    }

    @Test
    void getOrderItemList() {
    }

    @Test
    void setOrderItemList() {
    }

    @Test
    void testToString() {
    }
}