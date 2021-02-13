package com.ciklum.Hybris_Internship.integration_test;

import com.ciklum.Hybris_Internship.model.Order;
import com.ciklum.Hybris_Internship.model.OrderStatus;
import com.ciklum.Hybris_Internship.service.OrderService;
import com.ciklum.Hybris_Internship.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class OrderIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Test
    @Transactional
    public void getAllOrderTest() throws Exception {
        Order order = new Order();
        int sizeBeforeCreation = orderService.getAll().size();
        order = orderService.create(order);
        int sizeAfterCreation = orderService.getAll().size();
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("orders"))
                .andExpect(MockMvcResultMatchers.model().attribute("orders", orderService.getAll()));

        Assertions.assertNotEquals(sizeBeforeCreation, sizeAfterCreation);
    }

    @Test
    @Transactional
    public void createOrderGetRequestTest() throws Exception {
        Order order = new Order();
        long initialId = order.getId();
        order = orderService.create(order);
        long afterCreateId = order.getId();

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("order"))
                .andExpect(MockMvcResultMatchers.model().attribute("order", Matchers.hasProperty("status", Matchers.equalTo(order.getStatus()))))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", productService.getAll()));

        Assertions.assertNotEquals(initialId, afterCreateId);
    }

    @Test
    @Transactional
    public void readOrderGetRequestTest() throws Exception {
        Order order = new Order();
        order = orderService.create(order);
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/{id}/read", order.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("order"))
                .andExpect(MockMvcResultMatchers.model().attribute("order", orderService.readById(order.getId())))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", productService.getAll()));
    }

}
