package software.ulpgc.apps.swing;

import software.ulpgc.model.Money;
import software.ulpgc.view.MoneyDisplay;

import javax.swing.*;


public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {

    @Override
    public void display(Money money) {
        this.setText(money.toString());
    }
}
