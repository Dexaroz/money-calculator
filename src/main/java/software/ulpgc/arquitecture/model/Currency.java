package software.ulpgc.arquitecture.model;

import java.util.Objects;

public class Currency {

    private final String name;
    private final String code;
    private final String symbol;
    private final boolean favority;

    public Currency(String code, String name, String symbol, boolean favority) {
        this.name = name;
        this.code = code;
        this.symbol = symbol;
        this.favority = favority;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean getFavority() {
        return favority;
    }

    public Currency setFavority(){
        return new Currency(this.code, this.name, this.symbol, true);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", code, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency currency)) return false;
        return Objects.equals(name, currency.name) && Objects.equals(code, currency.code) && Objects.equals(symbol, currency.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, symbol);
    }
}
