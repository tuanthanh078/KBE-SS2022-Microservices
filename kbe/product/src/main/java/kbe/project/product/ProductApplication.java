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

		if (productRepository.count() == 0) {
			System.out.println("initializing Product Database by adding example products");
			productService.addProduct(ExampleEntries.getExampleProduct1());
			productService.addProduct(ExampleEntries.getExampleProduct2());
			productService.addProduct(ExampleEntries.getExampleProduct3());
			productService.addProduct(ExampleEntries.getExampleProduct4());
			productService.addProduct(ExampleEntries.getExampleProduct5());
			System.out.println("added example products");
		}

		for (Product product: productService.getAllProducts()) {
			System.out.println(product);
		}

		if (componentRepository.count() == 0) {
			System.out.println("initializing Component Database by adding example components");
			componentService.addComponent(ExampleEntries.getExampleGraphics1());
			componentService.addComponent(ExampleEntries.getExampleGraphics2());
			componentService.addComponent(ExampleEntries.getExampleProcessor1());
			componentService.addComponent(ExampleEntries.getExampleProcessor2());
			componentService.addComponent(ExampleEntries.getExampleStorage1());
			componentService.addComponent(ExampleEntries.getExampleStorage2());
			System.out.println("added example components");
		}

		for (Component component: componentService.getAllComponents()) {
			System.out.println(component);
		}
	}
}
