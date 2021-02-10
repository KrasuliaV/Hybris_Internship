package com.ciklum.Hybris_Internship.service.impl;

import com.ciklum.Hybris_Internship.model.OrderItem;
import com.ciklum.Hybris_Internship.repository.OrderItemRepository;
import com.ciklum.Hybris_Internship.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        if (orderItem == null)
            throw new NullPointerException("Order can't be null");
        return orderItemRepository.saveAndFlush(orderItem);
    }

    @Override
    public OrderItem readById(long id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ProductItem with " + id + "not found"));
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        if (orderItem != null) {
            readById(orderItem.getId());
            return orderItemRepository.saveAndFlush(orderItem);
        }
        throw new NullPointerException("ProductItem can't be null");
    }

    @Override
    public void delete(long id) {
        orderItemRepository.delete(readById(id));
    }

    @Override
    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }
}
