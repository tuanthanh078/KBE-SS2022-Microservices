package kbe.project.warehouse;

import kbe.project.warehouse.services.CSVComponentImporter;
import kbe.project.warehouse.services.CSVProductImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

	@Autowired
	CSVComponentImporter componentImporter;
	@Autowired
	CSVProductImporter productImporter;

	@EventListener(ApplicationReadyEvent.class)
	public void initializeDatabase() throws IOException{
		componentImporter.importComponents();
		productImporter.importProducts();
	}
}
