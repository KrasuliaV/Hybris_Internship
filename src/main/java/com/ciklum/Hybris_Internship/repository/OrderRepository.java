package com.ciklum.Hybris_Internship.repository;

import com.ciklum.Hybris_Internship.dto.OrderDto;
import com.ciklum.Hybris_Internship.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select orders.id, sum(quantity) as quant_sum, prod.name,\n" +
            "       sum(quantity*prod.price) as prod_total_price,\n" +
            "       orders.created_at\n" +
            "from order_item\n" +
            "inner join products prod on prod.id = order_item.product_id\n" +
            "right join orders on orders.id = order_item.order_id\n" +
            "group by order_id, prod.name, orders.created_at, orders.id\n" +
            "order by orders.id", nativeQuery = true)
    List<OrderDto> getOrderInformation();

    @Query(value = "select sum(quantity * prod.price) as prod_total_price\n" +
            "from order_item\n" +
            "         inner join products prod on prod.id = order_item.product_id\n" +
            "where order_id = :order_id\n" +
            "group by order_id", nativeQuery = true)
    int getOrderTotalPrice(@Param("order_id") long id);
}
