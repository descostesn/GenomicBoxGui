package GenomicBox.graphicClasses.formatClasses;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean isFocus, int row, int col) {
        setBackground((Color) value);

        return this;
        }
}
