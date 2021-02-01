package com.ciklum.Hybris_Internship.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private String createdAt;

    public Order() {
        createdAt = LocalDateTime.now().toString();
    }


}
