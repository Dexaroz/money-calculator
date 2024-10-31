package software.ulpgc.model;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRecord {

    private List<ExchangeRate> history;

    public ExchangeRecord() {
        this.history = new ArrayList<>();
    }

    public List<ExchangeRate> showHistory() {
        return history;
    }

    public void addExchange(ExchangeRate exchange) {
        if (!history.contains(exchange)) {
            history.add(exchange);
        }
    }

    public void removeExchange(ExchangeRate exchange) {
        history.remove(exchange);
    }
}
