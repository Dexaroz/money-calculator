package software.ulpgc.apps.mock;

import software.ulpgc.model.Currency;
import software.ulpgc.view.CurrencyDialog;

import java.util.List;

public class MockCurrencyDialog implements CurrencyDialog {
    private final List<Currency> currencies;

    public MockCurrencyDialog(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public Currency get() {
        return currencies.get(0);
    }
}
