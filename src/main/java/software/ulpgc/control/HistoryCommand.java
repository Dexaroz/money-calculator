package software.ulpgc.control;

import software.ulpgc.apps.swing.SwingMoneyDialog;
import software.ulpgc.model.ExchangeRecord;

public class HistoryCommand implements Command {
    private final SwingMoneyDialog moneyDialog;
    private final SwingMoneyDialog currencyDialog;
    private final ExchangeRecord exchangeRecord;

    public HistoryCommand(SwingMoneyDialog moneyDialog, SwingMoneyDialog currencyDialog, ExchangeRecord exchangeRecord) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRecord = exchangeRecord;
    }

    @Override
    public void execute() {

    }
}
