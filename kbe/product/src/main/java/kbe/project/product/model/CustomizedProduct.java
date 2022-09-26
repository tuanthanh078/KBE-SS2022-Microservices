package kbe.project.product.model;


import kbe.project.dto.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizedProduct {
    private List<SelectedComponents> selectedComponents;
    private Price price;

    public CustomizedProduct(List<SelectedComponents> selectedComponents) {
        this.selectedComponents = selectedComponents;
        this.price = new Price(0D, "USD");
    }
}
