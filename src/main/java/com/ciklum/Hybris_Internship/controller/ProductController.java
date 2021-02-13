package com.ciklum.Hybris_Internship.controller;

import com.ciklum.Hybris_Internship.model.Product;
import com.ciklum.Hybris_Internship.model.ProductStatus;
import com.ciklum.Hybris_Internship.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/all"})
    public String home(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products-list";
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
        return "products-list";
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
        productService.update(product);
        model.addAttribute("products", productService.getAll());
        return "products-list";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id, Model model) {
        productService.delete(id);
        model.addAttribute("products", productService.getAll());
        return "redirect:/products-list";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(@RequestParam("password") String password, Model model) {
        if(password.equals("1111")){
            productService.deleteAll();
        }
        model.addAttribute("products", productService.getAll());
        return "products-list";
    }

    @GetMapping("/ordersProduct")
    public String getOrdersProductList(Model model) {
        model.addAttribute("orProdList", productService.getListOrderedProducts());
        return "/orders-product-list";
    }

}
