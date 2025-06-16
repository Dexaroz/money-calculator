package software.ulpgc.arquitecture.io.exchange;

import software.ulpgc.arquitecture.model.ExchangeTransaction;

import java.io.IOException;
import java.util.List;

public interface ExchangeTransactionReader extends AutoCloseable {
    ExchangeTransaction read(ExchangeTransaction exchange) throws IOException;
    List<ExchangeTransaction> readAll() throws IOException;
}
