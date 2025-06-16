package software.ulpgc.arquitecture.io.currency;

import software.ulpgc.arquitecture.model.Currency;

import java.io.IOException;
import java.util.List;

public interface CurrencyReader extends AutoCloseable {
    Currency read(String name) throws IOException;
    List<Currency> readAll() throws IOException;
}
