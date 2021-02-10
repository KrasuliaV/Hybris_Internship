package com.ciklum.Hybris_Internship.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    private static OrderItem orderItem;

    @BeforeAll
    static void init(){
        orderItem = new OrderItem();
    }

    @Test
    void testSetAndGetId() {
        long id = 1L;

        orderItem.setId(id);

        assertEquals(id, orderItem.getId());
    }

    @Test
    void testSetAndGetOrder() {
        Order order = new Order();
        order.setId(2L);
        orderItem.setOrder(order);

        assertEquals(2.0, orderItem.getOrder().getId());
    }

    @Test
    void testSetAndGetProduct() {
        Product product = new Product();
        product.setName("Not new");
        orderItem.setProduct(product);

        assertEquals("Not new", orderItem.getProduct().getName());
    }

    @Test
    void testSetAndGetQuantity() {
        int quant = 500;
        orderItem.setQuantity(quant);

        assertEquals(quant, orderItem.getQuantity());
    }

    @Test
    void testToString() {
        orderItem.setId(1l);
        Order order = new Order();
        order.setId(2L);
        orderItem.setOrder(order);
        Product product = new Product();
        product.setName("Not new");
        orderItem.setProduct(product);
        orderItem.setQuantity(500);
        String orderItemInfo = "OrderItem{id=1, order=1, product=Not new, quantity=500}";

        assertEquals(orderItemInfo, orderItemInfo.toString());
    }
}