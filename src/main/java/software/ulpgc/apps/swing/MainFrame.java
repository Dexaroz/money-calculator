package software.ulpgc.apps.swing;

import software.ulpgc.apps.fixeraw.FixerExchangeRateLoader;
import software.ulpgc.control.AddTransactionCommand;
import software.ulpgc.control.CalculateCommand;
import software.ulpgc.control.Command;
import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRecord;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands;
    private final List<Currency> currencies;
    private final SwingTopMenuComponent topMenuComponent;
    private final JPanel contentPanel;

    private SwingCurrencyContent currencyContent;
    private SwingHistoryContent historyContent;
    private SwingFavoritiesContent favoritiesContent;

    private ExchangeRecord exchangeRecord;


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
        contentPanel.setBackground(new Color(17, 21, 24));
        this.add(contentPanel, BorderLayout.CENTER);

        this.showContent(getCurrencyContent());
        setUpMenuCommands();
    }

    public void put(String key, Command value) {
        commands.put(key, value);
    }

    public void setUpMenuCommands(){
        topMenuComponent.getHorizontalMenu().setButtonAction("Converter", e -> showContent(getCurrencyContent()));
        topMenuComponent.getHorizontalMenu().setButtonAction("History", e -> showContent(getHistoryContent()));
        topMenuComponent.getHorizontalMenu().setButtonAction("Favorities", e -> showContent(getFavoritiesContent()));
    }

    public SwingCurrencyContent getCurrencyContent(){
        if (currencyContent == null){
            this.currencyContent = new SwingCurrencyContent(currencies);
            this.exchangeRecord = new ExchangeRecord();
            CalculateCommand calculateCommand = new CalculateCommand(
                    currencyContent.moneyDialog(),
                    currencyContent.currencyDialog(),
                    new FixerExchangeRateLoader(),
                    currencyContent.moneyDisplay()
            );

            AddTransactionCommand addTransactionCommand = new AddTransactionCommand(
                    currencyContent.moneyDialog(),
                    currencyContent.currencyDialog(),
                    exchangeRecord
            );
            commands.put("addTransactionCommand", addTransactionCommand);
            commands.put("calculate", calculateCommand);

            currencyContent.setButtonAction("Calculate", e -> {
                        executeCommand("calculate");
                        executeCommand("addTransactionCommand");
            });
        }

        return currencyContent;
    }

    public SwingHistoryContent getHistoryContent(){
        if (historyContent == null){
            this.historyContent = new SwingHistoryContent(exchangeRecord);
        }

        return historyContent;
    }

    public SwingFavoritiesContent getFavoritiesContent(){
        if (favoritiesContent == null){
            this.favoritiesContent = new SwingFavoritiesContent();
        }

        return favoritiesContent;
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

    public ExchangeRecord getExchangeRecord(){
        return exchangeRecord;
    }
}
