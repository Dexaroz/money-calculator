package software.ulpgc.apps.swing;

import software.ulpgc.model.Money;
import software.ulpgc.view.MoneyDialog;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final JTextField amountField;
    private final SwingCurrencyDialog currencyDialog;

    public SwingMoneyDialog(SwingCurrencyDialog currencyDialog) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(this.amountField = amountField());
        this.add(this.currencyDialog = currencyDialog);

        this.setBackground(Color.WHITE);
    }

    private JTextField amountField() {
        JTextField textField = new JTextField();
        textField.setColumns(12);
        return textField;
    }

    @Override
    public Money get() {
        return new Money(Double.parseDouble(amountField.getText()), currencyDialog.get());
    }
}
