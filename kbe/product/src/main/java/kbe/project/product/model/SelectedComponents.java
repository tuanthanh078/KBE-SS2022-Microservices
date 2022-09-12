package kbe.project.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedComponents implements Serializable {

    private Long componentId;
    private Double priceUSD;
    private int selectedAmount;

    public SelectedComponents(Long componentId, int selectedAmount) {
        this.componentId = componentId;
        this.selectedAmount = selectedAmount;
        this.priceUSD = 0D;
    }
}
