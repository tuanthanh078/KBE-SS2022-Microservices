package kbe.project.warehouse.services;

import kbe.project.warehouse.WarehouseApplication;
import kbe.project.warehouse.model.Component;
import kbe.project.warehouse.repository.ComponentRepository;
import kbe.project.warehouse.model.Product;
import kbe.project.warehouse.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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
        System.out.println("Import Products ...");
        List<List<String>> data = csvImporter.importCSV(CSV_FILE);

        for(int i = 1; i < data.size(); i++){
            List<String> productData = data.get(i);

            if(productData.size() < AMOUNT_FIELDS)continue;

            UUID graphicsId = UUID.fromString(productData.get(GRAPHICS));
            UUID processorId = UUID.fromString(productData.get(PROCESSOR));
            UUID storageId = UUID.fromString(productData.get(STORAGE));

            Optional<Component> graphics = componentRepository.findById(graphicsId);
            Optional<Component> processor = componentRepository.findById(processorId);
            Optional<Component> storage = componentRepository.findById(storageId);

            if(graphics.isPresent() && processor.isPresent() && storage.isPresent()){
                try {
                    Product product = new Product(
                            UUID.fromString(productData.get(0)),
                            graphics.get(),
                            processor.get(),
                            storage.get()
                    );

                    if(!productRepository.existsById(product.getId()))
                        productRepository.save(product);

                }catch(IllegalArgumentException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
