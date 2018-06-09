package GenomicBox.graphicClasses.formatClasses;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;



public class ColorChooserEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton button = new JButton();

    Color savedColor;

    public ColorChooserEditor() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color color = JColorChooser.showDialog(button, "Choose a color", savedColor);
                ColorChooserEditor.this.changeColor(color);
            }
        };
        button.addActionListener(actionListener);
    }

    public Color getCellEditorValue() {
        return savedColor;
    }

    private void changeColor(Color color) {
        if (color != null) {
            savedColor = color;
            button.setBackground(color);
        }
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {
        changeColor((Color) value);
        return button;
    }
}
