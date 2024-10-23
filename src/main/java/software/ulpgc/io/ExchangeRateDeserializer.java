package software.ulpgc.io;

import software.ulpgc.model.ExchangeRate;

public interface ExchangeRateDeserializer {
    ExchangeRate deserialize(String line);
}
