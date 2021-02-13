package com.ciklum.Hybris_Internship.service.impl;

import com.ciklum.Hybris_Internship.dto.OrderDto;
import com.ciklum.Hybris_Internship.model.Order;
import com.ciklum.Hybris_Internship.repository.OrderRepository;
import com.ciklum.Hybris_Internship.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends AbstractServiceImpl<Order, OrderRepository> implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> getOrderInformation() {
        return orderRepository.getOrderInformation();
    }

    @Override
    public int getOrderTotalPrice(long id) {
        if (readById(id).getOrders().size() > 0) {
            return orderRepository.getOrderTotalPrice(id);
        } else return 0;
    }
}
