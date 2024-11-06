package software.ulpgc.apps.swing;

import software.ulpgc.apps.fixeraw.FixerExchangeRateLoader;
import software.ulpgc.control.AddTransactionCommand;
import software.ulpgc.control.CalculateCommand;
import software.ulpgc.control.Command;
import software.ulpgc.model.Currency;
import software.ulpgc.model.CurrencyFavoriteRecord;
import software.ulpgc.model.ExchangeRecord;
import software.ulpgc.view.ContentPanelManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame implements ContentPanelManager {
    private final Map<String, Command> commands;
    private final List<Currency> currencies;
    private final SwingTopMenuComponent topMenuComponent;
    private final JPanel contentPanel;

    private SwingCurrencyContent currencyContent;
    private SwingHistoryContent historyContent;
    private SwingFavoritiesContent favoritiesContent;

    private ExchangeRecord exchangeRecord = new ExchangeRecord();
    private CurrencyFavoriteRecord currencyFavorite = new CurrencyFavoriteRecord();


    public MainFrame(List<Currency> currencies) throws HeadlessException {
        this.currencies = currencies;
        this.commands = new HashMap<>();
        this.setTitle("MoneyCalculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800,600));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        this.add(getTopMenuLabel(gridBagConstraints, this.topMenuComponent = new SwingTopMenuComponent("Currency",  getClass().getResource("/logo.png"))), gridBagConstraints);
        this.add(getContentPanel(gridBagConstraints, this.contentPanel = new JPanel()), gridBagConstraints);

        this.showContent(getCurrencyContent());
        setUpMenuCommands();
    }

    private Component getTopMenuLabel(GridBagConstraints gridBagConstraints, SwingTopMenuComponent topMenuComponent){
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        return topMenuComponent;
    }

    private Component getContentPanel(GridBagConstraints gridBagConstraints, JPanel contentPanel){
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        contentPanel.setBackground(new Color(17, 21, 24));

        return contentPanel;
    }

    public void put(String key, Command value) {
        commands.put(key, value);
    }

    public void setUpMenuCommands(){
        topMenuComponent.addButton("Converter", () -> showContent(getCurrencyContent()));
        topMenuComponent.addButton("History", () -> showContent(getHistoryContent()));
        topMenuComponent.addButton("Favorities", () -> showContent(getFavoritiesContent()));
    }

    private void setUpCurrencyCommands(){
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

    public SwingCurrencyContent getCurrencyContent(){
        SwingCurrencyContent currencyContent = SwingCurrencyContent.getInstance(currencies);

        if (currencyContent == null){
            this.currencyContent = currencyContent;
            setUpCurrencyCommands();
        }

        return currencyContent;
    }

    public SwingHistoryContent getHistoryContent(){
        return SwingHistoryContent.getInstance(exchangeRecord);
    }

    public SwingFavoritiesContent getFavoritiesContent(){
        return SwingFavoritiesContent.getInstance(currencyFavorite);
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

    @Override
    public void showContent(Object visualComponent){
        contentPanel.removeAll();
        contentPanel.add((JPanel) visualComponent, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public ExchangeRecord getExchangeRecord(){
        return exchangeRecord;
    }

    public CurrencyFavoriteRecord getCurrencyFavorite(){
        return currencyFavorite;
    }
}
