package kbe.project.warehouse.model;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class Component {

    public static final String
            TYPE_GRAPHICS = "graphics",
            TYPE_PROCESSOR = "processor",
            TYPE_STORAGE = "storage";

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private Date date;
    private String brand;
    private String name;
    private String type;
    private String location;
    private float price;
    private float length;
    private float width;
    private int power;
    private boolean deliverable;

    public Component(){}
    public Component(UUID id, Date date, String brand, String name, String type, String location, float price, float length, float width, int power, boolean deliverable) {
        this.id = id;
        this.date = date;
        this.brand = brand;
        this.name = name;
        this.type = type;
        this.location = location;
        this.price = price;
        this.length = length;
        this.width = width;
        this.power = power;
        this.deliverable = deliverable;
    }

    public UUID getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public float getPrice() {
        return price;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public int getPower() {
        return power;
    }

    public boolean isDeliverable() {
        return deliverable;
    }
}
