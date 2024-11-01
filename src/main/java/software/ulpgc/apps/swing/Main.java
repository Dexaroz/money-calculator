package software.ulpgc.apps.swing;

import software.ulpgc.control.*;
import software.ulpgc.io.FileCurrencyLoader;
import software.ulpgc.io.TsvCurrencyDeserializer;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame(new FileCurrencyLoader(new File("src/main/resources/currencies.tsv"), new TsvCurrencyDeserializer()).loads());
        mainFrame.put("converter", new ContentCommand(mainFrame, mainFrame.getCurrencyContent()));
        mainFrame.put("history", new ContentCommand(mainFrame, mainFrame.getHistoryContent()));
        mainFrame.put("favorities", new ContentCommand(mainFrame, mainFrame.getFavoritiesContent()));
        mainFrame.setVisible(true);
    }
}
