package com.payment.expensecontrolsystem.controllers;

import com.payment.expensecontrolsystem.interfaces.IProductService;
import com.payment.expensecontrolsystem.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public Product getProducts(@RequestParam(name = "id") Long id){
        return this.productService.getProductById(id);
    }
}
