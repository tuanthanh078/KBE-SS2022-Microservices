package kbe.project.price;

import kbe.project.dto.Price;

import java.util.List;


public interface PriceService {
    Price calculatePrice(List<SelectedHardware> selectedHardwares);
}
