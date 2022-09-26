package kbe.project.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kbe.project.product.model.CustomizedProduct;
import kbe.project.product.model.Product;
import kbe.project.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path="/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get all products.")
    ResponseEntity<List<CustomizedProduct>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id.")
    ResponseEntity<CustomizedProduct> getProductById(
            @Parameter(description = "UUID of the product")
            @PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    @Operation(summary = "Add a product.")
    Product addProduct(
            @Parameter(description = "Product that needs to be added")
            @RequestBody Product product) {

        return productService.addProduct(product);
    }

    @PostMapping("/custom")
    @Operation(summary = "Create a product.")
    ResponseEntity<CustomizedProduct> createCustomizedProduct(
            @Parameter(description = "Customized product which is created from hardwares.")
            @RequestBody CustomizedProduct customizedProduct) {

        return ResponseEntity.ok(productService.createCustomizedProduct(customizedProduct));
    }
}
