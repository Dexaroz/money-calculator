package software.ulpgc.arquitecture.io.exchange;

import software.ulpgc.arquitecture.model.Currency;
import software.ulpgc.arquitecture.model.ExchangeRate;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency currencyFrom, Currency currencyTo);
}
