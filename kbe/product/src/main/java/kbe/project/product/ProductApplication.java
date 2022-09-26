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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

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

	private JSONObject osmData;

	@EventListener(ApplicationReadyEvent.class)
	public void initializeDatabase() {
		initializeComponentEntries();
		initializeProductEntries();
	}

	private void initializeProductEntries() {
		if (productRepository.count() == 0) {
			ExampleEntries exampleEntries = new ExampleEntries(this.componentService);
			productService.addProduct(exampleEntries.defineProduct1());
			productService.addProduct(exampleEntries.defineProduct2());
			productService.addProduct(exampleEntries.defineProduct3());
			productService.addProduct(exampleEntries.defineProduct4());
			productService.addProduct(exampleEntries.defineProduct5());
		}

		System.out.println("Products in database:");

		for (Product product: productService.getAllProducts()) {
			System.out.println(product.getId() + ":");
			System.out.println(product.getGraphics().getId().toString() + ":");
			System.out.println(product.getGraphics().getName());
			System.out.println(product.getStorage().getId().toString() + ":");
			System.out.println(product.getStorage().getName());
			System.out.println(product.getProcessor().getId().toString() + ":");
			System.out.println(product.getProcessor().getName());
		}
	}

	private void initializeComponentEntries() {
		if (componentRepository.count() == 0) {
			ExampleEntries exampleEntries = new ExampleEntries(this.componentService);

			System.out.println("Fetching OSM-Data...");

			for (Component component: exampleEntries.getExampleComponents()) {
				setOsmData(component.getLocation());
				component.setOsmLat(getOsmLat());
				component.setOsmLon(getOsmLon());
				componentService.addComponent(component);
			}
		}

		System.out.println("Components in database:");

		for (Component component: componentService.getAllComponents()) {
			System.out.println(component.getId().toString() + ":");
			System.out.println(component.getName());
		}
	}

	private Double getOsmLat() {
		if (osmData.isEmpty())
			return 1D;
		return Double.valueOf(osmData.get("lat").toString());
	}

	private Double getOsmLon() {
		if (osmData.isEmpty())
			return 1D;
		return Double.valueOf(osmData.get("lon").toString());
	}

	private void setOsmData(String location) {
		try {
			URL url = new URL("https://nominatim.openstreetmap.org/search?q="+location+"+germany&format=json");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			int responseCode = conn.getResponseCode();

			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode " + responseCode);
			}
			else {
				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}
				scanner.close();

				JSONParser jsonParser = new JSONParser();
				JSONArray dataObject = (JSONArray) jsonParser.parse(String.valueOf(informationString));

				if (!dataObject.isEmpty()) {
					osmData = (JSONObject) dataObject.get(0);
				}
			}
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
