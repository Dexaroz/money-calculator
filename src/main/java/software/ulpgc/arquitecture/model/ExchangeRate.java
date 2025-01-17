package software.ulpgc.arquitecture.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class ExchangeRate {

    private final Currency currencyFrom;
    private final Currency currencyTo;
    private final LocalDateTime date;
    private final double rate;

    public ExchangeRate(Currency currencyFrom, Currency currencyTo, double rate) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.date = LocalDateTime.now();
        this.rate = rate;
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public double getRate() {
        return rate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s | %s", currencyFrom.getCode(), currencyTo.getCode(), date.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExchangeRate that)) return false;
        return Double.compare(rate, that.rate) == 0 && Objects.equals(currencyFrom, that.currencyFrom) && Objects.equals(currencyTo, that.currencyTo) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyFrom, currencyTo, date, rate);
    }
}
