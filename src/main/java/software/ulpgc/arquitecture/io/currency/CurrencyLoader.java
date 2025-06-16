package software.ulpgc.arquitecture.io.currency;

import software.ulpgc.arquitecture.model.Currency;

import java.io.IOException;
import java.util.List;

public interface CurrencyLoader {
    List<Currency> loads() throws IOException;
}
