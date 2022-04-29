package kbe.project.price;

import kbe.project.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PriceServiceImp implements PriceService {
    @Override
    public Price calculatePrice(List<SelectedHardware> selectedHardwares) {
        System.out.println(selectedHardwares.toString());
        Double price = 0D;

        for (SelectedHardware selectedHardware: selectedHardwares) {
            System.out.println(selectedHardware);
            price += selectedHardware.getPriceUSD() * selectedHardware.getSelectedAmount();
        }

        return new Price(price, "USD");
    }
}
