package software.ulpgc.arquitecture.io;

import software.ulpgc.arquitecture.model.Currency;

public interface CurrencyDeserializer {
    Currency deserialize(String line);
}
