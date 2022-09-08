package com.pro.controller;

import com.pro.service.IProductService;
import com.pro.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final IProductService productService = new ProductService();

}
