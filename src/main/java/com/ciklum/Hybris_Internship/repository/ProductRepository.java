package com.ciklum.Hybris_Internship.repository;

import com.ciklum.Hybris_Internship.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
