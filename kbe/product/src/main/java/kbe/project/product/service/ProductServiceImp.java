package kbe.project.product.service;

import kbe.project.dto.Price;
import kbe.project.product.model.CustomizedProduct;
import kbe.project.product.model.Hardware;
import kbe.project.product.model.Product;
import kbe.project.product.model.SelectedHardware;
import kbe.project.product.repository.HardwareRepository;
import kbe.project.product.repository.ProductRepository;
import kbe.project.product.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final HardwareRepository hardwareRepository;
    private final CustomizedProductPub customizedProductPub;

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.insert(product);
    }

    @Override
    public CustomizedProduct createCustomizedProduct(CustomizedProduct customizedProduct) {
        List<SelectedHardware> selectedHardwares = customizedProduct.getSelectedHardwares();
        System.out.println(selectedHardwares.toString());
        List<Hardware> selectedHardwaresDetails = (List<Hardware>) hardwareRepository
                .findAllById(selectedHardwares
                        .stream()
                        .map(selectedHardware -> selectedHardware.getHardwareId()).toList());
        System.out.println(selectedHardwaresDetails.toString());
        for (int i = 0; i < selectedHardwares.size(); i++) {
            for (int j = 0; j < selectedHardwaresDetails.size(); j++) {
                if (selectedHardwares.get(i).getHardwareId() == selectedHardwaresDetails.get(j).getId()) {
                    selectedHardwares.get(i).setPriceUSD(selectedHardwaresDetails.get(j).getPriceUSD());
                }
            }
        }
        customizedProduct.setSelectedHardwares(selectedHardwares);
        System.out.println(customizedProduct.toString());
        Price price = customizedProductPub.getPrice(customizedProduct);
        System.out.println(price.toString());
        customizedProduct.setPrice(price);
        System.out.println(customizedProduct.toString());
        return customizedProduct;
    }

}
