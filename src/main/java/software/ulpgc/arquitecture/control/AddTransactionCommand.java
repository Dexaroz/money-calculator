package software.ulpgc.arquitecture.control;

import software.ulpgc.arquitecture.model.ExchangeRecord;
import software.ulpgc.arquitecture.model.ExchangeTransaction;
import software.ulpgc.arquitecture.view.CurrencyDialog;
import software.ulpgc.arquitecture.view.MoneyDialog;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddTransactionCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRecord exchangeRecord;

    public AddTransactionCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRecord exchangeRecord) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRecord = exchangeRecord;
    }

    @Override
    public void execute() throws SQLException {
        ExchangeTransaction exchangeTransaction = new ExchangeTransaction(moneyDialog.get().getCurrency(), currencyDialog.get(), moneyDialog.get().getAmount(), LocalDate.now());
        exchangeRecord.addExchangeTransaction(exchangeTransaction);
    }
}
