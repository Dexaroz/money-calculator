package software.ulpgc.model;

import java.util.HashSet;
import java.util.Set;

public class Country {

    private final String name;
    private final String flag;
    private Set<Currency> currencyList;

    public Country(String name, String flag) {
        this.name = name;
        this.flag = flag;
        this.currencyList = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getflag() {
        return flag;
    }

    public Set<Currency> getCurrencyList() {
        return currencyList;
    }

    public boolean addCurrencyList(Currency currency) {
        return currencyList.add(currency);
    }

    public boolean removeCurrencyList(Currency currency) {
        return currencyList.remove(currency);
    }

    @Override
    public String toString() {
        return String.format("%s %s", flag, name);
    }
}
