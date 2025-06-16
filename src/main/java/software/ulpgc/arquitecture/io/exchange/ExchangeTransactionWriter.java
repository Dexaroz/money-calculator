package software.ulpgc.arquitecture.io.exchange;

import software.ulpgc.arquitecture.model.ExchangeTransaction;

import java.io.IOException;

public interface ExchangeTransactionWriter extends AutoCloseable {
    void write(ExchangeTransaction exchangeTransaction) throws IOException;
}
