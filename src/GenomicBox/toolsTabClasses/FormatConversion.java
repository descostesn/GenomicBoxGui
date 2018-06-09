package GenomicBox.toolsTabClasses;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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
import GenomicBox.graphicClasses.paneClasses.FileSelectionDisplayAndNamePane;
import GenomicBox.graphicClasses.paneClasses.LabelAndSelectButtonPane;
import GenomicBox.graphicClasses.paneClasses.OrganismGenomePane;
import GenomicBox.graphicClasses.paneClasses.SubmitPane;


public class FormatConversion extends JPanel {
	
	private JFrame frame = new JFrame();
	private Font police2 = new Police2();
	private Font police = new Police();
	
	private LabelAndSelectButtonPane fileSelectPane = new LabelAndSelectButtonPane("Input files:");
	private JButton fileButton = fileSelectPane.getButton();
	private FillFormWithFiles fillListener;
	private Object rowDataFileExp[][] = new EmptyObjectArray(40, 2).getRowData();
	private String[] columnNamesFileExp = {"File", "Exp. Name"};
	private CustomTableModel fileExpNameModel = new CustomTableModel(rowDataFileExp, columnNamesFileExp);
	private JTable fileExpNameTable = new JTable(fileExpNameModel);
	private JScrollPane scrollPaneFile = new JScrollPane(fileExpNameTable);
	
	private OrganismGenomePane organismGenomePane = new OrganismGenomePane();
	private JLabel qualityThresholdLabel = new JLabel("Quality Threshold:");
	private JTextField qualityValueField = new JTextField("25");
	private JPanel qualityPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel binSizeLabel = new JLabel("Bin Size:");
	private JTextField binSizeField = new JTextField("50");
	private JPanel binSizePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel inputFormatLabel = new JLabel("Input Format:");
	private JLabel outputFormatLabel = new JLabel("Output Format");
	private String[] inputFormatComboData = {"BAM", "BED", "BEDGRAPH", "BIGWIG", "GFF", "GTF", "SAM", "WIGFIX", "WIGVARIABLE"};
	private String[] outputFormatComboData = {"BED", "SAM", "WIGFIX"};
	private JComboBox<String> inputFormatCombo = new JComboBox<String>(inputFormatComboData);
	private JComboBox<String> outputFormatCombo = new JComboBox<String>(outputFormatComboData);
	private JPanel formatSelectionPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private SubmitPane submitPanel = new SubmitPane();
	
