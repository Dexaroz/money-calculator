package software.ulpgc.apps.swing.content;

import software.ulpgc.arquitecture.model.Money;
import software.ulpgc.arquitecture.view.MoneyDialog;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final JTextField amountField;
    private final SwingCurrencyDialog currencyDialog;

    public SwingMoneyDialog(SwingCurrencyDialog currencyDialog) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.amountField = amountField();
        this.add(amountField);
        this.currencyDialog = currencyDialog;
        this.add(this.currencyDialog);

        currencyDialog.setOpaque(false);
        amountField.setOpaque(false);
    }

    private JTextField amountField() {
        JTextField textField = new JTextField(15);

        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        textField.setForeground(Color.WHITE);
        textField.setBackground(new Color(44, 44, 46));
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(new SwingRoundedBorder(1));

        textField.setEnabled(true);
        textField.setEditable(true);

        return textField;
    }

    @Override
    public Money get() {
            String amountText = amountField.getText().trim();
            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Amount field cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            try {
                return new Money(Double.parseDouble(amountText), currencyDialog.get());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid number format", "Input Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
    }
}
