package com.ciklum.Hybris_Internship.service.impl;

import com.ciklum.Hybris_Internship.dto.ProductDto;
import com.ciklum.Hybris_Internship.model.Product;
import com.ciklum.Hybris_Internship.repository.ProductRepository;
import com.ciklum.Hybris_Internship.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends AbstractServiceImpl<Product, ProductRepository> implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public Product getByName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public List<ProductDto> getListOrderedProducts() {
        return productRepository.getListOrderedProducts();
    }
}
