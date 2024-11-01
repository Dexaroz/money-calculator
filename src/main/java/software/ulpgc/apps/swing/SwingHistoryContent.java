package software.ulpgc.apps.swing;

import software.ulpgc.model.ExchangeRecord;
import software.ulpgc.model.ExchangeTransaction;
import software.ulpgc.view.VisualComponent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SwingHistoryContent extends JPanel implements VisualComponent {

    public SwingHistoryContent(ExchangeRecord exchangeRecord) {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(17, 21, 24));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(20,0,0,0));

        JLabel tittleLabel = new JLabel("History");
        tittleLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        tittleLabel.setForeground(Color.WHITE);
        topPanel.add(tittleLabel);
        this.add(topPanel, BorderLayout.NORTH);

        JTable table = getTable(exchangeRecord.showHistory());
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

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setBackground(new Color(17, 21, 24));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private JTable getTable(List<ExchangeTransaction> transactions){
        return new SwingExchangeTable(transactions);
    }

    @Override
    public Object getComponent() {
        return this;
    }
}
