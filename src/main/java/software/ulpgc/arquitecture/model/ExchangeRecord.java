package software.ulpgc.arquitecture.model;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRecord {

    private final List<ExchangeTransaction> history;

    public ExchangeRecord() {
        this.history = new ArrayList<>();
    }

    public List<ExchangeTransaction> showHistory() {
        return history;
    }

    public void addExchangeTransaction(ExchangeTransaction exchange) {
        if (!history.contains(exchange)) {
            history.add(exchange);
        }
    }
}
