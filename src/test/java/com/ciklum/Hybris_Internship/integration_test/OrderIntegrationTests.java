package com.ciklum.Hybris_Internship.integration_test;

import com.ciklum.Hybris_Internship.model.Order;
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

}
