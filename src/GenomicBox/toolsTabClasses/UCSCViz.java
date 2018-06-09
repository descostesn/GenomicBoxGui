package GenomicBox.toolsTabClasses;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import GenomicBox.graphicClasses.actionClasses.FillFormWithFiles;
import GenomicBox.graphicClasses.formatClasses.ButtonRenderer;
import GenomicBox.graphicClasses.formatClasses.ColorChooserEditor;
import GenomicBox.graphicClasses.modelClasses.CustomTableModel;
import GenomicBox.graphicClasses.paneClasses.LabelAndSelectButtonPane;
import GenomicBox.graphicClasses.paneClasses.OrganismGenomePane;
import GenomicBox.graphicClasses.paneClasses.SeqplotsToolPart1Pane;




public class UCSCViz extends JPanel {
	
	private JFrame frame = new JFrame();
	private LabelAndSelectButtonPane selectBigWigs = new LabelAndSelectButtonPane("BigWigs:");
	private JButton selectBigwigsButton = selectBigWigs.getButton();
	
	private OrganismGenomePane organismGenomePane = new OrganismGenomePane();
	
	private Object rawData[][] = {{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue},
			{"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}, {"", "", Color.blue}};
	private String[] columnNames = {"Bigwig", "Name", "Color"};
	private CustomTableModel model = new CustomTableModel(rawData, columnNames);
	private TableCellEditor editor = new ColorChooserEditor();
    private JTable table = new JTable(model);
    private TableColumn column = table.getColumnModel().getColumn(2);
    private JScrollPane scrollPane = new JScrollPane(table);
	
    private JPanel emptyPanel1 = new JPanel();
	private JPanel emptyPanel2 = new JPanel();
	private JButton ucscSubmit = new JButton("Go to UCSC");
	
	
	public UCSCViz(int preProcWidth, int preProcHeight){
		
		selectBigwigsButton.addActionListener(new FillFormWithFiles(UCSCViz.this, "bw", frame,
				"'.bw' files are expected as input", model));
		
		column.setCellRenderer(new ButtonRenderer());
		column.setCellEditor(editor);
		column.setMaxWidth(preProcWidth/10);
		
		emptyPanel1.setBackground(Color.white);
		emptyPanel2.setBackground(Color.white);
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(selectBigWigs);
		this.add(organismGenomePane);
		this.add(scrollPane);
		this.add(ucscSubmit);
		this.add(emptyPanel1);
		this.add(emptyPanel2);
		this.setVisible(true);
	}

}
