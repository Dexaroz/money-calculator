package software.ulpgc.arquitecture.io.exchange;

import software.ulpgc.arquitecture.io.currency.CurrencyReader;
import software.ulpgc.arquitecture.model.Currency;
import software.ulpgc.arquitecture.model.ExchangeTransaction;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseExchangeTransactionReader implements ExchangeTransactionReader {
    private final Connection connection;
    private final PreparedStatement selectStatement;
    private final List<Currency> currencies;

    private static final String CreateTableStatement = """
        CREATE TABLE IF NOT EXISTS ExchangeTransactionTable (
            CurrencyFrom TEXT NOT NULL,
            CurrencyTo TEXT NOT NULL,
            Amount REAL NOT NULL,
            Date TEXT NOT NULL
        )
        """;

    private static final String SelectRecordStatement = """
        SELECT * FROM ExchangeTransactionTable
        WHERE CurrencyFrom = ? AND CurrencyTo = ? AND Amount = ? AND Date = ?
        """;

    private static final String SelectAllRecordStatement = """
        SELECT * FROM ExchangeTransactionTable
        """;

    public DatabaseExchangeTransactionReader(File file, List<Currency> currencies) throws SQLException {
        this(connectionFor(file), currencies);
    }

    public DatabaseExchangeTransactionReader(String connectionString, List<Currency> currencies) throws SQLException {
        this.connection = DriverManager.getConnection(connectionString);
        this.connection.setAutoCommit(false);
        this.selectStatement = initDatabase(this.connection);
        this.currencies = currencies;
    }

    private PreparedStatement initDatabase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(CreateTableStatement);
        return connection.prepareStatement(SelectRecordStatement);
    }

    private static String connectionFor(File file) {
        return "jdbc:sqlite:" + file.getAbsolutePath();
    }

    @Override
    public ExchangeTransaction read(ExchangeTransaction exchange) throws IOException {
        try {
            selectStatement.setString(1, exchange.getCurrencyFrom().getCode());
            selectStatement.setString(2, exchange.getCurrencyTo().getCode());
            selectStatement.setDouble(3, exchange.getAmount());
            selectStatement.setString(4, exchange.getDate().toString());

            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                Currency currencyFrom = findCurrencyByCode(rs.getString("CurrencyFrom"));
                Currency currencyTo = findCurrencyByCode(rs.getString("CurrencyTo"));

                return new ExchangeTransaction(
                        currencyFrom,
                        currencyTo,
                        rs.getDouble("Amount"),
                        LocalDate.parse(rs.getString("Date"))
                );
            }
            return null;
        } catch (SQLException e) {
            throw new IOException("Error reading exchange transaction", e);
        }
    }

    @Override
    public List<ExchangeTransaction> readAll() throws IOException {
        List<ExchangeTransaction> transactions = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SelectAllRecordStatement);
            while (rs.next()) {
                Currency currencyFrom = findCurrencyByCode(rs.getString("CurrencyFrom"));
                Currency currencyTo = findCurrencyByCode(rs.getString("CurrencyTo"));

                transactions.add(new ExchangeTransaction(
                        currencyFrom,
                        currencyTo,
                        rs.getDouble("Amount"),
                        LocalDate.parse(rs.getString("Date"))
                ));
            }
            return transactions;
        } catch (SQLException e) {
            throw new IOException("Error reading all exchange transactions", e);
        }
    }

    private Currency findCurrencyByCode(String code) {
        return currencies.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Currency not found for code: " + code));
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}