package software.ulpgc.apps.mock;

import software.ulpgc.io.CurrencyLoader;
import software.ulpgc.model.Currency;

import java.util.List;

public class MockCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> loads() {
        return List.of(
                new Currency("USD", "Dólar", "$"),
                new Currency("EUR", "Euro", "€")
        );
    }
}
