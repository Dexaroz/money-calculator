package software.ulpgc.arquitecture.control;

import software.ulpgc.arquitecture.io.exchange.ExchangeRateLoader;
import software.ulpgc.arquitecture.model.Currency;
import software.ulpgc.arquitecture.model.ExchangeRate;
import software.ulpgc.arquitecture.model.Money;
import software.ulpgc.arquitecture.view.CurrencyDialog;
import software.ulpgc.arquitecture.view.MoneyDialog;
import software.ulpgc.arquitecture.view.MoneyDisplay;

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
