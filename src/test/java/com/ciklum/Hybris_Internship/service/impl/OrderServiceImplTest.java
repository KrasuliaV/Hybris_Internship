package com.ciklum.Hybris_Internship.service.impl;

import com.ciklum.Hybris_Internship.model.Order;
import com.ciklum.Hybris_Internship.model.OrderItem;
import com.ciklum.Hybris_Internship.repository.OrderRepository;
import com.ciklum.Hybris_Internship.service.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class OrderServiceImplTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    static Order order;

    @BeforeAll
    static void init(){
        order = new Order();
        order.setId(3L);
    }


    @Test
    void createTest() {
        when(orderRepository.saveAndFlush(any(Order.class))).thenReturn(order);

        Order actual = orderService.create(order);

        assertEquals(order, actual);

        verify(orderRepository, times(1)).saveAndFlush(order);
    }

    @Test
    void createTestWithNullEntity() {
        assertThrows(NullPointerException.class, () -> orderService.create(null));
    }

    @Test
    void readByIdTest() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        Order actual = orderService.readById(1L);

        assertEquals(order, actual);
        verify(orderRepository, times(1)).findById(anyLong());
    }

    @Test
    void updateTest() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        when(orderRepository.saveAndFlush(any(Order.class))).thenReturn(order);

        Order actual = orderService.update(order);

        assertEquals(order, actual);
        verify(orderRepository, times(1)).findById(3L);
        verify(orderRepository, times(1)).saveAndFlush(order);
    }

    @Test
    void updateTestWithNullEntity() {
        assertThrows(NullPointerException.class, () -> orderService.update(null));
    }

    @Test
    void deleteTest() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        doNothing().when(orderRepository).delete(any(Order.class));

        orderService.delete(3l);

        verify(orderRepository, times(1)).findById(anyLong());
        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void getAllTest() {
        List<Order> expected = Arrays.asList(order);
        when(orderRepository.findAll()).thenReturn(expected);

        List<Order> actual = orderService.getAll();

        assertEquals(expected.size(), actual.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void getOrderInformationTest() {
//        doNothing().when(orderRepository).getOrderInformation();
        orderService.getOrderInformation();

        verify(orderRepository, times(1)).getOrderInformation();
    }

    @Test
    void getOrderTotalPriceTest() {
        int expected = 500;
        OrderItem orderItem = new OrderItem();
        order.getOrders().add(orderItem);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        when(orderRepository.getOrderTotalPrice(anyLong())).thenReturn(500);

        int actual = orderService.getOrderTotalPrice(3L);

        assertEquals(expected, actual);
        verify(orderRepository, times(1)).findById(anyLong());
        verify(orderRepository, times(1)).getOrderTotalPrice(anyLong());

    }
}