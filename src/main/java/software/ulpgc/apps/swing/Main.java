package software.ulpgc.apps.swing;

import software.ulpgc.apps.fixeraw.FixerCurrencyLoader;
import software.ulpgc.arquitecture.control.*;
import software.ulpgc.apps.swing.MainFrame;
import software.ulpgc.arquitecture.io.currency.CurrencySynchronizer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        File dbFile = new File("moneyCalculator.db");
        new CurrencySynchronizer().syncWithWeb(dbFile, new FixerCurrencyLoader().loads());
        MainFrame mainFrame = new MainFrame(dbFile);
        mainFrame.put("converter", new ContentCommand(mainFrame, mainFrame.getCurrencyContent()));
        mainFrame.put("history", new ContentCommand(mainFrame, mainFrame.getHistoryContent()));
        mainFrame.put("favorities", new ContentCommand(mainFrame, mainFrame.getFavoritiesContent()));
        mainFrame.setVisible(true);
    }
}
