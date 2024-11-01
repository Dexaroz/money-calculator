package software.ulpgc.apps.swing;

import software.ulpgc.model.ExchangeRecord;
import software.ulpgc.model.ExchangeTransaction;
import software.ulpgc.view.VisualComponent;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class SwingHistoryContent extends JPanel implements VisualComponent {

    public SwingHistoryContent(ExchangeRecord exchangeRecord) {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(17, 21, 24));

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
        this.add(table, BorderLayout.CENTER);
    }

    private JTable getTable(List<ExchangeTransaction> transactions){
        return new SwingExchangeTable(transactions);
    }

    @Override
    public Object getComponent() {
        return this;
    }
}
