package software.ulpgc.apps.fixeraw;

import software.ulpgc.arquitecture.io.CurrencyLoader;
import software.ulpgc.arquitecture.model.Currency;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class FixerCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> loads() {
        try {
            return toList(loadJson());
        } catch (IOException e) {
            return emptyList();
        }
    }

    private List<Currency> toList(String json) {
        List<Currency> list = new ArrayList<>();
        Map<String, JsonElement> symbols = new Gson().fromJson(json, JsonObject.class).get("symbols").getAsJsonObject().asMap();
        for (String symbol : symbols.keySet()) {
            list.add(new Currency(symbol, symbols.get(symbol).getAsString(), null));
        }
        return list;
    }

    private String loadJson() throws IOException {
        URL url = new URL("http://data.fixer.io/api/symbols?access_key=" + FixerAPI.getApiKey());
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}