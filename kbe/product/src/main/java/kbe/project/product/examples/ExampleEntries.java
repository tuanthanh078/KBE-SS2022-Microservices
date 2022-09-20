package kbe.project.product.examples;

import kbe.project.product.model.Component;
import kbe.project.product.model.Product;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.text.SimpleDateFormat;


public class ExampleEntries {

    private static final SimpleDateFormat COMPONENT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    public static Set<Component> getExampleComponentSet1() {
        Set<Component> tmpSet = new HashSet<Component>();
        tmpSet.add(getExampleGraphics1());
        tmpSet.add(getExampleStorage1());
        tmpSet.add(getExampleProcessor1());
        return tmpSet;
    }

    public static Set<Component> getExampleComponentSet2() {
        Set<Component> tmpSet = new HashSet<Component>();
        tmpSet.add(getExampleGraphics1());
        tmpSet.add(getExampleStorage2());
        tmpSet.add(getExampleProcessor1());
        return tmpSet;
    }

    public static Set<Component> getExampleComponentSet3() {
        Set<Component> tmpSet = new HashSet<Component>();
        tmpSet.add(getExampleGraphics1());
        tmpSet.add(getExampleStorage1());
        tmpSet.add(getExampleProcessor2());
        return tmpSet;
    }

    public static Set<Component> getExampleComponentSet4() {
        Set<Component> tmpSet = new HashSet<Component>();
        tmpSet.add(getExampleGraphics2());
        tmpSet.add(getExampleStorage2());
        tmpSet.add(getExampleProcessor1());
        return tmpSet;
    }

    public static Set<Component> getExampleComponentSet5() {
        Set<Component> tmpSet = new HashSet<Component>();
        tmpSet.add(getExampleGraphics2());
        tmpSet.add(getExampleStorage2());
        tmpSet.add(getExampleProcessor2());
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
                    false
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
                    true
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
                    true
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
                    true
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
                    true
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
                    false
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Product getExampleProduct1() {
        return new Product(
                UUID.fromString("e8051183-3f32-4864-911d-232094764821"),
                ExampleEntries.getExampleComponentSet1());
    }

    public static Product getExampleProduct2() {
        return new Product(
                UUID.fromString("65936e39-2b11-4125-a245-504fe4510df2"),
                ExampleEntries.getExampleComponentSet2());
    }

    public static Product getExampleProduct3() {
        return new Product(
                UUID.fromString("6fac8c23-8483-463a-97c6-c669f819f166"),
                ExampleEntries.getExampleComponentSet3());
    }

    public static Product getExampleProduct4() {
        return new Product(
                UUID.fromString("91a3792d-e66f-4e8a-abd1-0c4011cbfd9e"),
                ExampleEntries.getExampleComponentSet4());
    }

    public static Product getExampleProduct5() {
        return new Product(
                UUID.fromString("29a84033-4185-4c08-adf7-487cbd8719c4"),
                ExampleEntries.getExampleComponentSet5());
    }

    public static Set<Product> getExampleProductSet() {
        Set<Product> set = new HashSet<Product>();
        set.add(getExampleProduct1());
        set.add(getExampleProduct2());
        set.add(getExampleProduct3());
        set.add(getExampleProduct4());
        set.add(getExampleProduct5());
        return set;
    }
}
