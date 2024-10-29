package software.ulpgc.apps.swing;

import software.ulpgc.control.Command;
import software.ulpgc.model.Currency;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands;
    private final List<Currency> currencies;
    private SwingMoneyDialog moneyDialog;
    private SwingCurrencyDialog currencyDialog;

    public MainFrame(List<Currency> currencies) throws HeadlessException {
        this.currencies = currencies;
        this.commands = new HashMap<>();
        this.setTitle("MoneyCalculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(255,255,255));

        JPanel toolbar = (JPanel) toolbar();
        toolbar.setOpaque(false);
        this.add(toolbar, BorderLayout.SOUTH);

        JPanel topMenu = (JPanel) topMenu();
        this.add(topMenu, BorderLayout.NORTH);

        JPanel compositeDialog = (JPanel) compositeDialog();
        compositeDialog.setOpaque(false);
        this.add(compositeDialog, BorderLayout.CENTER);
    }

    private Component compositeDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(moneyDialog = new SwingMoneyDialog(new SwingCurrencyDialog(currencies)));
        panel.add(currencyDialog = new SwingCurrencyDialog(currencies));
        return panel;
    }

    public void put(String key, Command value) {
        commands.put(key, value);
    }

    private Component topMenu(){
        return new SwingTopMenu("Currency Converter", getClass().getResource("/logo.png"));
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button("calculate"));
        return panel;
    }

    private Component button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }

        });
        return button;
    }

    public MoneyDialog moneyDialog() {
        return moneyDialog;
    }

    public CurrencyDialog currencyDialog() {
        return currencyDialog;
    }
}
