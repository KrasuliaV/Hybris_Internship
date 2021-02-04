package com.ciklum.Hybris_Internship.service.impl;

import com.ciklum.Hybris_Internship.model.Order;
import com.ciklum.Hybris_Internship.repository.OrderRepository;
import com.ciklum.Hybris_Internship.repository.ProductRepository;
import com.ciklum.Hybris_Internship.service.OrderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        if (order == null)
            throw new NullPointerException("Order can't be null");
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order readById(long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order with " + id + "not found"));
    }

    @Override
    public Order update(Order order) {
        if (order != null) {
            readById(order.getId());
            return orderRepository.saveAndFlush(order);
        }
        throw new NullPointerException("Order can't be null");
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(readById(id));
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
