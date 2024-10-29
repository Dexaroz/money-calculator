package software.ulpgc.apps.mock;

import software.ulpgc.model.Money;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.MoneyDialog;

public class MockMoneyDialog implements MoneyDialog {
    private final CurrencyDialog dialog;

    public MockMoneyDialog(CurrencyDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public Money get() {
        return new Money(10, dialog.get());
    }
}
