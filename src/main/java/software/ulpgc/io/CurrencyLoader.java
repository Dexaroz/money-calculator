package software.ulpgc.io;

import software.ulpgc.model.Currency;

import java.io.IOException;
import java.util.List;

public interface CurrencyLoader {
    List<Currency> loads() throws IOException;
}
