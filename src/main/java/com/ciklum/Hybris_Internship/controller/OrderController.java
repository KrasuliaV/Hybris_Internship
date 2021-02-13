package com.ciklum.Hybris_Internship.controller;

import com.ciklum.Hybris_Internship.model.Order;
import com.ciklum.Hybris_Internship.model.OrderItem;
import com.ciklum.Hybris_Internship.model.OrderStatus;
import com.ciklum.Hybris_Internship.service.OrderItemService;
import com.ciklum.Hybris_Internship.service.OrderService;
import com.ciklum.Hybris_Internship.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.productService = productService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("/all")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "/orders-list";
    }

    @GetMapping("/create")
    public String createOrder(Model model) {
        Order newOrder = new Order();
        newOrder = orderService.create(newOrder);
        model.addAttribute("order", newOrder);
        model.addAttribute("products", productService.getAll());
        return "/create-order";
    }

    @GetMapping("/{id}/read")
    public String read(@PathVariable long id, Model model) {
        Order order = orderService.readById(id);
        model.addAttribute("order", order);
        model.addAttribute("products", productService.getAll());
        return "/create-order";
    }

    @GetMapping("/{id}/add")
    public String addOrderItem(@PathVariable long id,
                               @RequestParam("product_id") long productId,
                               @RequestParam("quantity") int quantity,
                               Model model) {
        Order order = orderService.readById(id);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(productService.readById(productId));
        orderItem.setQuantity(quantity);
        orderItem = orderItemService.create(orderItem);
        order.getOrders().add(orderItem);
        orderService.update(order);
        if (order.getStatus().name().equals(OrderStatus.NEW.name())) {
            return "redirect:/orders/" + id + "/read";
        } else {
            model.addAttribute("order", orderService.update(order));
            model.addAttribute("products", productService.getAll());
            return "/update-order";
        }
    }

    @GetMapping("/{id}/removeOrderItem")
    public String removeOrderItem(@PathVariable long id, Model model) {
        Order order = orderService.readById(orderItemService.readById(id).getOrder().getId());
        orderItemService.delete(id);
        if (order.getStatus().name().equals(OrderStatus.NEW.name())) {
            return "redirect:/orders/" + id + "/read";
        } else {
            model.addAttribute("order", orderService.update(order));
            model.addAttribute("products", productService.getAll());
            return "/update-order";
        }
    }

    @GetMapping("/{id}/update")
    public String updateOrder(@PathVariable long id, Model model) {
        Order order = orderService.readById(id);
        if (order.getStatus().equals(OrderStatus.NEW)) {
            order.setStatus(OrderStatus.IN_PROGRESS);
        }
        model.addAttribute("totalOrderPrice", orderService.getOrderTotalPrice(id));
        model.addAttribute("order", orderService.update(order));
        model.addAttribute("products", productService.getAll());
        return "/update-order";
    }

    @PostMapping("/{id}/update")
    public String updateOrder(@PathVariable long id,
                              @ModelAttribute("order") Order order,
                              Model model) {
        order.setOrders(orderService.readById(id).getOrders());
        orderService.update(orderService.readById(id));
        model.addAttribute("orders", orderService.getAll());
        return "/orders-list";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable long id, Model model) {
        orderService.delete(id);
        return "redirect:/orders/all";
    }

    @GetMapping("/information")
    public String ordersInformation(Model model) {
        model.addAttribute("orderDtos", orderService.getOrderInformation());
        return "/read-order";
    }

}
