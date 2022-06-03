package kbe.project.warehouse;

import kbe.project.warehouse.data.Component;
import kbe.project.warehouse.data.ComponentRepository;
import kbe.project.warehouse.data.Product;
import kbe.project.warehouse.data.ProductRepository;
import kbe.project.warehouse.services.CSVComponentImporter;
import kbe.project.warehouse.services.CSVProductImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

	@Autowired
	CSVComponentImporter componentImporter;
	@Autowired
	CSVProductImporter productImporter;
	@Autowired
	ComponentRepository componentRepository;
	@Autowired
	ProductRepository productRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void initializeDatabase() throws IOException {
		componentImporter.importComponents();
		productImporter.importProducts();
	}

	@GetMapping("/warehouse/components")
	public Component[] getComponents(){
		Iterable<Component> componentIterable = componentRepository.findAll();
		ArrayList<Component> components = new ArrayList<>();
		for(Component c : componentIterable){
			components.add(c);
		}
		return components.toArray(new Component[0]);
	}

	@GetMapping("/warehouse/products")
	public Product[] getProducts(){
		Iterable<Product> productIterable = productRepository.findAll();
		ArrayList<Product> products = new ArrayList<>();
		for(Product p : productIterable){
			products.add(p);
		}
		return products.toArray(new Product[0]);
	}
}
