package kbe.project.product.examples;

import kbe.project.product.model.Component;
import kbe.project.product.model.Product;
import kbe.project.product.service.ComponentService;

import java.util.List;
import java.util.UUID;

public class ExampleEntries {

    ComponentService componentService;

    public ExampleEntries(ComponentService componentService) {
        this.componentService = componentService;
    }

    public Product defineProduct1() {
        List<Component> componentList = componentService.getAllComponents();
        return new Product(
                UUID.fromString("e8051183-3f32-4864-911d-232094764821"),
                componentList.stream().filter(component -> component.getName().equals("GEFORCE RTX 3060")).findFirst().orElseThrow(),
                componentList.stream().filter(component -> component.getName().equals("Core i9 12900K")).findFirst().orElseThrow(),
                componentList.stream().filter(component -> component.getName().equals("ST4000 M004")).findFirst().orElseThrow()
        );
    }

    public Product defineProduct2() {
        List<Component> componentList = componentService.getAllComponents();
        return new Product(
                UUID.fromString("65936e39-2b11-4125-a245-504fe4510df2"),
                componentList.stream().filter(component -> component.getName().equals("GEFORCE RTX 2070")).findFirst().orElseThrow(),
                componentList.stream().filter(component -> component.getName().equals("Core i9 12900K")).findFirst().orElseThrow(),
                componentList.stream().filter(component -> component.getName().equals("ST4000 M004")).findFirst().orElseThrow()
        );
    }

    public Product defineProduct3() {
        List<Component> componentList = componentService.getAllComponents();
        return new Product(
                UUID.fromString("6fac8c23-8483-463a-97c6-c669f819f166"),
                componentList.stream().filter(component -> component.getName().equals("RADEON RX 6600")).findFirst().orElseThrow(),
                componentList.stream().filter(component -> component.getName().equals("Core i5 12400")).findFirst().orElseThrow(),
                componentList.stream().filter(component -> component.getName().equals("ST4000 M004")).findFirst().orElseThrow()
        );
    }

    public Product defineProduct4() {
        List<Component> componentList = componentService.getAllComponents();
        return new Product(
                UUID.fromString("492f77ae-0509-497a-bf13-3682cf743c96"),
                componentList.stream().filter(component -> component.getName().equals("GEFORCE RTX 2070")).findFirst().orElseThrow(),
                componentList.stream().filter(component -> component.getName().equals("Core i5 12400")).findFirst().orElseThrow(),
                componentList.stream().filter(component -> component.getName().equals("ST4000 M006")).findFirst().orElseThrow()
        );
    }

    public Product defineProduct5() {
        List<Component> componentList = componentService.getAllComponents();
        return new Product(
                UUID.fromString("b5c3f767-c9c6-4f06-85cd-317bb65d358d"),
                componentList.stream().filter(component -> component.getName().equals("GEFORCE RTX 3060")).findFirst().orElseThrow(),
                componentList.stream().filter(component -> component.getName().equals("Core i5 12400")).findFirst().orElseThrow(),
                componentList.stream().filter(component -> component.getName().equals("ST4000 M006")).findFirst().orElseThrow()
        );
    }
}
