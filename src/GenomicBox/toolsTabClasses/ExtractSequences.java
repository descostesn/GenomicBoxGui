package GenomicBox.toolsTabClasses;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import GenomicBox.graphicClasses.modelClasses.CustomTableModel;
import GenomicBox.graphicClasses.paneClasses.EmptyObjectArray;
import GenomicBox.graphicClasses.paneClasses.OrganismGenomePane;
import GenomicBox.graphicClasses.paneClasses.SeqplotsToolPart1Pane;
import GenomicBox.graphicClasses.paneClasses.SubmitPane;



public class ExtractSequences extends JPanel {

	private SeqplotsToolPart1Pane selectionPane;
	private Object rowData[][] = new EmptyObjectArray(40, 2).getRowData();
	private String[] columnNames = {"GFF file", "Name"};
	private CustomTableModel modelGFF = new CustomTableModel(rowData, columnNames);
    private JTable tableGFF = new JTable(modelGFF);
    private JScrollPane scrollPane = new JScrollPane(tableGFF);
    private SubmitPane submitPanel = new SubmitPane();
	
	public ExtractSequences(int preProcWidth, int preProcHeight){
		
		selectionPane = new SeqplotsToolPart1Pane(preProcWidth, preProcHeight, "ExtractSequences", 
				modelGFF, "gff", false, "GFF coordinates");
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(selectionPane);
		this.add(scrollPane);
		this.add(submitPanel);
		this.setVisible(true);
	}
}
