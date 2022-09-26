package kbe.project.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Document
@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedComponents implements Serializable {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID componentId;
    private Double price;
    private int selectedAmount;

    public SelectedComponents(UUID componentId, int selectedAmount) {
        this.componentId = componentId;
        this.selectedAmount = selectedAmount;
        this.price = 0D;
    }

    public SelectedComponents(String componentId, int selectedAmount) {
        this.componentId = UUID.fromString(componentId);
        this.selectedAmount = selectedAmount;
        this.price = 0D;
    }
}
