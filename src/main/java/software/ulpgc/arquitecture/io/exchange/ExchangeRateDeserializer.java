package software.ulpgc.arquitecture.io.exchange;

import software.ulpgc.arquitecture.model.ExchangeRate;

public interface ExchangeRateDeserializer {
    ExchangeRate deserialize(String line);
}
