package software.ulpgc.arquitecture.model;

import software.ulpgc.arquitecture.io.currency.DatabaseCurrencyReader;
import software.ulpgc.arquitecture.io.currency.DatabaseCurrencyWriter;

import java.io.File;
import java.util.List;

public class CurrencyFavoriteRecord {

    private final List<Currency> favoriteCurrencies;
    private final File databaseFile;

    public CurrencyFavoriteRecord(File databaseFile) {
        this.databaseFile = databaseFile;

        try (DatabaseCurrencyReader databaseCurrencyReader = new DatabaseCurrencyReader(databaseFile)){
            this.favoriteCurrencies = databaseCurrencyReader.readAllFavorities();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Currency> showFavorite(){
        return favoriteCurrencies;
    }

    public void addFavoriteCurrency(Currency currency) {
        if (!favoriteCurrencies.contains(currency)) {
            favoriteCurrencies.add(currency);

            try (DatabaseCurrencyWriter databaseFavoriteCurrencyWriter = new DatabaseCurrencyWriter(databaseFile)) {
                databaseFavoriteCurrencyWriter.write(currency.setFavority());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
