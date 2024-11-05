package software.ulpgc.apps.swing;

import software.ulpgc.model.ExchangeRecord;
import software.ulpgc.model.ExchangeTransaction;
import software.ulpgc.view.VisualComponent;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class SwingHistoryContent extends JPanel implements VisualComponent {

    private final ExchangeRecord exchangeRecord;
    private static SwingHistoryContent instance;

    public static SwingHistoryContent getInstance(ExchangeRecord exchangeRecord){
        return (instance == null) ? instance = new SwingHistoryContent(exchangeRecord) : instance;
    }

    private SwingHistoryContent(ExchangeRecord exchangeRecord) {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(17, 21, 24));
        this.exchangeRecord = exchangeRecord;

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
        JLabel tittleLabel = new JLabel("History");
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

        JScrollPane scrollPane = new JScrollPane(getTable(exchangeRecord.showHistory()));
        scrollPane.setPreferredSize(new Dimension(650,400));
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setBackground(new Color(17, 21, 24));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        return scrollPane;
    }

    private JTable getTable(List<ExchangeTransaction> transactions){
        JTable table = new SwingExchangeTable(transactions);
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
