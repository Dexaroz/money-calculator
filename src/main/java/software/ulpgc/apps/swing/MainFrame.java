package software.ulpgc.apps.swing;

import software.ulpgc.apps.fixeraw.FixerExchangeRateLoader;
import software.ulpgc.apps.swing.content.SwingCurrencyContent;
import software.ulpgc.apps.swing.content.SwingFavoritiesContent;
import software.ulpgc.apps.swing.content.SwingHistoryContent;
import software.ulpgc.apps.swing.topMenu.SwingTopMenuComponent;
import software.ulpgc.arquitecture.control.AddFavoriteCurrencyCommand;
import software.ulpgc.arquitecture.control.AddTransactionCommand;
import software.ulpgc.arquitecture.control.CalculateCommand;
import software.ulpgc.arquitecture.control.Command;
import software.ulpgc.arquitecture.io.currency.DatabaseCurrencyReader;
import software.ulpgc.arquitecture.model.Currency;
import software.ulpgc.arquitecture.model.CurrencyFavoriteRecord;
import software.ulpgc.arquitecture.model.ExchangeRecord;
import software.ulpgc.arquitecture.view.ContentPanelManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainFrame extends JFrame implements ContentPanelManager {
    private static final Color BACKGROUND_COLOR = new Color(17, 21, 24);

    private final Map<String, Command> commands;
    private final List<Currency> currencies;
    private final SwingTopMenuComponent topMenuComponent;
    private final JPanel contentPanel;

    private SwingCurrencyContent currencyContent;
    private SwingHistoryContent historyContent;
    private SwingFavoritiesContent favoritiesContent;

    private final ExchangeRecord exchangeRecord;
    private final CurrencyFavoriteRecord currencyFavorite;
    private final File database;

    public MainFrame(File dbFile) throws HeadlessException, SQLException, IOException {
        this.database = dbFile;
        this.currencies = loadCurrenciesFromDb();
        this.commands = new HashMap<>();
        this.setTitle("MoneyCalculator");

        setIconLogo();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800,600));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        this.currencyFavorite = new CurrencyFavoriteRecord(this.database);
        this.exchangeRecord = new ExchangeRecord(this.database);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        this.add(getTopMenuLabel(gridBagConstraints, this.topMenuComponent = new SwingTopMenuComponent("Currency",  getClass().getResource("/logo.png"))), gridBagConstraints);
        this.add(getContentPanel(gridBagConstraints, this.contentPanel = new JPanel()), gridBagConstraints);

        this.showContent(getCurrencyContent());
        setUpMenuCommands();
    }

    private List<Currency> loadCurrenciesFromDb() {
        try (DatabaseCurrencyReader databaseCurrencyReader = new DatabaseCurrencyReader(this.database)) {
            return databaseCurrencyReader.readAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        contentPanel.setBackground(BACKGROUND_COLOR);

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

    private void setUpCurrencyCommands() throws SQLException {
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

        AddFavoriteCurrencyCommand addFavoriteCurrencyCommand = new AddFavoriteCurrencyCommand(
                currencyContent.currencyDialog(),
                currencyFavorite
        );

        commands.put("calculate", calculateCommand);
        commands.put("addTransactionCommand", addTransactionCommand);
        commands.put("addFavoriteCurrencyCommand", addFavoriteCurrencyCommand);


        currencyContent.setButtonAction("Calculate", e -> {
            try {
                executeCommand("calculate");
                executeCommand("addTransactionCommand");
            } catch (IOException | SQLException ex) {
                throw new RuntimeException(ex);
            }

        });

        currencyContent.setButtonAction("Fav", e -> {
            try {
                executeCommand("addFavoriteCurrencyCommand");
            } catch (IOException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public SwingCurrencyContent getCurrencyContent() throws SQLException {
        if (currencyContent == null) {

            currencyContent = SwingCurrencyContent.getInstance(currencies);
            if (currencyContent != null) {
                setUpCurrencyCommands();
            }
        }

        return currencyContent;
    }

    public SwingHistoryContent getHistoryContent(){
        return SwingHistoryContent.getInstance(exchangeRecord);
    }

    public SwingFavoritiesContent getFavoritiesContent(){
        return SwingFavoritiesContent.getInstance(currencyFavorite);
    }

    public void executeCommand(String commandKey) throws IOException, SQLException {
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

    private void setIconLogo() {
        URL imageURL = getClass().getResource("/logo.png");
        if (imageURL != null) {
            setIconImage(new ImageIcon(imageURL).getImage());
        }
    }
}
