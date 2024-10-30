package software.ulpgc.apps.swing;

import software.ulpgc.model.Currency;
import software.ulpgc.view.CurrencyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private final List<Currency> currencies;
    private final JComboBox<Currency> selector;

    public SwingCurrencyDialog(List<Currency> currencies) {
        this.currencies = currencies;
        this.add(selector = selector());
    }

    private JComboBox<Currency> selector() {
        JComboBox<Currency> comboBox = new JComboBox<>();
        comboBox.setOpaque(true);
        for (Currency currency : currencies)
            comboBox.addItem(currency);
        return comboBox;
    }

    @Override
    public Currency get() {
        return currencies.get(selector.getSelectedIndex());
    }
}
