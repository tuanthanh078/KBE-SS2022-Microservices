package kbe.project.product.examples;

import kbe.project.product.model.Component;
import kbe.project.product.model.Product;
import kbe.project.product.service.ComponentService;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.text.SimpleDateFormat;


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
                componentList.stream().filter(component -> component.getName().equals("GEFORCE RTX 2070")).findFirst().orElseThrow(),
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

    private static final SimpleDateFormat COMPONENT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public Set<Component> getExampleComponents() {
        Set<Component> tmpSet = new HashSet<Component>();
        tmpSet.add(getExampleGraphics1());
        tmpSet.add(getExampleGraphics2());
        tmpSet.add(getExampleStorage1());
        tmpSet.add(getExampleStorage2());
        tmpSet.add(getExampleProcessor1());
        tmpSet.add(getExampleProcessor2());
        tmpSet.add(getExampleComponent1());
        tmpSet.add(getExampleComponent2());
        tmpSet.add(getExampleComponent3());
        tmpSet.add(getExampleComponent4());
        return tmpSet;
    }

    public static Component getExampleGraphics1() {
        try {
            return new Component(UUID.fromString("d8cd9749-5522-4131-98f2-390fc9eed1e3"),
                    COMPONENT_DATE_FORMAT.parse("2018-10-17"),
                    "Nvidia",
                    "GEFORCE RTX 2070",
                    "graphics",
                    "Hannover",
                    539.90D,
                    228.6f,
                    112.6f,
                    175,
                    false,
                    1D,
                    1D
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Component getExampleGraphics2() {
        try {
            return new Component(UUID.fromString("e7e0cc9d-a300-4032-8b6c-44d439845676"),
                    COMPONENT_DATE_FORMAT.parse("2021-02-25"),
                    "Nvidia",
                    "GEFORCE RTX 3060",
                    "graphics",
                    "Berlin",
                    449.00D,
                    242.0f,
                    112.6f,
                    170,
                    true,
                    1D,
                    1D
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Component getExampleProcessor1() {
        try {
            return new Component(UUID.fromString("9b2785c3-87ee-4054-9e9b-800e678eb398"),
                    COMPONENT_DATE_FORMAT.parse("2021-11-04"),
                    "Intel",
                    "Core i9 12900K",
                    "processor",
                    "Hamburg",
                    599.00D,
                    45.0f,
                    37.5f,
                    240,
                    true,
                    1D,
                    1D
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Component getExampleProcessor2() {
        try {
            return new Component(UUID.fromString("1a944994-93b9-4933-9d7d-410794e7355c"),
                    COMPONENT_DATE_FORMAT.parse("2022-05-01"),
                    "Intel",
                    "Core i5 12400",
                    "processor",
                    "Frankfurt",
                    199.00D,
                    45.0f,
                    37.5f,
                    117,
                    true,
                    1D,
                    1D
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Component getExampleStorage1() {
        try {
            return new Component(UUID.fromString("a24fe9b5-d49b-4fb7-a990-7391c95e932a"),
                    COMPONENT_DATE_FORMAT.parse("2020-05-04"),
                    "Seagate",
                    "ST4000 M004",
                    "storage",
                    "Dortmund",
                    45.99D,
                    147.0f,
                    102.0f,
                    4,
                    true,
                    1D,
                    1D
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Component getExampleStorage2() {
        try {
            return new Component(UUID.fromString("31f56314-f52f-4f5e-b48e-2f66139f67d9"),
                    COMPONENT_DATE_FORMAT.parse("2021-05-04"),
                    "Seagate",
                    "ST4000 M006",
                    "storage",
                    "Berlin",
                    69.99D,
                    150.0f,
                    102.0f,
                    5,
                    false,
                    1D,
                    1D
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Component getExampleComponent1() {
        try {
            return new Component(UUID.fromString("49fe7fff-d676-4cb2-b1c4-dc41e50e66cb"),
                    COMPONENT_DATE_FORMAT.parse("2020-01-01"),
                    "be quiet!",
                    "STRAIGHT POWER 11",
                    "power supply",
                    "Mains",
                    159.90D,
                    160.0f,
                    150.0f,
                    1200,
                    true,
                    1D,
                    1D
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Component getExampleComponent2() {
        try {
            return new Component(UUID.fromString("f3609db9-1c70-45e3-9e93-fb395b8af0ce"),
                    COMPONENT_DATE_FORMAT.parse("2021-06-07"),
                    "EVGA",
                    "SuperNOVA 650 GT",
                    "power supply",
                    "Berlin",
                    97.90D,
                    150.0f,
                    150.0f,
                    650,
                    false,
                    1D,
                    1D
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Component getExampleComponent3() {
        try {
            return new Component(UUID.fromString("bbfa9a5a-67b9-44b5-8da4-ce16adf08305"),
                    COMPONENT_DATE_FORMAT.parse("2019-12-12"),
                    "MSI",
                    "Z97 GAMING 3",
                    "board",
                    "Rostock",
                    129.00D,
                    3050.0f,
                    2440.0f,
                    0,
                    true,
                    1D,
                    1D
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Component getExampleComponent4() {
        try {
            return new Component(UUID.fromString("e7e0cc9d-a300-4032-8b6c-44d439845676"),
                    COMPONENT_DATE_FORMAT.parse("2021-03-22"),
                    "MSI",
                    "MEG Z690 UNIFY",
                    "board",
                    "Dresden",
                    550.00D,
                    3050.0f,
                    2440.0f,
                    0,
                    true,
                    1D,
                    1D
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
