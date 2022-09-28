package kbe.project.product.service;

import kbe.project.dto.Price;
import kbe.project.product.model.Component;
import kbe.project.product.model.CustomizedProduct;
import kbe.project.product.model.Product;
import kbe.project.product.model.SelectedComponents;
import kbe.project.product.repository.ProductRepository;
import kbe.project.product.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ComponentService componentService;
    private final CustomizedProductPub customizedProductPub;

    @Override
    public CustomizedProduct getProductById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return getCustomizedProduct(product);
    }

    @Override
    public List<CustomizedProduct> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<CustomizedProduct> customizedProductList = new ArrayList<>();
        for (Product product : productList) {
            CustomizedProduct customizedProduct = getCustomizedProduct(product);
            customizedProductList.add(customizedProduct);
        }
        return customizedProductList;
    }

    @Override
    public Product addProduct(CustomizedProduct customizedProduct) {
        Set<Component> components = new HashSet<>();
        for (SelectedComponents selectedComponent : customizedProduct.getSelectedComponents()) {
            components.add(componentService.getComponentById(selectedComponent.getComponentId()));
        }
        Product product = new Product(UUID.randomUUID(), components);
        return productRepository.insert(product);
    }

    private CustomizedProduct getCustomizedProduct(Product product) {
        List<SelectedComponents> selectedComponents = new ArrayList<>();
        for (Component component : product.getComponents()) {
            selectedComponents.add(component.toSelectedComponents());
        }
        CustomizedProduct customizedProduct = new CustomizedProduct(selectedComponents, product.getId());
        Price price = customizedProductPub.getPrice(customizedProduct);
        customizedProduct.setPrice(price);
        return customizedProduct;
    }
}
