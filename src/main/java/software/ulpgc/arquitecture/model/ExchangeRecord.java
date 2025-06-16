package software.ulpgc.arquitecture.model;

import software.ulpgc.arquitecture.io.currency.DatabaseCurrencyReader;
import software.ulpgc.arquitecture.io.exchange.DatabaseExchangeTransactionReader;
import software.ulpgc.arquitecture.io.exchange.DatabaseExchangeTransactionWriter;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRecord {

    private List<ExchangeTransaction> exchangeTransactions = new ArrayList<>();
    private final File databaseFile;

    public ExchangeRecord(File databaseFile) {
        this.databaseFile = databaseFile;

        try {
            List<Currency> currencies;
            try (DatabaseCurrencyReader currencyReader = new DatabaseCurrencyReader(databaseFile)) {
                currencies = currencyReader.readAll();
            }

            try (DatabaseExchangeTransactionReader transactionReader = new DatabaseExchangeTransactionReader(databaseFile, currencies)) {
                this.exchangeTransactions = transactionReader.readAll();
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load exchange transactions", e);
        }
    }

    public List<ExchangeTransaction> showHistory() {
        return exchangeTransactions;
    }

    public void addExchangeTransaction(ExchangeTransaction exchange) throws SQLException {
        if (!exchangeTransactions.contains(exchange)) {
            exchangeTransactions.add(exchange);

            try (DatabaseExchangeTransactionWriter databaseExchangeTransactionWriter = new DatabaseExchangeTransactionWriter(databaseFile)){
                databaseExchangeTransactionWriter.write(exchange);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

