package com.ciklum.Hybris_Internship.service.impl;

//import com.ciklum.Hybris_Internship.dto.ProductDto;

import com.ciklum.Hybris_Internship.dto.ProductDto;
import com.ciklum.Hybris_Internship.model.Product;
import com.ciklum.Hybris_Internship.repository.ProductRepository;
import com.ciklum.Hybris_Internship.service.ProductService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        if (product == null)
            throw new NullPointerException("Product can't be null");
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product readById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with " + id + "not found"));
    }

    @Override
    public Product update(Product product) {
        if (product != null) {
            readById(product.getId());
            return productRepository.saveAndFlush(product);
        }
        throw new NullPointerException("Product can't be null");
    }

    @Override
    public void delete(long id) {
        productRepository.delete(readById(id));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
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
