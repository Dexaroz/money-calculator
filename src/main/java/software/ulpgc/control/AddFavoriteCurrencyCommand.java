package software.ulpgc.control;

import software.ulpgc.model.CurrencyFavoriteRecord;
import software.ulpgc.view.CurrencyDialog;

public class AddFavoriteCurrencyCommand implements Command {
    private final CurrencyDialog currencyDialog;
    private final CurrencyFavoriteRecord currenciesFavorite;

    public AddFavoriteCurrencyCommand(CurrencyDialog currencyDialog, CurrencyFavoriteRecord currenciesFavorite) {
        this.currencyDialog = currencyDialog;
        this.currenciesFavorite = currenciesFavorite;
    }

    @Override
    public void execute() {
        currenciesFavorite.addFavoriteCurrency(currencyDialog.get());
    }
}
