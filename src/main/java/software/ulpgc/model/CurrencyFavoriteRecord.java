package software.ulpgc.model;

import java.util.HashSet;
import java.util.Set;

public class CurrencyFavoriteRecord {

    private final Set<Currency> favoriteCurrencies;

    public CurrencyFavoriteRecord() {
        this.favoriteCurrencies = new HashSet<>();
    }

    public Set<Currency> showFavorite(){
        return favoriteCurrencies;
    }

    public void addFavoriteCurrency(Currency currency){
        favoriteCurrencies.add(currency);
    }

    public void removeFavoriteCurrency(Currency currency){
        favoriteCurrencies.remove(currency);
    }
}
