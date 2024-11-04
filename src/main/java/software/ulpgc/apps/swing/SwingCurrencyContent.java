package software.ulpgc.apps.swing;

import software.ulpgc.view.MoneyDisplay;
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
    private SwingMoneyDisplay moneyDisplay;
    private final List<Currency> currencies;
    private final Map<String, JButton> commandsButton;

    public SwingCurrencyContent(List<Currency> currencies) {
        this.currencies = currencies;
        this.commandsButton = new HashMap<>();

        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(17, 21, 24));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        this.add(getTitlePanel(gridBagConstraints), gridBagConstraints);
        this.add(getCompositeDialogPanel(gridBagConstraints, this.moneyDialog = new SwingMoneyDialog(new SwingCurrencyDialog(currencies)), this.currencyDialog = new SwingCurrencyDialog(currencies)), gridBagConstraints);
        this.add(moneyDisplayPretty((SwingMoneyDisplay) getMoneyDisplayPanel(gridBagConstraints)), gridBagConstraints);
        this.add(getToolbarPanel(gridBagConstraints), gridBagConstraints);
    }

    private Component getTitlePanel(GridBagConstraints gridBagConstraints){
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(5,0,60,0);
        JLabel tittleLabel = new JLabel("Currency Converter");
        tittleLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        tittleLabel.setForeground(Color.WHITE);

        return tittleLabel;
    }

    private Component getCompositeDialogPanel(GridBagConstraints gridBagConstraints, SwingMoneyDialog moneyDialog, SwingCurrencyDialog currencyDialog){
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(20,0,10,0);
        JPanel compositeDialog = (JPanel) compositeDialog(moneyDialog, currencyDialog);
        compositeDialog.setOpaque(false);

        return compositeDialog;
    }

    private Component getMoneyDisplayPanel(GridBagConstraints gridBagConstraints){
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 20);
        moneyDisplay = new SwingMoneyDisplay();

        return moneyDisplay;
    }

    private Component getToolbarPanel(GridBagConstraints gridBagConstraints){
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(20, 0, 20, 0);
        JPanel toolbar = (JPanel) toolbar();
        toolbar.setOpaque(false);

        return toolbar;
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
        button.setFont(new Font("Arial", Font.PLAIN, 10));
        button.setBackground(new Color(0, 122, 255));
        commandsButton.put(name, button);
        return button;
    }

    private Component moneyDisplayPretty(SwingMoneyDisplay moneyDisplayUnpretty){
        this.moneyDisplay = moneyDisplayUnpretty;
        moneyDisplay.setFont(new Font("Arial", Font.PLAIN, 15));
        moneyDisplay.setForeground(Color.WHITE);
        return moneyDisplay;
    }

    private Component compositeDialog(SwingMoneyDialog moneyDialog, SwingCurrencyDialog currencyDialog) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        moneyDialog.setOpaque(true);
        moneyDialog.setBackground(new Color(17, 21, 24));
        panel.add(moneyDialog, BorderLayout.WEST);

        currencyDialog.setOpaque(true);
        currencyDialog.setBackground(new Color(17, 21, 24));
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(0,1,0,0));
        topPanel.setOpaque(false);
        topPanel.add(currencyDialog);
        panel.add(topPanel, BorderLayout.CENTER);

        return panel;
    }

    public MoneyDialog moneyDialog() {
        return moneyDialog;
    }

    public CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    public void setButtonAction(String buttonName, ActionListener action){
        JButton button = commandsButton.get(buttonName);
        if (button != null){
            button.addActionListener(action);
        }
    }

    @Override
    public Object getComponent() {
        return this;
    }

    public MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }
}
