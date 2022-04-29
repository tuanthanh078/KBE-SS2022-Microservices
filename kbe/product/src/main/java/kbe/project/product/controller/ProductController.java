package kbe.project.product.controller;

import kbe.project.product.model.CustomizedProduct;
import kbe.project.product.model.Product;
import kbe.project.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path="/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/custom")
    ResponseEntity<CustomizedProduct> createCustomizedProduct(@RequestBody CustomizedProduct customizedProduct) {
        return ResponseEntity.ok(productService.createCustomizedProduct(customizedProduct));
    }
}
