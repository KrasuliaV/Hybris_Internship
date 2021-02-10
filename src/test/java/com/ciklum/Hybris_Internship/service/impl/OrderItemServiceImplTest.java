package com.ciklum.Hybris_Internship.service.impl;

import com.ciklum.Hybris_Internship.model.OrderItem;
import com.ciklum.Hybris_Internship.repository.OrderItemRepository;
import com.ciklum.Hybris_Internship.service.OrderItemService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class OrderItemServiceImplTest {

    @MockBean
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemService orderItemService;

    static OrderItem orderItem;

    @BeforeAll
    private static void init(){
        orderItem = new OrderItem();
        orderItem.setId(3L);
        orderItem.setQuantity(550);
    }

    @Test
    void create() {
        when(orderItemRepository.saveAndFlush(any(OrderItem.class))).thenReturn(orderItem);

        OrderItem actual = orderItemService.create(orderItem);

        assertEquals(orderItem, actual);

        verify(orderItemRepository,times(1)).saveAndFlush(orderItem);
    }

    @Test
    void createTestWithNullEntity(){
        when(orderItemRepository.saveAndFlush(null)).thenThrow(new NullPointerException());
        assertThrows(NullPointerException.class, () -> orderItemService.create(null));
        verify(orderItemRepository, times(1)).saveAndFlush(null);
    }

    @Test
    void readById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}