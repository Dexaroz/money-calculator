package software.ulpgc.apps.mock;

import software.ulpgc.model.Money;
import software.ulpgc.view.MoneyDisplay;

public class MockMoneyDisplay implements MoneyDisplay {
    @Override
    public void display(Money money) {
        System.out.println(money);
    }
}
