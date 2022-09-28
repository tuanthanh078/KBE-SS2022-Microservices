package kbe.project.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Document
@Data
public class Product {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToMany()
    @JoinTable(name = "product_component",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id"))
    Set<Component> components;

    public Product(){}
    public Product(UUID id, Component graphics, Component processor, Component storage) throws IllegalArgumentException{
        if(graphics == null || !graphics.getType().equals(Component.TYPE_GRAPHICS))throw new IllegalArgumentException("Graphics has to be Component with type \"graphics\".");
        if(processor == null || !processor.getType().equals(Component.TYPE_PROCESSOR))throw new IllegalArgumentException("Processor has to be Component with type \"processor\".");
        if(storage == null || !storage.getType().equals(Component.TYPE_STORAGE))throw new IllegalArgumentException("Storage has to be Component with type \"storage\".");

        this.id = id;
        components = new HashSet<>();
        components.add(graphics);
        components.add(processor);
        components.add(storage);
    }

    public UUID getId() {
        return id;
    }

    public Component getGraphics() {
        return getComponentWithType(Component.TYPE_GRAPHICS);
    }

    public Component getProcessor() {
        return getComponentWithType(Component.TYPE_PROCESSOR);
    }

    public Component getStorage() {
        return getComponentWithType(Component.TYPE_STORAGE);
    }

    private Component getComponentWithType(String type) throws IllegalArgumentException{
        for(Component component : components)
            if(component.getType().equals(type))return component;
        throw new IllegalArgumentException("Component type \"" + type + "\" is not valid.");
    }

    public Product(UUID id, Set<Component> components) {
        this.id = id;
        this.components = components;
    }
}
