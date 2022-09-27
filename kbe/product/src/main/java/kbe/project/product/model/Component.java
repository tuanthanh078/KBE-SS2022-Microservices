package kbe.project.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Double price;
    private float length;
    private float width;
    private int power;
    private boolean deliverable;
    private Double osmLat;
    private Double osmLon;

    public SelectedComponents toSelectedComponents() {
        return new SelectedComponents(this.id,this.price);
    }
}