	public FormatConversion(int preProcWidth, int preProcHeight){
		
		fillListener = new FillFormWithFiles(FormatConversion.this, 
				"bam", frame, "'bam' files are expected as input", fileExpNameModel);
		fileButton.addActionListener(fillListener);
		fileSelectPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, fileSelectPane.getMinimumSize().height));
		
		organismGenomePane.setMaximumSize(new Dimension(Integer.MAX_VALUE, organismGenomePane.getMinimumSize().height));
		organismGenomePane.setVisible(false);
		
		qualityThresholdLabel.setFont(police2);
		qualityThresholdLabel.setBackground(Color.white);
		qualityValueField.setFont(police);
		qualityValueField.setBackground(Color.white);
		qualityValueField.setPreferredSize(new Dimension(preProcWidth/8, preProcHeight/20));
		qualityPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, qualityPane.getMinimumSize().height));
		qualityPane.setBackground(Color.white);
		qualityPane.add(qualityThresholdLabel);
		qualityPane.add(qualityValueField);
		qualityPane.setVisible(false);
		
		binSizeLabel.setFont(police2);
		binSizeLabel.setBackground(Color.white);
		binSizeField.setFont(police);
		binSizeField.setBackground(Color.white);
		binSizeField.setPreferredSize(new Dimension(preProcWidth/8, preProcHeight/20));
		binSizePane.setBackground(Color.white);
		binSizePane.setMaximumSize(new Dimension(Integer.MAX_VALUE, binSizePane.getMinimumSize().height));
		binSizePane.add(binSizeLabel);
		binSizePane.add(binSizeField);
		binSizePane.setVisible(false);
		
		inputFormatLabel.setFont(police2);
		inputFormatLabel.setBackground(Color.white);
		outputFormatLabel.setFont(police2);
		outputFormatLabel.setBackground(Color.white);
		formatSelectionPane.setBackground(Color.white);
		formatSelectionPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, formatSelectionPane.getMinimumSize().height));
		formatSelectionPane.add(inputFormatLabel);
		inputFormatCombo.addItemListener(new FormatListener());
		outputFormatCombo.addItemListener(new FormatListener2());
		formatSelectionPane.add(inputFormatCombo);
		formatSelectionPane.add(outputFormatLabel);
		formatSelectionPane.add(outputFormatCombo);
		
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.white);
		this.add(fileSelectPane);
		this.add(organismGenomePane);
		this.add(qualityPane);
		this.add(binSizePane);
		this.add(formatSelectionPane);
		this.add(scrollPaneFile);
		this.add(submitPanel);
		this.setVisible(true);

	}
	
	
	class FormatListener implements ItemListener{
		
		public void itemStateChanged(ItemEvent event){
			
			String[] replaceFormat;
			
			if(event.getStateChange() == ItemEvent.SELECTED){
				
				if(inputFormatCombo.getSelectedItem() == "BAM"){
					replaceFormat = new String[]{"BED", "SAM", "WIGFIX"};
					
				}else if(inputFormatCombo.getSelectedItem() == "BED"){
					replaceFormat = new String[]{"GFF"};
					
				}else if(inputFormatCombo.getSelectedItem() == "BEDGRAPH"){
					replaceFormat = new String[]{"WIGFIX"};
					
				}else if(inputFormatCombo.getSelectedItem() == "BIGWIG"){
					replaceFormat = new String[]{"WIGFIX"};
					
				}else if(inputFormatCombo.getSelectedItem() == "GFF"){
					replaceFormat = new String[]{"BED", "GTF"};
					
				}else if(inputFormatCombo.getSelectedItem() == "GTF"){
					replaceFormat = new String[]{"GFF"};
					
				}else if(inputFormatCombo.getSelectedItem() == "SAM"){
					replaceFormat = new String[]{"BAM"};
					
				}else if(inputFormatCombo.getSelectedItem() == "WIGFIX"){
					replaceFormat = new String[]{"BED", "BIGWIG"};
					
				}else{// if(inputFormatCombo.getSelectedItem() == "WIGVARIABLE")
					replaceFormat = new String[]{"WIGFIX"};
				}
				
				DefaultComboBoxModel<String> modelChange = new DefaultComboBoxModel<String>(replaceFormat);
				modelChange.removeAllElements();
				
				for(String item : replaceFormat)
					modelChange.addElement(item);
				outputFormatCombo.setModel(modelChange);
				
				switch(inputFormatCombo.getSelectedItem().toString()){
				
				case "BED": fillListener.setFormat("bed"); fillListener.setErrorMessage("'bed' files are expected as input");break;
				case "BEDGRAPH": fillListener.setFormat("bedgraph"); fillListener.setErrorMessage("'bedgraph' files are expected as input");break;
				case "BIGWIG": fillListener.setFormat("bw"); fillListener.setErrorMessage("'bw' files are expected as input");break;
				case "GFF": fillListener.setFormat("gff"); fillListener.setErrorMessage("'gff' files are expected as input");break;
				case "GTF": fillListener.setFormat("gtf"); fillListener.setErrorMessage("'gtf' files are expected as input");break;
				case "SAM": fillListener.setFormat("sam"); fillListener.setErrorMessage("'sam' files are expected as input");break;
				case "WIGFIX": fillListener.setFormat("wig"); fillListener.setErrorMessage("'wig' files are expected as input");break;
				case "WIGVARIABLE": fillListener.setFormat("wig"); fillListener.setErrorMessage("'wig' files are expected as input");break;
				default: fillListener.setFormat("bam"); fillListener.setErrorMessage("'bam' files are expected as input");
				};
				
				if(inputFormatCombo.getSelectedItem() == "BEDGRAPH" && outputFormatCombo.getSelectedItem() == "WIGFIX")
					binSizePane.setVisible(true);
				else if(inputFormatCombo.getSelectedItem() == "WIGVARIABLE" && outputFormatCombo.getSelectedItem() == "WIGFIX"){
					binSizePane.setVisible(true);
					organismGenomePane.setVisible(true);
				}else{
					qualityPane.setVisible(false);
					binSizePane.setVisible(false);
					organismGenomePane.setVisible(false);
				}
				
				fillListener.clearTable();
			}
		}
	}
	
	
	class FormatListener2 implements ItemListener{
		
		public void itemStateChanged(ItemEvent event){
			
			String[] replaceFormat;
			
			if(event.getStateChange() == ItemEvent.SELECTED){
				
				if(inputFormatCombo.getSelectedItem() == "BAM" && outputFormatCombo.getSelectedItem() == "WIGFIX"){
					qualityPane.setVisible(true);
					organismGenomePane.setVisible(true);
				}else if(inputFormatCombo.getSelectedItem() == "WIGFIX" && outputFormatCombo.getSelectedItem() == "BIGWIG")
					organismGenomePane.setVisible(true);
				else{
					qualityPane.setVisible(false);
					binSizePane.setVisible(false);
					organismGenomePane.setVisible(false);
				}
			}
		}
	}
}


