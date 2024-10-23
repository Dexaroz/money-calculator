package software.ulpgc.io;

import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRate;

import java.time.LocalDate;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency currencyFrom, Currency currencyTo);
    ExchangeRate load(Currency currencyFrom, Currency currencyTo, LocalDate localDate);
}
