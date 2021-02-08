package com.ciklum.Hybris_Internship.controller;

import com.ciklum.Hybris_Internship.dto.ProductDto;
import com.ciklum.Hybris_Internship.model.Product;
import com.ciklum.Hybris_Internship.model.ProductStatus;
import com.ciklum.Hybris_Internship.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String createProduct(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("statuses", ProductStatus.values());
        return "create-product";
    }

    @PostMapping("/create")
    public String createProduct(Model model, @Validated @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("statuses", ProductStatus.values());
            return "create-product";
        }
        if (productService.getByName(product.getName()) != null) {
            model.addAttribute("message", "This products name is exist");
            model.addAttribute("product", product);
            model.addAttribute("statuses", ProductStatus.values());
            return "create-product";
        }
        System.out.println(product);
        productService.create(product);
        model.addAttribute("products", productService.getAll());
        return "home";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") long id, Model model) {
        model.addAttribute("statuses", ProductStatus.values());
        model.addAttribute("product", productService.readById(id));
        return "update-product";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") long id, Model model, @Validated @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("statuses", ProductStatus.values());
            return "update-product";
        }
        if (productService.getByName(product.getName()) != null && productService.getByName(product.getName()).getId() != product.getId()) {
            model.addAttribute("message", "This products name is exist");
            model.addAttribute("product", product);
            model.addAttribute("statuses", ProductStatus.values());
            return "update-product";
        }
        System.out.println(product);
        productService.update(product);
        model.addAttribute("products", productService.getAll());
        return "home";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id, Model model) {
        productService.delete(id);
        model.addAttribute("products", productService.getAll());
        return "redirect:/home";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        productService.deleteAll();
        return "home";
    }

    @GetMapping("/ordersProduct")
    public String getOrdersProductList(Model model) {
//        List<ProductDto> list = productService.getListOrderedProducts();
//        for (ProductDto someDTO : list) {
//            System.out.println(someDTO.getName());
//            System.out.println(someDTO.getCount());
//            System.out.println(someDTO.getSum_quant());
//        }
        model.addAttribute("orProdList", productService.getListOrderedProducts());
        return "/orders-product-list";
    }

}
