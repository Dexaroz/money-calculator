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

        currencyDialog.setOpaque(false);
        amountField.setOpaque(false);
    }

    private JTextField amountField() {
        JTextField textField = new JTextField(15);

        textField.setBorder(new SwingRoundedBorder(15));
        textField.setPreferredSize(new Dimension(150, 25));
        textField.setFont(new Font("Arial", Font.PLAIN, 9));
        textField.setOpaque(true);
        textField.setBackground(new Color(44, 44, 46));

        return textField;
    }

    @Override
    public Money get() {
        return new Money(Double.parseDouble(amountField.getText()), currencyDialog.get());
    }
}
