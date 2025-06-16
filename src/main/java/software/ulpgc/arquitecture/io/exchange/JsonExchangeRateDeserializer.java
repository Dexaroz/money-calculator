package software.ulpgc.arquitecture.io.exchange;

import software.ulpgc.arquitecture.io.currency.CurrencyLookup;
import software.ulpgc.arquitecture.model.ExchangeRate;

public class JsonExchangeRateDeserializer implements ExchangeRateDeserializer {
    private final CurrencyLookup lookup;

    public JsonExchangeRateDeserializer(CurrencyLookup lookup) {
        this.lookup = lookup;
    }

    @Override
    public ExchangeRate deserialize(String line) {
        return null;
    }
}
