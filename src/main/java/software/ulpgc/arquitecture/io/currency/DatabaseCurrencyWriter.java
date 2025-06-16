package software.ulpgc.arquitecture.io.currency;

import software.ulpgc.arquitecture.model.Currency;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DatabaseCurrencyWriter implements CurrencyWriter, CurrencyDeleter {

    private final Connection connection;
    private final PreparedStatement insertStatement;
    private final PreparedStatement deleteStatement;
    private final String CreateTableStatement = """
            CREATE TABLE IF NOT EXISTS Currencies(
                Code TEXT PRIMARY KEY,
                Name TEXT NOT NULL,
                Symbol TEXT,
                Favority BOOLEAN
            )
            """;

    private final static String InsertRecordStatement = """
            INSERT INTO Currencies (Code, Name, Symbol, Favority)
            VALUES (?, ?, ?, ?)
            """;

    private final static String DeleteRecordStatement = """
            DELETE FROM Currencies
            WHERE Code = ?
            """;

    public DatabaseCurrencyWriter(File file) throws SQLException {
        this(connectionFor(file));
    }

    public DatabaseCurrencyWriter(String connection) throws SQLException {
        this.connection = DriverManager.getConnection(connection);
        this.connection.setAutoCommit(false);
        this.insertStatement = initDatabase(this.connection);
        this.deleteStatement = this.connection.prepareStatement(DeleteRecordStatement);
    }

    private PreparedStatement initDatabase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(CreateTableStatement);
        return connection.prepareStatement(InsertRecordStatement);
    }

    private static String connectionFor(File file) {
        return "jdbc:sqlite:" + file.getAbsolutePath();
    }

    @Override
    public void write(Currency currency) throws IOException {
        try {
            updateInsertStatementWith(currency);
            insertStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public void delete(Currency currency) throws IOException {
        try {
            deleteStatement.setString(1, currency.getCode());
            deleteStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void updateInsertStatementWith(Currency currency) throws SQLException {
        for (Parameter parameter : toParameter(currency)) {
            updateInsertStatementWith(parameter);
        }
    }

    private List<Parameter> toParameter(Currency currency) {
        return List.of(
                new Parameter(1, currency.getCode(), Types.LONGNVARCHAR),
                new Parameter(2, currency.getName(), Types.LONGNVARCHAR),
                new Parameter(3, currency.getSymbol(), Types.LONGNVARCHAR),
                new Parameter(4, currency.getFavority(), Types.BOOLEAN)
        );
    }

    private void  updateInsertStatementWith(Parameter parameter) throws  SQLException{
        if (isNull(parameter.value))
            insertStatement.setNull(parameter.id, parameter.type);
        else
            insertStatement.setObject(parameter.id, parameter.value);
    }

    private boolean isNull(Object value){
        return value instanceof Integer && (Integer) value == -1;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }

    private record Parameter(int id, Object value, int type){}
}
