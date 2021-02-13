package com.ciklum.Hybris_Internship.service.impl;

import com.ciklum.Hybris_Internship.model.OrderItem;
import com.ciklum.Hybris_Internship.repository.OrderItemRepository;
import com.ciklum.Hybris_Internship.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl extends AbstractServiceImpl<OrderItem, OrderItemRepository> implements OrderItemService {

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        super(orderItemRepository);
    }

}
