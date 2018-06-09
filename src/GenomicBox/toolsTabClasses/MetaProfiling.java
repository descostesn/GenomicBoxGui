package GenomicBox.toolsTabClasses;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import GenomicBox.graphicClasses.formatClasses.ButtonRenderer;
import GenomicBox.graphicClasses.formatClasses.ColorChooserEditor;
import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;
import GenomicBox.graphicClasses.modelClasses.CustomTableModel;
import GenomicBox.graphicClasses.paneClasses.SeqplotsToolPart1Pane;
import GenomicBox.graphicClasses.paneClasses.SeqplotsToolPart2Pane;
import GenomicBox.graphicClasses.paneClasses.SubmitPane;


//class defining the meta-profiling interface which can be opened from the tools menu

public class MetaProfiling extends JPanel {

	private JFrame frame;
	private Font police = new Police();
	private Font police2 = new Police2();
	private SeqplotsToolPart1Pane seqplotsToolPart1Pane;
	private SeqplotsToolPart2Pane seqplotsToolPart2Pane;
	private Object rowData[][] = {{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}};
	private String[] columnNames = {"Bigwig", "Name", "Color"};
	private CustomTableModel model = new CustomTableModel(rowData, columnNames);
	private TableCellEditor editor = new ColorChooserEditor();
    private JTable table = new JTable(model);
    private TableColumn column = table.getColumnModel().getColumn(2);
    private JScrollPane scrollPane = new JScrollPane(table);
	
    
    
	public MetaProfiling(int preProcWidth, int preProcHeight){
		
		seqplotsToolPart1Pane = new SeqplotsToolPart1Pane(preProcWidth, preProcHeight, "MetaProfiling", model, "bw", true, "Bigwigs");
		seqplotsToolPart2Pane = new SeqplotsToolPart2Pane(preProcWidth, preProcHeight, true);
		
	    column.setCellRenderer(new ButtonRenderer());
	    column.setCellEditor(editor);
	    column.setMaxWidth(preProcWidth/10);
		
		SubmitPane submitPane = new SubmitPane();
		submitPane.getButton().addActionListener(new SubmitProfiles());
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(seqplotsToolPart1Pane);
		this.add(seqplotsToolPart2Pane);
		this.add(scrollPane);
		this.add(submitPane);
		this.setVisible(true);
	}
	
	 
/*	class ColorChooserEditor extends AbstractCellEditor implements TableCellEditor {

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
	}*/
 
/*	class ButtonRenderer extends JButton implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean isFocus, int row, int col) {
            setBackground((Color) value);

            return this;
            }
	}*/
	
	
	class SubmitProfiles implements ActionListener{
		
		private String currentLabel;
		private Color currentColor;
		private String currentColorName;
		
		public void actionPerformed(ActionEvent e){
			
			for (int i = 0; i < model.getColumnCount(); i++) {
				
				currentLabel = (String) model.getValueAt(i, 1);
				currentColor = (Color) model.getValueAt(i, 2);
				
				if(!currentLabel.equals("")){
					currentColorName = currentColor.toString();
					System.out.println(currentColorName);
				}
			}
		}
	}
}




