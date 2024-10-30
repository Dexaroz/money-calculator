package software.ulpgc.apps.swing;

import software.ulpgc.apps.mock.MockExchangeRateLoader;
import software.ulpgc.apps.mock.MockMoneyDisplay;
import software.ulpgc.control.CalculateCommand;
import software.ulpgc.control.Command;
import software.ulpgc.model.Currency;
import software.ulpgc.view.VisualComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands;
    private final List<Currency> currencies;
    private SwingTopMenuComponent topMenuComponent;
    private JPanel contentPanel;

    public MainFrame(List<Currency> currencies) throws HeadlessException {
        this.currencies = currencies;
        this.commands = new HashMap<>();
        this.setTitle("MoneyCalculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800,600));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        topMenuComponent = new SwingTopMenuComponent("Currency", getClass().getResource("/logo.png"));
        this.add((JPanel) topMenuComponent.getComponent(), BorderLayout.NORTH);

        this.contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(17, 21, 24));
        this.add(contentPanel, BorderLayout.CENTER);
        this.showContent(setUpCurrency());

        setUpCurrency();
        setUpMenuCommands();
    }

    public void put(String key, Command value) {
        commands.put(key, value);
    }

    public void setUpMenuCommands(){
        topMenuComponent.getHorizontalMenu().setButtonAction("Converter", e -> executeCommand("converter"));
        topMenuComponent.getHorizontalMenu().setButtonAction("History", e -> executeCommand("history"));
        topMenuComponent.getHorizontalMenu().setButtonAction("Favorities", e -> executeCommand("favorities"));
    }

    public SwingCurrencyContent setUpCurrency(){
        SwingCurrencyContent currencyContent = new SwingCurrencyContent(currencies);
        CalculateCommand calculateCommand = new CalculateCommand(
                currencyContent.moneyDialog(),
                currencyContent.currencyDialog(),
                new MockExchangeRateLoader(),
                new MockMoneyDisplay()
        );
        commands.put("calculate", calculateCommand);
        currencyContent.setButtonAction("Calculate", e -> executeCommand("calculate"));
        return currencyContent;
    }

    public void executeCommand(String commandKey){
        Command command = commands.get(commandKey);
        if (command != null){
            command.execute();
        }
    }

    public List<Currency> getCurrencies(){
        return currencies;
    }

    public void showContent(Object visualComponent){
        contentPanel.removeAll();
        contentPanel.add((JPanel) visualComponent, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
