package com.ciklum.Hybris_Internship.repository;

import com.ciklum.Hybris_Internship.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
