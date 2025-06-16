package software.ulpgc.arquitecture.io.exchange;

import software.ulpgc.arquitecture.model.ExchangeTransaction;

import java.io.IOException;

public interface ExchangeTransactionDeleter extends AutoCloseable {
    void delete(ExchangeTransaction exchangeTransaction) throws IOException;
}
