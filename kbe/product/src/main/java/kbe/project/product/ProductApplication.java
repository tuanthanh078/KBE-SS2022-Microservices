package kbe.project.product;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import kbe.project.product.examples.ExampleEntries;
import kbe.project.product.model.Component;
import kbe.project.product.model.Product;
import kbe.project.product.repository.ComponentRepository;
import kbe.project.product.repository.ProductRepository;
import kbe.project.product.service.ComponentService;
import kbe.project.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Product API", version = "1.0", description = "Documentation Product API v1.0")
)
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ComponentService componentService;

	@Autowired
	ComponentRepository componentRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void initializeDatabase() {
		productRepository.deleteAll();
		componentRepository.deleteAll();

		initializeComponentEntries();
		initializeProductEntries();
	}

	private void initializeProductEntries() {
		if (productRepository.count() == 0) {
			URI uri = getWarehouseUri("products");

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Product[]> result = restTemplate.getForEntity(uri, Product[].class);

			for (Product product: result.getBody()) {
				productService.addProduct(product);
			}
		}

		productService.addProduct(ExampleEntries.getExampleProduct4());
		productService.addProduct(ExampleEntries.getExampleProduct5());

		for (Product product: productService.getAllProducts()) {
			System.out.println(product);
		}
	}

	private URI getWarehouseUri(String request) {
		String baseUrl = "http://localhost:8085/"+ request;
		URI uri;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		return uri;
	}

	private void initializeComponentEntries() {
		if (productRepository.count() == 0) {
			URI uri = getWarehouseUri("components");

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Component[]> result = restTemplate.getForEntity(uri, Component[].class);

			for (Component component: result.getBody()) {
				componentService.addComponent(component);
			}
		}

		componentService.addComponent(ExampleEntries.getExampleGraphics2());
		componentService.addComponent(ExampleEntries.getExampleProcessor2());
		componentService.addComponent(ExampleEntries.getExampleStorage2());

		for (Component component: componentService.getAllComponents()) {
			System.out.println(component);
		}
	}
}
