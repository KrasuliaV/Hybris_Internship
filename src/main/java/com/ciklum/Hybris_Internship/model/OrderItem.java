package com.ciklum.Hybris_Internship.model;

import javax.persistence.*;


@Table(name = "order_item")
public class OrderItem {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}
