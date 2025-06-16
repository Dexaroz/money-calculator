package software.ulpgc.arquitecture.io.currency;

import software.ulpgc.arquitecture.model.Currency;

import java.io.IOException;

public interface CurrencyDeleter extends AutoCloseable {
    void delete(Currency currency) throws IOException;
}
