package software.ulpgc.apps.swing;

import software.ulpgc.model.Currency;
import software.ulpgc.model.CurrencyFavoriteRecord;
import software.ulpgc.model.ExchangeTransaction;
import software.ulpgc.view.VisualComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SwingFavoritiesContent extends JPanel implements VisualComponent {

    private static final Color BACKGROUND_COLOR = new Color(17, 21, 24);

    private final CurrencyFavoriteRecord currencyFavorites;
    private static SwingFavoritiesContent instance;

    public static SwingFavoritiesContent getInstance(CurrencyFavoriteRecord currencyFavorites){
        return (instance == null) ? instance = new SwingFavoritiesContent(currencyFavorites) : instance;
    }

    private SwingFavoritiesContent(CurrencyFavoriteRecord currencyFavorites) {
        this.setLayout(new GridBagLayout());
        this.setBackground(BACKGROUND_COLOR);
        this.currencyFavorites = currencyFavorites;

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        this.add(getTitlePanel(gridBagConstraints), gridBagConstraints);
        this.add(getTablePanel(gridBagConstraints), gridBagConstraints);
    }

    private Component getTitlePanel(GridBagConstraints gridBagConstraints){
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(5,0,40,0);
        JLabel tittleLabel = new JLabel("Favorities");
        tittleLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        tittleLabel.setForeground(Color.WHITE);

        return tittleLabel;
    }

    private Component getTablePanel(GridBagConstraints gridBagConstraints){
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);

        JScrollPane scrollPane = new JScrollPane(getTable(currencyFavorites.showFavorite()));
        scrollPane.setPreferredSize(new Dimension(650,400));
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        return scrollPane;
    }

    private JTable getTable(List<Currency> currenciesFavorites){
        JTable table = new SwingFavoritesTable(currenciesFavorites);
        table.setBackground(new Color(30, 30, 30));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(60, 60, 60));
        table.setSelectionBackground(new Color(45, 45, 45));
        table.setSelectionForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(40, 40, 40));
        table.getTableHeader().setForeground(Color.WHITE);

        return table;
    }

    @Override
    public Object getComponent() {
        return this;
    }
}

