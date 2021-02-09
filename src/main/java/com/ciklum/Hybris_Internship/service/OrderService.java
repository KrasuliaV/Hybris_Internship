package com.ciklum.Hybris_Internship.service;

import com.ciklum.Hybris_Internship.dto.OrderDto;
import com.ciklum.Hybris_Internship.model.Order;

import java.util.List;

public interface OrderService extends AbstractService<Order>{


    public List<OrderDto> getOrderInformation();

    public  int getOrderTotalPrice(long id);
}
