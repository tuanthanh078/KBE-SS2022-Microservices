package kbe.project.product.model;


import kbe.project.dto.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizedProduct {
    private List<SelectedComponents> selectedComponents;
    private Price price;
    private UUID id;

    public CustomizedProduct(List<SelectedComponents> selectedComponents, UUID id) {
        this.selectedComponents = selectedComponents;
        this.id = id;
        this.price = new Price(0D, "USD");
    }

    public CustomizedProduct(List<SelectedComponents> selectedComponents) {
        this.selectedComponents = selectedComponents;
        this.id = UUID.randomUUID();
        this.price = new Price(0D, "USD");
    }
}
