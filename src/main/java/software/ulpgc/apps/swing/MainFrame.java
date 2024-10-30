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
        this.setMinimumSize(new Dimension(800,600));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(17, 21, 24));

        JPanel toolbar = (JPanel) toolbar();
        toolbar.setOpaque(false);
        this.add(toolbar, BorderLayout.SOUTH);

        this.add(topMenu(), BorderLayout.NORTH);

        JPanel compositeDialog = (JPanel) compositeDialog();
        compositeDialog.setOpaque(false);
        this.add(compositeDialog, BorderLayout.CENTER);
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

    public void put(String key, Command value) {
        commands.put(key, value);
    }

    private Component topMenu() {
        SwingTopMenuComponent topMenuComponent = new SwingTopMenuComponent("Currency", getClass().getResource("/logo.png"));
        return (JPanel) topMenuComponent.getComponent();
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
