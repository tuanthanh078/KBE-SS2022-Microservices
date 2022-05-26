package kbe.project.warehouse.data;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
public class Component {

    @Id
    private UUID id;

    private Date date;
    private String brand;
    private String name;
    private String component;
    private String location;
    private float price;
    private float length;
    private float width;
    private int power;
    private boolean deliverable;

    protected Component(){}
    public Component(Date date, String brand, String name, String component, String location, float price, float length, float width, int power, boolean deliverable) {
        id = UUID.randomUUID();

        this.date = date;
        this.brand = brand;
        this.name = name;
        this.component = component;
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

    public String getComponent() {
        return component;
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
