package com.ciklum.Hybris_Internship.repository;

import com.ciklum.Hybris_Internship.dto.ProductDto;
import com.ciklum.Hybris_Internship.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select name, count(product_id), sum(quantity) as sum_quant  from products\n" +
            "INNER JOIN order_item oi on products.id = oi.product_id\n" +
            "GROUP BY name\n" +
            "ORDER BY sum(quantity) DESC", nativeQuery = true)
    List<ProductDto> getListOrderedProducts();

    Product getProductByName(String name);
}
