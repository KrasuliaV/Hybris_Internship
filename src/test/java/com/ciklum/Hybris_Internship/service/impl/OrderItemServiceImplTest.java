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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    private static void init() {
        orderItem = new OrderItem();
        orderItem.setId(3L);
        orderItem.setQuantity(550);
    }

    @Test
    void createTest() {
        when(orderItemRepository.saveAndFlush(any(OrderItem.class))).thenReturn(orderItem);

        OrderItem actual = orderItemService.create(orderItem);

        assertEquals(orderItem, actual);

        verify(orderItemRepository, times(1)).saveAndFlush(orderItem);
    }

    @Test
    void createTestWithNullEntity() {
        assertThrows(NullPointerException.class, () -> orderItemService.create(null));
    }

    @Test
    void readByIdTest() {
        when(orderItemRepository.findById(anyLong())).thenReturn(Optional.of(orderItem));
        OrderItem actual = orderItemService.readById(1L);

        assertEquals(orderItem, actual);
        verify(orderItemRepository, times(1)).findById(1L);
    }

    @Test
    void updateTest() {
        when(orderItemRepository.findById(anyLong())).thenReturn(Optional.of(orderItem));
        when(orderItemRepository.saveAndFlush(any(OrderItem.class))).thenReturn(orderItem);

        OrderItem actual = orderItemService.update(orderItem);

        assertEquals(orderItem, actual);
        verify(orderItemRepository, times(1)).findById(3L);
        verify(orderItemRepository, times(1)).saveAndFlush(orderItem);
    }

    @Test
    void updateTestWithNullEntity() {
        assertThrows(NullPointerException.class, () -> orderItemService.update(null));
    }

    @Test
    void delete() {
        when(orderItemRepository.findById(anyLong())).thenReturn(Optional.of(orderItem));
        doNothing().when(orderItemRepository).delete(any(OrderItem.class));

        orderItemService.delete(3l);

        verify(orderItemRepository, times(1)).findById(anyLong());
        verify(orderItemRepository, times(1)).delete(orderItem);
    }

    @Test
    void getAll() {
        List<OrderItem> expected = Arrays.asList(orderItem);
        when(orderItemRepository.findAll()).thenReturn(expected);

        List<OrderItem> actual = orderItemService.getAll();

        assertEquals(expected.size(), actual.size());
        verify(orderItemRepository, times(1)).findAll();
    }
}