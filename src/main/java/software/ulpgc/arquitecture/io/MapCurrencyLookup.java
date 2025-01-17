package software.ulpgc.arquitecture.io;

import software.ulpgc.arquitecture.model.Currency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapCurrencyLookup implements CurrencyLookup{

    private final Map<String, Currency> map;

    public MapCurrencyLookup(List<Currency> currencies) {
        this.map = toMap(currencies);
    }

    private static Map<String, Currency> toMap(List<Currency> currencies) {
        Map<String, Currency> result = new HashMap<>();
        for (Currency currency : currencies)
            result.put(currency.getCode(), currency);
        return result;
    }


    @Override
    public Currency get(String code) {
        return map.get(code);
    }
}
