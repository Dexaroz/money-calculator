package software.ulpgc.apps.swing;

import software.ulpgc.apps.fixeraw.FixerCurrencyLoader;
import software.ulpgc.arquitecture.control.*;
import software.ulpgc.apps.swing.MainFrame;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame(new FixerCurrencyLoader().loads());
        mainFrame.put("converter", new ContentCommand(mainFrame, mainFrame.getCurrencyContent()));
        mainFrame.put("history", new ContentCommand(mainFrame, mainFrame.getHistoryContent()));
        mainFrame.put("favorities", new ContentCommand(mainFrame, mainFrame.getFavoritiesContent()));
        mainFrame.setVisible(true);
    }
}
