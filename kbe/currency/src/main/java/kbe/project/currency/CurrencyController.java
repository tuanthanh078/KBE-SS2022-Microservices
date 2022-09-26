package kbe.project.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    @ResponseBody Double
    changeCurrency(@RequestParam("amount") double amount,
                   @RequestParam("currentCurrency") String currentCurrency,
                   @RequestParam("newCurrency") String newCurrency) {
        return currencyService.switchAmount(amount, currentCurrency, newCurrency);}
}
