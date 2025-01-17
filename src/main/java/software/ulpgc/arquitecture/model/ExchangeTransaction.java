package software.ulpgc.arquitecture.model;

import java.time.LocalDate;
import java.util.Objects;

public class ExchangeTransaction {
    private final Currency currencyFrom;
    private final Currency currencyTo;
    private final double amount;
    private final LocalDate date;

    public ExchangeTransaction(Currency currencyFrom, Currency currencyTo, double amount, LocalDate date) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.date = date;
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeTransaction that = (ExchangeTransaction) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(currencyFrom, that.currencyFrom) && Objects.equals(currencyTo, that.currencyTo) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyFrom, currencyTo, amount, date);
    }

    @Override
    public String toString() {
        return  "currencyFrom=" + currencyFrom +
                ", currencyTo=" + currencyTo +
                ", amount=" + amount +
                ", date=" + date;
    }
}
