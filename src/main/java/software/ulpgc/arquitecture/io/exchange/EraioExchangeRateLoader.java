package software.ulpgc.arquitecture.io.exchange;

import software.ulpgc.arquitecture.model.Currency;
import software.ulpgc.arquitecture.model.ExchangeRate;

public class EraioExchangeRateLoader implements ExchangeRateLoader{

    private final ExchangeRateDeserializer deserializer;

    public EraioExchangeRateLoader(ExchangeRateDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    public ExchangeRate load(Currency currencyFrom, Currency currencyTo){
        return null;
    }
}
