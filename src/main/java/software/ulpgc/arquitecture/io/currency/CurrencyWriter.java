package software.ulpgc.arquitecture.io.currency;

import software.ulpgc.arquitecture.model.Currency;

import java.io.IOException;

public interface CurrencyWriter extends AutoCloseable{
    void write(Currency currency) throws IOException;
}
