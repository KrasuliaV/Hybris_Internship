package com.ciklum.Hybris_Internship.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends AbstractEntity{

    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Product name must start with a capital letter followed by one or more lowercase letters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Min(1)
    @Column(name = "price")
    private double price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Transient
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<OrderItem> orderItemList;

    public Product() {
        super();

        dateTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", dateTime=" + dateTime +
                '}';
    }
}
