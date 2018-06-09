package GenomicBox.toolsTabClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import GenomicBox.graphicClasses.actionClasses.FillFormWithFiles;
import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;
import GenomicBox.graphicClasses.modelClasses.CustomTableModel;
import GenomicBox.graphicClasses.paneClasses.EmptyObjectArray;
import GenomicBox.graphicClasses.paneClasses.JRadioButtonAndShowPanelPane;
import GenomicBox.graphicClasses.paneClasses.EndTypePane;
import GenomicBox.graphicClasses.paneClasses.FormatChooserPane;
import GenomicBox.graphicClasses.paneClasses.FileSelectionDisplayAndNamePane;
import GenomicBox.graphicClasses.paneClasses.LabelAndSelectButtonPane;
import GenomicBox.graphicClasses.paneClasses.OutputFolderChooserPane;
import GenomicBox.graphicClasses.paneClasses.SubmitPane;



public class DEAnalysis extends JPanel{

	private JFrame frame;
	private Font police = new Police();
	private Font police2 = new Police2();
	private JPanel edgeROptionsPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel speciesLabel = new JLabel("Species: ");
	private String[] speciesComBoData = {"mouse", "human"};
	private JComboBox<String> speciesCombo = new JComboBox<String>(speciesComBoData);
	private JRadioButtonAndShowPanelPane toolChoicePane;
	private LabelAndSelectButtonPane bamSelectPane = new LabelAndSelectButtonPane("BAM:");
	public JButton bamButton = bamSelectPane.getButton();
	private Object rowDataBamExp[][] = new EmptyObjectArray(40, 2).getRowData();
	private String[] columnNamesBamExp = {"BAM", "Exp. Name"};
	private CustomTableModel bamFileExpNameModel = new CustomTableModel(rowDataBamExp, columnNamesBamExp);
	private JTable bamFileExpNameTable = new JTable(bamFileExpNameModel);
	private JScrollPane scrollPaneBam = new JScrollPane(bamFileExpNameTable);
	private EndTypePane singleOrPairedPane = new EndTypePane();
	private FileSelectionDisplayAndNamePane GFFChooserPane;
	private FileSelectionDisplayAndNamePane GTFChooserPane;
	private JPanel formatAndCompNamePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private FormatChooserPane formatChooserPane = new FormatChooserPane();
	private JLabel compNameLabel = new JLabel("Comparison name:");
	private JTextField compNameField = new JTextField("");
	private Object rowDataSample[][] = new EmptyObjectArray(40, 5).getRowData();
	private String[] columnNamesSample = {"Sample Name", "Cell", "Condition", "Reads length", "Replicate Number"};
	private CustomTableModel sampleModel = new CustomTableModel(rowDataSample, columnNamesSample);
	private JTable sampleTable = new JTable(sampleModel);
	private JScrollPane scrollPaneSample = new JScrollPane(sampleTable);
	private String[] conditionComboData = {"Ctrl", "Experimental"};
	private Integer[] replicateNumberComboData = new Integer[20];
	private JComboBox<String> conditionCombo = new JComboBox<String>(conditionComboData);
	private JComboBox<Integer> replicateNumberCombo;
	private OutputFolderChooserPane outputFolderChooser;
	private SubmitPane submitPane = new SubmitPane();
	
	
	public DEAnalysis(int preProcWidth, int preProcHeight){
		
		GTFChooserPane = new FileSelectionDisplayAndNamePane(preProcWidth, preProcHeight, "GTF annotations:", "GTF name:", DEAnalysis.this, frame, "GTF", "gtf");
		
		speciesLabel.setBackground(Color.white);
		speciesLabel.setFont(police2);
		edgeROptionsPane.setBackground(Color.white);
		edgeROptionsPane.add(GTFChooserPane);
		edgeROptionsPane.add(speciesLabel);
		edgeROptionsPane.add(speciesCombo);
		edgeROptionsPane.setVisible(false);
		
		toolChoicePane = new JRadioButtonAndShowPanelPane("edgeR", "DESeq2", edgeROptionsPane);
		
		bamButton.addActionListener(new FillFormWithFiles(DEAnalysis.this, "bam", frame,
				"DESeq2 expects '.bam' files as input", bamFileExpNameModel, sampleModel));
		
		GFFChooserPane = new FileSelectionDisplayAndNamePane(preProcWidth, preProcHeight, "GFF annotations:", "GFF name:", DEAnalysis.this, frame, "GFF", "gff");
		
		formatAndCompNamePane.setBackground(Color.white);
		compNameLabel.setFont(police2);
		compNameField.setBackground(Color.white);
		compNameField.setFont(police);
		compNameField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		compNameField.setBorder(BorderFactory.createLineBorder(Color.black));
		formatAndCompNamePane.add(formatChooserPane);
		formatAndCompNamePane.add(compNameLabel);
		formatAndCompNamePane.add(compNameField);
		
		int inc = 1;
		for (int i = 0; i < replicateNumberComboData.length; ++i) {
			replicateNumberComboData[i] = inc;
			inc++;
		}
		replicateNumberCombo = new JComboBox<Integer>(replicateNumberComboData);
		sampleTable.getColumn("Condition").setCellEditor(new DefaultCellEditor(conditionCombo));
		sampleTable.getColumn("Replicate Number").setCellEditor(new DefaultCellEditor(replicateNumberCombo));
		
		outputFolderChooser = new OutputFolderChooserPane(DEAnalysis.this, frame, preProcWidth, preProcHeight);
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.white);
		this.add(toolChoicePane);
		this.add(bamSelectPane);
		this.add(scrollPaneBam);
		this.add(singleOrPairedPane);
		this.add(GFFChooserPane);
		this.add(edgeROptionsPane);
		this.add(formatAndCompNamePane);
		this.add(scrollPaneSample);
		this.add(outputFolderChooser);
		this.add(submitPane);
		this.setVisible(true);
	}	
	
}
