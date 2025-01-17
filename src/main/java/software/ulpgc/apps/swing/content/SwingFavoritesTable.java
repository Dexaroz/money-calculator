package software.ulpgc.apps.swing.content;

import software.ulpgc.arquitecture.model.Currency;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class SwingFavoritesTable extends JTable {
    public SwingFavoritesTable(List<Currency> currencyFavorites) {
        super((TableModel) new ExchangeTableModel(currencyFavorites) );
    }

    private static class ExchangeTableModel extends AbstractTableModel {
        private final List<Currency> currencyFavorites;
        private final String[] columnNames = {"Name", "Code", "Symbol"};

        public ExchangeTableModel(List<Currency> currencyFavorites) {
            this.currencyFavorites = currencyFavorites;
        }

        @Override
        public int getRowCount() {
            return currencyFavorites.size();
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
            Currency currency = currencyFavorites.get(rowIndex);
            switch (columnIndex) {
                case 0: return currency.getName();
                case 1: return currency.getCode();
                case 2: return currency.getSymbol();
                default: return null;
            }
        }
    }
}