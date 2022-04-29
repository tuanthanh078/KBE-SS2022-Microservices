package kbe.project.product.service;

import kbe.project.product.model.CustomizedProduct;
import kbe.project.product.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(String id);
    List<Product> getAllProducts();
    Product addProduct(Product product);
    CustomizedProduct createCustomizedProduct(CustomizedProduct customProduct);
}
