package com.ciklum.Hybris_Internship.service.impl;

import com.ciklum.Hybris_Internship.dto.OrderDto;
import com.ciklum.Hybris_Internship.model.Order;
import com.ciklum.Hybris_Internship.repository.OrderRepository;
import com.ciklum.Hybris_Internship.repository.ProductRepository;
import com.ciklum.Hybris_Internship.service.OrderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<OrderDto> getOrderInformation(){
        return orderRepository.getOrderInformation();
    }

    @Override
    public  int getOrderTotalPrice(long id){
        if(readById(id).getOrders().size() > 0){
            return orderRepository.getOrderTotalPrice(id);
        }else return 0;
            }

//    public Map<Long, Integer> getTotalPriceForEachOrder(){
//        Map<Long, Integer> totalPriceMap= new LinkedHashMap<>();
//        getAll().forEach(order -> totalPriceMap.put(order.getId(), orderRepository.getOrderTotalPrice((order.getId()))));
//        return totalPriceMap;
//    }
}
