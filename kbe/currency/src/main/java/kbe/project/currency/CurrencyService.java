package kbe.project.currency;

public interface CurrencyService {
    double switchAmount(double currentAmount, String currentCurrency, String newCurrency);
}
