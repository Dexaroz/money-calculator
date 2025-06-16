package software.ulpgc.arquitecture.io.exchange;

import software.ulpgc.arquitecture.model.ExchangeTransaction;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class DatabaseExchangeTransactionWriter implements ExchangeTransactionWriter, AutoCloseable {
    private final Connection connection;
    private final PreparedStatement insertStatement;

    private static final String CreateTableStatement = """
        CREATE TABLE IF NOT EXISTS ExchangeTransactionTable (
            CurrencyFrom TEXT NOT NULL,
            CurrencyTo TEXT NOT NULL,
            Amount REAL NOT NULL,
            Date TEXT NOT NULL
        )
        """;

    private static final String InsertRecordStatement = """
        INSERT INTO ExchangeTransactionTable (CurrencyFrom, CurrencyTo, Amount, Date)
        VALUES (?, ?, ?, ?)
        """;

    public DatabaseExchangeTransactionWriter(File file) throws SQLException {
        this(connectionFor(file));
    }

    public DatabaseExchangeTransactionWriter(String connectionString) throws SQLException {
        this.connection = DriverManager.getConnection(connectionString);
        this.connection.setAutoCommit(false);
        this.insertStatement = initDatabase(this.connection);
    }

    private PreparedStatement initDatabase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(CreateTableStatement);
        return connection.prepareStatement(InsertRecordStatement);
    }

    private static String connectionFor(File file) {
        return "jdbc:sqlite:" + file.getAbsolutePath();
    }

    public void write(ExchangeTransaction transaction) throws IOException {
        try {
            insertStatement.setString(1, transaction.getCurrencyFrom().getCode());
            insertStatement.setString(2, transaction.getCurrencyTo().getCode());
            insertStatement.setDouble(3, transaction.getAmount());
            insertStatement.setString(4, transaction.getDate().toString());

            insertStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new IOException("Error writing exchange transaction", e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
