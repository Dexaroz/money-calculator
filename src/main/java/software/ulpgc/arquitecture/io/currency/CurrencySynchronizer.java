package software.ulpgc.arquitecture.io.currency;

import software.ulpgc.arquitecture.model.Currency;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencySynchronizer {

    public void syncWithWeb(File dbFile, List<Currency> webCurrencies) throws IOException, SQLException {
        List<String> existingCodes = new ArrayList<>();

        try (DatabaseCurrencyReader reader = new DatabaseCurrencyReader(dbFile)) {
            for (Currency c : reader.readAll()) {
                existingCodes.add(c.getCode());
            }
        } catch (Exception e) {
            throw new IOException("Error reading currencies from DB", e);
        }

        try (DatabaseCurrencyWriter writer = new DatabaseCurrencyWriter(dbFile)) {
            for (Currency currency : webCurrencies) {
                if (!existingCodes.contains(currency.getCode())) {
                    writer.write(currency);
                }
            }
        } catch (Exception e) {
            throw new IOException("Error writing currencies to DB", e);
        }
    }
}
