package software.ulpgc.arquitecture.io.currency;

import software.ulpgc.arquitecture.model.Currency;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCurrencyReader implements CurrencyReader {
    private final Connection connection;
    private final PreparedStatement selectStatement;
    private final String CreateTableStatement = """
            CREATE TABLE IF NOT EXISTS Currencies(
                Code TEXT PRIMARY KEY,
                Name TEXT NOT NULL,
                Symbol TEXT,
                Favority BOOLEAN
            )
            """;

    private final static String SelectRecordStatement = """
            SELECT *
            FROM Currencies
            WHERE Code = ?
            """;

    private final static String SelectAllRecordStatement = """
            SELECT *
            FROM Currencies
            """;

    private final static String SelectAllFavoritiesRecordStatement = """
            SELECT *
            FROM Currencies
            WHERE Favority = 1
            """;

    public DatabaseCurrencyReader(File file) throws SQLException {
        this(connectionFor(file));
    }

    public DatabaseCurrencyReader(String connection) throws SQLException {
        this.connection = DriverManager.getConnection(connection);
        this.connection.setAutoCommit(false);
        this.selectStatement = initDatabase(this.connection);
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
    public Currency read(String name) throws IOException {
        try {
            selectStatement.setString(1, name);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()){
                return new Currency(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Symbol"),
                        resultSet.getBoolean("Favority")
                );
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Currency> readAll() throws IOException {
        List<Currency> currencies = new ArrayList<>();

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SelectAllRecordStatement);

            while (resultSet.next()){
                currencies.add(new Currency(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Symbol"),
                        resultSet.getBoolean("Favority")
                ));
            }

            return currencies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Currency> readAllFavorities() throws IOException {
        List<Currency> currencies = new ArrayList<>();

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SelectAllFavoritiesRecordStatement);

            while (resultSet.next()){
                currencies.add(new Currency(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Symbol"),
                        resultSet.getBoolean("Favority")
                ));
            }

            return currencies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
