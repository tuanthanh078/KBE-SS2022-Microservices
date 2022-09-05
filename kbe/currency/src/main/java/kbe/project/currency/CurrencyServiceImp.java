package kbe.project.currency;

import static kbe.project.currency.Currency.*;

public class CurrencyServiceImp implements CurrencyService{

    @Override
    public double switchAmount(double currentAmount, String currentCurrency, String newCurrency) {
        Currency tmpCurrent;
        Currency tmpNew;

        tmpCurrent = matchStringToCurrency(currentCurrency);
        tmpNew = matchStringToCurrency(newCurrency);

        return Currency.switchAmount(currentAmount, tmpCurrent, tmpNew);
    }

    private Currency matchStringToCurrency(String currency) {
        return switch (currency) {
            case "EUR" -> EUR;
            case "GBP" -> GBP;
            case "JPY" -> JPY;
            case "CHF" -> CHF;
            default -> USD;
        };
    }
}
