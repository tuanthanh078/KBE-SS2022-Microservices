package kbe.project.price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedComponents implements Serializable {

    private UUID componentId;
    private Double price;
    private int selectedAmount;

    public SelectedComponents(UUID componentId, int selectedAmount) {
        this.componentId = componentId;
        this.selectedAmount = selectedAmount;
        this.price = 0D;
    }
}
