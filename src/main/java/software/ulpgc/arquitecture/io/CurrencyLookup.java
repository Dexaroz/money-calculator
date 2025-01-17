package software.ulpgc.arquitecture.io;

import software.ulpgc.arquitecture.model.Currency;

public interface CurrencyLookup {
    Currency get(String code);
}
