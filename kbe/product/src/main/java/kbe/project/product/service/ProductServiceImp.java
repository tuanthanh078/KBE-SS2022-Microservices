package kbe.project.product.service;

import kbe.project.dto.Price;
import kbe.project.product.model.Component;
import kbe.project.product.model.CustomizedProduct;
import kbe.project.product.model.Product;
import kbe.project.product.model.SelectedComponents;
import kbe.project.product.repository.ComponentRepository;
import kbe.project.product.repository.ProductRepository;
import kbe.project.product.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ComponentRepository componentRepository;
    private final CustomizedProductPub customizedProductPub;

    @Override
    public CustomizedProduct getProductById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        CustomizedProduct customizedProduct = getCustomizedProduct(product);
        Price price = customizedProductPub.getPrice(customizedProduct);
        customizedProduct.setPrice(price);
        return customizedProduct;
    }

    private CustomizedProduct getCustomizedProduct(Product product) {
        List<SelectedComponents> selectedComponents = new ArrayList<>();
        for (Component component : product.getComponents()) {
            selectedComponents.add(component.toSelectedComponents());
        }
        return new CustomizedProduct(selectedComponents);
    }

    @Override
    public List<CustomizedProduct> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<CustomizedProduct> customizedProductList = new ArrayList<>();
        for (Product product : productList) {
            CustomizedProduct customizedProduct = getCustomizedProduct(product);
            Price price = customizedProductPub.getPrice(customizedProduct);
            customizedProduct.setPrice(price);
//            customizedProduct = createCustomizedProduct(customizedProduct);
            customizedProductList.add(customizedProduct);
        }
        return customizedProductList;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.insert(product);
    }

    @Override
    public CustomizedProduct createCustomizedProduct(CustomizedProduct customizedProduct) {
        List<SelectedComponents> selectedComponents = customizedProduct.getSelectedComponents();
        System.out.println(selectedComponents.toString());
        List<Component> selectedComponentsDetails = (List<Component>) componentRepository
                .findAllById(selectedComponents
                        .stream()
                        .map(components -> components.getComponentId()).toList());
        System.out.println(selectedComponentsDetails.toString());
        for (int i = 0; i < selectedComponents.size(); i++) {
            for (int j = 0; j < selectedComponentsDetails.size(); j++) {
                if (selectedComponents.get(i).getComponentId().equals(selectedComponentsDetails.get(j).getId())) {
                    selectedComponents.get(i).setPrice(selectedComponentsDetails.get(j).getPrice());
                }
            }
        }
        customizedProduct.setSelectedComponents(selectedComponents);
        System.out.println(customizedProduct.toString());
        Price price = customizedProductPub.getPrice(customizedProduct);
        System.out.println(price.toString());
        customizedProduct.setPrice(price);
        System.out.println(customizedProduct.toString());
        return customizedProduct;
    }

}
