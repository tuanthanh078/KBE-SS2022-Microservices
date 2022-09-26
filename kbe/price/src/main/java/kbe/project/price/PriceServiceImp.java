package kbe.project.price;

import kbe.project.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PriceServiceImp implements PriceService {
    @Override
    public Price calculatePrice(List<SelectedComponents> selectedComponents) {
        System.out.println(selectedComponents.toString());
        Double price = 0D;

        for (SelectedComponents selectedComponent: selectedComponents) {
            System.out.println(selectedComponent);
            price += selectedComponent.getPrice() * selectedComponent.getSelectedAmount();
        }

        return new Price(price, "USD");
    }
}
