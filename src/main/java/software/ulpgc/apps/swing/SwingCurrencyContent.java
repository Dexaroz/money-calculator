package software.ulpgc.apps.swing;

import software.ulpgc.control.CalculateCommand;
import software.ulpgc.model.Currency;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.MoneyDialog;
import software.ulpgc.view.VisualComponent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingCurrencyContent extends JPanel implements VisualComponent {

    private final SwingMoneyDialog moneyDialog;
    private final SwingCurrencyDialog currencyDialog;
    private final List<Currency> currencies;
    private final Map<String, JButton> commandsButton;

    public SwingCurrencyContent(List<Currency> currencies) {
        this.currencies = currencies;
        this.commandsButton = new HashMap<>();
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(17, 21, 24));

        this.moneyDialog = new SwingMoneyDialog(new SwingCurrencyDialog(currencies));
        this.currencyDialog = new SwingCurrencyDialog(currencies);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(20,0,0,0));

        JLabel tittleLabel = new JLabel("Currency Converter");
        tittleLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        tittleLabel.setForeground(Color.WHITE);

        topPanel.add(tittleLabel);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel toolbar = (JPanel) toolbar();
        toolbar.setOpaque(false);
        this.add(toolbar, BorderLayout.SOUTH);

        JPanel compositeDialog = (JPanel) compositeDialog();
        compositeDialog.setOpaque(false);
        this.add(compositeDialog, BorderLayout.CENTER);
    }

    public void setButtonAction(String buttonName, ActionListener action){
        JButton button = commandsButton.get(buttonName);
        if (button != null){
            button.addActionListener(action);
        }
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton calculateButton = (JButton) button("Calculate");
        panel.add(calculateButton);
        return panel;
    }

    private Component button(String name) {
        JButton button = new JButton(name);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        commandsButton.put(name, button);
        return button;
    }

    private Component compositeDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        SwingMoneyDialog moneyDialog = new SwingMoneyDialog(new SwingCurrencyDialog(currencies));
        moneyDialog.setOpaque(true);
        moneyDialog.setBackground(new Color(17, 21, 24));
        panel.add(moneyDialog);

        SwingCurrencyDialog currencyDialog = new SwingCurrencyDialog(currencies);
        currencyDialog.setOpaque(true);
        currencyDialog.setBackground(new Color(17, 21, 24));
        panel.add(currencyDialog);
        return panel;
    }

    public MoneyDialog moneyDialog() {
        return moneyDialog;
    }

    public CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    @Override
    public Object getComponent() {
        return this;
    }
}
