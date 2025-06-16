package software.ulpgc.apps.fixeraw;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import software.ulpgc.arquitecture.io.exchange.ExchangeRateLoader;
import software.ulpgc.arquitecture.model.Currency;
import software.ulpgc.arquitecture.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FixerExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            String json = loadJson(from, to);
            return toExchangeRate(json, from, to);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String loadJson(Currency from, Currency to) throws IOException {
        String urlString = String.format("http://data.fixer.io/api/latest?access_key=%s&symbols=%s,%s",
                FixerAPI.getApiKey(),
                from.getCode(),
                to.getCode());

        URL url = new URL(urlString);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }

    private ExchangeRate toExchangeRate(String json, Currency from, Currency to) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        double rate = jsonObject.getAsJsonObject("rates").get(to.getCode()).getAsDouble();
        return new ExchangeRate(from, to, rate);
    }
}
