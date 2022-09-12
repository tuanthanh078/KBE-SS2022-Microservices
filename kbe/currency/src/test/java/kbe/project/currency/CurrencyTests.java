package kbe.project.currency;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

@SpringBootTest
public class CurrencyTests {
    @Test
    public void enumSwitchAmountSameCurrencyNotUSDTest() {
        double currentAmount = 1.0;

        double newAmount = Currency.switchAmount(currentAmount, Currency.CHF, Currency.CHF);

        assertEquals(currentAmount, newAmount, 0.0);
    }

    @Test
    public void enumSwitchAmountTwiceUSDTest() {
        double currentAmount = 12.0;

        double newAmount = Currency.switchAmount(currentAmount, Currency.USD, Currency.USD);

        assertEquals(currentAmount, newAmount, 0.0);
    }

    @Test
    public void enumSwitchAmountFromUSDTest() {
        double currentAmount = 5.0;

        double newAmount = Currency.switchAmount(currentAmount, Currency.USD, Currency.JPY);

        assertEquals(newAmount, 693.45, 0.5);
    }

    @Test
    public void enumSwitchAmountToUSDTest() {
        double currentAmount = 20.0;

        double newAmount = Currency.switchAmount(currentAmount, Currency.GBP, Currency.USD);

        assertEquals(newAmount, 23.3, 0.5);
    }

    @Test
    public void enumSwitchAmountNoUSDTest() {
        double currentAmount = 13.0;

        double newAmount = Currency.switchAmount(currentAmount, Currency.CHF, Currency.GBP);

        assertEquals(newAmount, 11.4, 0.5);
    }

    @Test
    public void enumSwitchAmountWrongActualTest() {
        double currentAmount = 13.0;

        double newAmount = Currency.switchAmount(currentAmount, Currency.JPY, Currency.EUR);

        assertNotEquals(newAmount, 70.0, 0.5);
    }

    @Test
    public void enumSwitchAmountInitializedEnumTest() {
        double currentAmount = 7.0;
        Currency currentCurrency = Currency.GBP;

        double newAmount = currentCurrency.switchAmount(currentAmount, currentCurrency, Currency.JPY);

        assertEquals(newAmount, 1132.00, 5.0);
        assertEquals(currentCurrency, Currency.GBP);
    }

    @Test
    public void serviceSwitchAmount() {
        CurrencyServiceImp currencyServiceImp = new CurrencyServiceImp();
        double currentAmount = 13.0;

        double newAmount = currencyServiceImp.switchAmount(currentAmount, "CHF", "GBP");

        assertEquals(newAmount, 11.4, 0.5);
    }

    @Test
    public void serviceSwitchAmountSameCurrency() {
        CurrencyServiceImp currencyServiceImp = new CurrencyServiceImp();
        double currentAmount = 200.0;

        double newAmount = currencyServiceImp.switchAmount(currentAmount, "JPY", "JPY");

        assertEquals(newAmount, 200.0, 0.0);
    }

    @Test
    public void serviceSwitchAmountDefaultUSDToUSD() {
        CurrencyServiceImp currencyServiceImp = new CurrencyServiceImp();
        double currentAmount = 200.0;

        double newAmount = currencyServiceImp.switchAmount(currentAmount, "test", "USD");

        assertEquals(newAmount, 200.0, 0.0);
    }

    @Test
    public void serviceSwitchAmountDefaultUSDToJPY() {
        CurrencyServiceImp currencyServiceImp = new CurrencyServiceImp();
        double currentAmount = 5.0;

        double newAmount = currencyServiceImp.switchAmount(currentAmount, "test", "JPY");

        assertEquals(newAmount, 693.45, 5.0);
    }
}
