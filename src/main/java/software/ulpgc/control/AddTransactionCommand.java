package software.ulpgc.control;

import software.ulpgc.model.ExchangeRecord;
import software.ulpgc.model.ExchangeTransaction;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.MoneyDialog;

import java.time.LocalDate;

public class AddTransactionCommand implements Command { /* Make it without Swing classes */
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRecord exchangeRecord;

    public AddTransactionCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRecord exchangeRecord) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRecord = exchangeRecord;
    }

    @Override
    public void execute() {
        ExchangeTransaction exchangeTransaction = new ExchangeTransaction(moneyDialog.get().getCurrency(), currencyDialog.get(), moneyDialog.get().getAmount(), LocalDate.now());
        exchangeRecord.addExchangeTransaction(exchangeTransaction);
    }
}
