package com.ciklum.Hybris_Internship.service;

import com.ciklum.Hybris_Internship.dto.ProductDto;
import com.ciklum.Hybris_Internship.model.Product;

import java.util.List;

public interface ProductService extends AbstractService<Product> {

    void deleteAll();

    Product getByName(String name);

    List<ProductDto> getListOrderedProducts();
}
