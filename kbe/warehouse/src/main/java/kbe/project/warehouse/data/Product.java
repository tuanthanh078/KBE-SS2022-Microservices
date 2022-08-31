package kbe.project.warehouse.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Product {

    @Id
    private UUID id;

    private UUID graphicsId;
    private UUID processorId;
    private UUID storageId;

    public Product(){}
    public Product(UUID id, Component graphics, Component processor, Component storage) throws IllegalArgumentException{
        if(graphics == null || !graphics.getType().equals(Component.TYPE_GRAPHICS))throw new IllegalArgumentException("Graphics has to be Component with type \"graphics\".");
        if(processor == null || !processor.getType().equals(Component.TYPE_PROCESSOR))throw new IllegalArgumentException("Processor has to be Component with type \"processor\".");
        if(storage == null || !storage.getType().equals(Component.TYPE_STORAGE))throw new IllegalArgumentException("Storage has to be Component with type \"storage\".");

        this.id = id;
        this.graphicsId = graphics.getId();
        this.processorId = processor.getId();
        this.storageId = storage.getId();
    }

    public UUID getId() {
        return id;
    }

    public UUID getGraphicsId() {
        return graphicsId;
    }

    public UUID getProcessorId() {
        return processorId;
    }

    public UUID getStorageId() {
        return storageId;
    }
}
