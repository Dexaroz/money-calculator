package software.ulpgc.io;

import software.ulpgc.model.Currency;

public interface CurrencyDeserializer {
    Currency deserialize(String line);
}
