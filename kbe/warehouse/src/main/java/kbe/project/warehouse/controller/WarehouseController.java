package kbe.project.warehouse.controller;

import kbe.project.warehouse.exceptions.IllegalIdFormatException;
import kbe.project.warehouse.exceptions.NotFoundException;
import kbe.project.warehouse.model.Component;
import kbe.project.warehouse.model.Product;
import kbe.project.warehouse.repository.ComponentRepository;
import kbe.project.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/components/{id}")
    public Component getComponent(@PathVariable String id){
        UUID componentId = validateId(id);

        return componentRepository.findById(componentId)
                .orElseThrow(() -> {return new NotFoundException(componentId);});
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

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable String id){
        UUID productId = validateId(id);

        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(productId));
    }

    private UUID validateId(String idString){
        try{
            return UUID.fromString(idString);
        }catch(IllegalArgumentException e){
            throw new IllegalIdFormatException(idString);
        }
    }
}
