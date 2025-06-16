package software.ulpgc.arquitecture.control;

import software.ulpgc.arquitecture.model.CurrencyFavoriteRecord;
import software.ulpgc.arquitecture.view.CurrencyDialog;

import java.sql.SQLException;

public class AddFavoriteCurrencyCommand implements Command {
    private final CurrencyDialog currencyDialog;
    private final CurrencyFavoriteRecord currenciesFavorite;

    public AddFavoriteCurrencyCommand(CurrencyDialog currencyDialog, CurrencyFavoriteRecord currenciesFavorite) throws SQLException {
        this.currencyDialog = currencyDialog;
        this.currenciesFavorite = currenciesFavorite;
    }

    @Override
    public void execute() throws SQLException {
        currenciesFavorite.addFavoriteCurrency(currencyDialog.get());
    }
}
