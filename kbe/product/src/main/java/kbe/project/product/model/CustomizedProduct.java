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
    private List<SelectedHardware> selectedHardwares;
    private Price price;

    public CustomizedProduct(List<SelectedHardware> selectedHardwares) {
        this.selectedHardwares = selectedHardwares;
        this.price = new Price(0D, "USD");
    }
}
