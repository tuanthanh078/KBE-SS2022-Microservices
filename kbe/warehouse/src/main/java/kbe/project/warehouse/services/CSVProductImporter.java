package kbe.project.warehouse.services;

import kbe.project.warehouse.data.Component;
import kbe.project.warehouse.data.ComponentRepository;
import kbe.project.warehouse.data.Product;
import kbe.project.warehouse.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CSVProductImporter {

    private static final int ID = 0;
    private static final int GRAPHICS = 1;
    private static final int PROCESSOR = 2;
    private static final int STORAGE = 3;

    private static final int AMOUNT_FIELDS = 4;

    private static File CSV_FILE;

    static {
        try{
            String currentPath = new java.io.File(".").getCanonicalPath();
            CSV_FILE = new File(currentPath, "data/products.csv");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private CSVImporter csvImporter;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ComponentRepository componentRepository;

    public void importProducts() throws IOException {
        List<List<String>> data = csvImporter.importCSV(CSV_FILE);

        for(int i = 1; i < data.size(); i++){
            List<String> productData = data.get(i);

            if(productData.size() < AMOUNT_FIELDS)continue;

            Optional<Component> graphics = componentRepository.findById(UUID.fromString(productData.get(GRAPHICS)));
            Optional<Component> processor = componentRepository.findById(UUID.fromString(productData.get(PROCESSOR)));
            Optional<Component> storage = componentRepository.findById(UUID.fromString(productData.get(STORAGE)));

            if(graphics.isPresent() && processor.isPresent() && storage.isPresent()){
                try {
                    Product product = new Product(
                            UUID.fromString(productData.get(0)),
                            graphics.get(),
                            processor.get(),
                            storage.get()
                    );

                    if(!productRepository.existsById(product.getId())){
                        productRepository.save(product);
                    }
                }catch(IllegalArgumentException e){}
            }
        }
    }
}
