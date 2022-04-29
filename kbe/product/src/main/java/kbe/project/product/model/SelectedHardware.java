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
public class SelectedHardware implements Serializable {

    private Long hardwareId;
    private Double priceUSD;
    private int selectedAmount;

    public SelectedHardware(Long hardwareId, int selectedAmount) {
        this.hardwareId = hardwareId;
        this.selectedAmount = selectedAmount;
        this.priceUSD = 0D;
    }
}
