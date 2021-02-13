package com.ciklum.Hybris_Internship.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private static Product product;

    @BeforeAll
    static void init() {
        product = new Product();
    }

    @Test
    void testSetAndGetId() {
        long id = 1L;

        product.setId(id);

        assertEquals(id, product.getId());
    }

    @Test
    void testSetAndGetName() {
        String name = "New name";
        product.setName(name);

        assertEquals(name, product.getName());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidProductName")
    void constraintInvalidProductName(String input, String errorValue) {
        product.setName(input);
        product.setPrice(50);
        product.setStatus(ProductStatus.IN_STOCK);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidProductName() {
        return Stream.of(
                Arguments.of("invalidName", "invalidName"),
                Arguments.of("123456", "123456"),
                Arguments.of("", "")
        );
    }

    @Test
    void testSetAndGetPrice() {
        double price = 500.0;

        product.setPrice(price);

        assertEquals(price, product.getPrice());
    }

    @Test
    void testSetAndGetStatus() {
        product.setStatus(ProductStatus.IN_STOCK);

        assertEquals(ProductStatus.IN_STOCK, product.getStatus());
    }

    @Test
    void testSetAndGetDateTime() {
        LocalDateTime time = LocalDateTime.now();

        product.setDateTime(time);

        assertEquals(time, product.getDateTime());
    }

    @Test
    void testSetAndGetOrderItemList() {
        OrderItem orderItem = new OrderItem();
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);

        product.setOrderItemList(orderItemList);

        assertEquals(orderItemList.size(), product.getOrderItemList().size());
    }

    @Test
    void testToString() {
        LocalDateTime time = LocalDateTime.now();
        String productToString = "Product{id=1, name='New name', price=500.0, status=IN_STOCK, dateTime=" +
                time + '}';
        product.setId(1L);
        product.setName("New name");
        product.setPrice(500);
        product.setStatus(ProductStatus.IN_STOCK);
        product.setDateTime(time);
        assertEquals(productToString, product.toString());
    }
}