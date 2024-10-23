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

    public boolean addExchange(ExchangeRate exchange) {
        if (history.contains(exchange)) {
            return false;
        }

        return history.add(exchange);
    }

    public boolean removeExchange(ExchangeRate exchange) {
        return history.remove(exchange);
    }
}
