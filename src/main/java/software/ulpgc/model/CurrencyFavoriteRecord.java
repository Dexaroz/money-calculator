package software.ulpgc.model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyFavoriteRecord {

    private final List<Currency> favoriteCurrencies;

    public CurrencyFavoriteRecord() {
        this.favoriteCurrencies = new ArrayList<>();
    }

    public List<Currency> showFavorite(){
        return favoriteCurrencies;
    }

    public void addFavoriteCurrency(Currency currency){
        if (!favoriteCurrencies.contains(currency)) {
            favoriteCurrencies.add(currency);
        }
    }

    public void removeFavoriteCurrency(Currency currency){
        favoriteCurrencies.remove(currency);
    }
}
