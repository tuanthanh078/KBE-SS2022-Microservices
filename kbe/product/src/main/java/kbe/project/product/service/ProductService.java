package kbe.project.product.service;

import kbe.project.product.model.CustomizedProduct;
import kbe.project.product.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    CustomizedProduct getProductById(UUID id);
    List<CustomizedProduct> getAllProducts();
    Product addProduct(Product product);
    CustomizedProduct createCustomizedProduct(CustomizedProduct customProduct);
}
