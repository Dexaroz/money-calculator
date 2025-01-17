package software.ulpgc.apps.swing.content;

import software.ulpgc.arquitecture.model.ExchangeTransaction;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class SwingExchangeTable extends JTable {
    public SwingExchangeTable(List<ExchangeTransaction> transactions) {
        super((TableModel) new ExchangeTableModel(transactions));
    }

    private static class ExchangeTableModel extends AbstractTableModel {
        private final List<ExchangeTransaction> transactions;
        private final String[] columnNames = {"From", "To", "Amount", "Date"};

        public ExchangeTableModel(List<ExchangeTransaction> transactions) {
            this.transactions = transactions;
        }

        @Override
        public int getRowCount() {
            return transactions.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            ExchangeTransaction transaction = transactions.get(rowIndex);
            switch (columnIndex) {
                case 0: return transaction.getCurrencyFrom();
                case 1: return transaction.getCurrencyTo();
                case 2: return transaction.getAmount();
                case 3: return transaction.getDate();
                default: return null;
            }
        }
    }
}