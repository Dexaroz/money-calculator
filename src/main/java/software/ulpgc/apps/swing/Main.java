package software.ulpgc.apps.swing;

import software.ulpgc.apps.mock.MockCurrencyLoader;
import software.ulpgc.apps.mock.MockExchangeRateLoader;
import software.ulpgc.apps.mock.MockMoneyDisplay;
import software.ulpgc.control.CalculateCommand;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame(new MockCurrencyLoader().loads());
        mainFrame.put("calculate", new CalculateCommand(
                mainFrame.moneyDialog(),
                mainFrame.currencyDialog(),
                new MockExchangeRateLoader(),
                new MockMoneyDisplay()
        ));
        mainFrame.setVisible(true);
    }
}
