package com.ciklum.Hybris_Internship.controller;

import com.ciklum.Hybris_Internship.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductServiceImpl productService;

    @Autowired
    public HomeController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping({"/home", "/"})
    public String home(Model model) {
        model.addAttribute("products", productService.getAll());
        return "home";
    }

}
