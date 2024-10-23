package software.ulpgc.io;

import software.ulpgc.model.Currency;

public interface CurrencyLookup {
    Currency get(String code);
}
