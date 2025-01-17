package software.ulpgc.apps.swing.content;

import software.ulpgc.arquitecture.model.Money;
import software.ulpgc.arquitecture.view.MoneyDisplay;

import javax.swing.*;


public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {

    @Override
    public void display(Money money) {
        this.setText(money.toString());
    }
}
