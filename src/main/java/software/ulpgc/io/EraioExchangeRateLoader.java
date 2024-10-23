package software.ulpgc.io;

import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRate;

import java.time.LocalDate;

public class EraioExchangeRateLoader implements ExchangeRateLoader{

    private final ExchangeRateDeserializer deserializer;

    public EraioExchangeRateLoader(ExchangeRateDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    public ExchangeRate load(Currency currencyFrom, Currency currencyTo){
        return null;
    }

    @Override
    public ExchangeRate load(Currency currencyFrom, Currency currencyTo, LocalDate date){
        return null;
    }
}
