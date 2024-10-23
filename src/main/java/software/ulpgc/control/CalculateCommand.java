package software.ulpgc.control;

import software.ulpgc.io.ExchangeRateLoader;
import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRate;
import software.ulpgc.model.Money;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.MoneyDialog;
import software.ulpgc.view.MoneyDisplay;

public class CalculateCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final MoneyDisplay moneyDisplay;

    public CalculateCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader exchangeRateLoader, MoneyDisplay moneyDisplay) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute() {
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();
        ExchangeRate exchangeRate = exchangeRateLoader.load(money.getCurrency(), currency);
        Money result = new Money(money.getAmount() * exchangeRate.getRate(), currency);
        moneyDisplay.display(result);
    }
}
