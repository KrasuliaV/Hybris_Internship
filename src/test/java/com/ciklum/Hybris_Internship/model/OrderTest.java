package com.ciklum.Hybris_Internship.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private static Order order;

    @BeforeAll
    static void init() {
        order = new Order();
    }

    @Test
    void testSetAndGetId() {
        long id = 1L;

        order.setId(id);

        assertEquals(id, order.getId());
    }

    @Test
    void testSetAndGetUserId() {
        long userId = 1L;

        order.setUserId(userId);

        assertEquals(userId, order.getUserId());
    }

    @Test
    void testSetAndGetStatus() {
        order.setStatus(OrderStatus.IN_PROGRESS);

        assertEquals(OrderStatus.IN_PROGRESS, order.getStatus());
    }

    @Test
    void testSetAndGetOrders() {
        OrderItem orderItem = new OrderItem();
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);

        order.setOrders(orderItemList);

        assertEquals(orderItemList.size(), order.getOrders().size());
    }

    @Test
    void testSetAndGetCreatedAt() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString();
        order.setCreatedAt(time);

        assertEquals(time, order.getCreatedAt());
    }

    @Test
    void testToString() {
        order.setId(1L);
        order.setUserId(1L);
        order.setStatus(OrderStatus.IN_PROGRESS);
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString();
        order.setCreatedAt(time);
        String orderInfo = "Order{id=1, userId=1, status='IN_PROGRESS', createdAt='" + time + "'}";
        assertEquals(orderInfo, order.toString());
    }
}