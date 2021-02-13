package com.ciklum.Hybris_Internship.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "order_item")
public class OrderItem extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Min(1)
    @Column(name = "quantity")
    private int quantity;

    public OrderItem() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + super.getId() +
                ", order=" + order.getId() +
                ", product=" + product.getName() +
                ", quantity=" + quantity +
                '}';
    }

}
