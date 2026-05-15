package com.example.bai1.controller;

import com.example.bai1.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping("/hot")
    public List<Product> getHotProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("HP001", "Áo thun 'Code is Life'", 199000));
        products.add(new Product("HP002", "Móc khóa 'Bug Free'", 99000));
        return products; // Spring Boot sẽ tự động chuyển đổi thành JSON
    }
}
