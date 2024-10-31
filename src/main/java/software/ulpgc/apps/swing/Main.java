package software.ulpgc.apps.swing;

import software.ulpgc.apps.mock.MockCurrencyLoader;
import software.ulpgc.control.*;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame(new MockCurrencyLoader().loads());
        mainFrame.put("converter", new ContentCommand(mainFrame, new SwingCurrencyContent(mainFrame.getCurrencies())));
        mainFrame.put("history", new ContentCommand(mainFrame, new SwingHistoryContent()));
        mainFrame.put("favorities", new ContentCommand(mainFrame, new SwingFavoritiesContent()));
        mainFrame.setVisible(true);
    }
}
