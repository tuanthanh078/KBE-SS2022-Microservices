package kbe.project.warehouse.controller;

import kbe.project.warehouse.model.Component;
import kbe.project.warehouse.model.Product;
import kbe.project.warehouse.repository.ComponentRepository;
import kbe.project.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class WarehouseController {

    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/components")
    public Component[] getComponents(){
        Iterable<Component> componentIterable = componentRepository.findAll();
        ArrayList<Component> components = new ArrayList<>();
        for(Component c : componentIterable){
            components.add(c);
        }
        return components.toArray(new Component[0]);
    }

    @GetMapping("/products")
    public Product[] getProducts(){
        Iterable<Product> productIterable = productRepository.findAll();
        ArrayList<Product> products = new ArrayList<>();
        for(Product p : productIterable){
            products.add(p);
        }
        return products.toArray(new Product[0]);
    }
}
