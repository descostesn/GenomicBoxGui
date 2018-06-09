package GenomicBox.toolsTabClasses;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import GenomicBox.graphicClasses.actionClasses.FillComboFormWithFiles;
import GenomicBox.graphicClasses.actionClasses.FillFormWithFiles;
import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;
import GenomicBox.graphicClasses.modelClasses.CustomTableModel;
import GenomicBox.graphicClasses.paneClasses.EmptyObjectArray;
import GenomicBox.graphicClasses.paneClasses.LabelAndSelectButtonPane;
import GenomicBox.graphicClasses.paneClasses.OrganismGenomePane;
import GenomicBox.graphicClasses.paneClasses.OutputFolderChooserPane;
import GenomicBox.graphicClasses.paneClasses.SubmitPane;




public class PeakDetection extends JPanel {

	private Font police = new Police();
	private Font police2 = new Police2();
	private JFrame frame = new JFrame();
	
	private LabelAndSelectButtonPane bamSelectPane = new LabelAndSelectButtonPane("BAM:");
	private LabelAndSelectButtonPane controlBamSelectPane = new LabelAndSelectButtonPane("Control:");
	private JPanel bamSelectionPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JButton bamButton = bamSelectPane.getButton();
	private JButton bamControlButton = controlBamSelectPane.getButton();
	private FillFormWithFiles fillBamTable;
	
	private LabelAndSelectButtonPane bedSelectPane = new LabelAndSelectButtonPane("BED:");
	private LabelAndSelectButtonPane controlBedSelectPane = new LabelAndSelectButtonPane("Control:");
	private JPanel bedSelectionPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JButton bedButton = bedSelectPane.getButton();
	private JButton bedControlButton = controlBedSelectPane.getButton();
	private FillFormWithFiles fillBedTable;
	
	private JCheckBox macs2Checkbox = new JCheckBox("MACS2");
	private JCheckBox hiddenDomainsCheckbox = new JCheckBox("hiddenDomains");
	private JCheckBox sicerCheckbox = new JCheckBox("SICER");
	private JCheckBox sppCheckbox = new JCheckBox("SPP");
	private JPanel toolSelectionPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	private OrganismGenomePane organismGenomePane = new OrganismGenomePane();
	
	private JLabel qvalueLabel = new JLabel("Q-value:");
	private JTextField qvalueField = new JTextField("");
	private JPanel qvaluePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel fdrLabel = new JLabel("FDR:");
	private JTextField fdrField = new JTextField("");
	private JPanel fdrPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel zscoreLabel = new JLabel("Z-score:");
	private JTextField zscoreField = new JTextField("");
	private JPanel zscorePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel thresholdsPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	private OutputFolderChooserPane outputFolderPane;
	
	private JLabel bamTableLabel = new JLabel("MACS2/SPP/hiddenDomains:");
	private JPanel bamTableLabelPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private Object rowDataBamTable[][] = new EmptyObjectArray(40, 6).getRowData();
	private String[] columnNamesBam = {"Experiment", "Experiment Name", "Control Experiment", "Tag-size", "Duplicate Threshold", "Elongation size"};
	private CustomTableModel bamFileTableModel = new CustomTableModel(rowDataBamTable, columnNamesBam);
	private JTable bamFileTable = new JTable(bamFileTableModel);
	private JScrollPane scrollPaneBam = new JScrollPane(bamFileTable);
	private JComboBox<String> bamControlCombo;
	
	private JLabel bedTableLabel = new JLabel("Sicer:");
	private JPanel bedTableLabelPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private Object rowDataBedTable[][] = new EmptyObjectArray(40, 7).getRowData();
	private String[] columnNamesBed = {"Experiment", "Experiment Name", "Control Experiment", "Tag-size", "Duplicate Threshold", "Gap size", "Window Size"};
	private CustomTableModel bedFileTableModel = new CustomTableModel(rowDataBedTable, columnNamesBed);
	private JTable bedFileTable = new JTable(bedFileTableModel);
	private JScrollPane scrollPaneBed = new JScrollPane(bedFileTable);
	private JComboBox<String> bedControlCombo;
	
	private SubmitPane submitPanel = new SubmitPane();
	
	
	
	public PeakDetection(int preProcWidth, int preProcHeight){
		
		bamSelectionPane.setBackground(Color.white);
		bamSelectionPane.add(bamSelectPane);
		bamSelectionPane.add(controlBamSelectPane);
		fillBamTable = new FillFormWithFiles(PeakDetection.this, "bam", frame,
				"'.bam' files are expected as input", bamFileTableModel);
		bamButton.addActionListener(fillBamTable);
		//bamControlButton.addActionListener(new FillComboFormWithFiles(PeakDetection.this, "bam", frame, "'.bam' files are expected as input"));
		bamControlButton.addActionListener(new FillComboFormWithFiles(PeakDetection.this, "bam", frame, "'.bam' files are expected as input", "Control Experiment", 
			bamControlCombo, bamFileTable));
		
		bedSelectionPane.setBackground(Color.white);
		bedSelectionPane.add(bedSelectPane);
		bedSelectionPane.add(controlBedSelectPane);
		bedSelectionPane.setVisible(false);
		fillBedTable = new FillFormWithFiles(PeakDetection.this, "bed", frame, 
				"'.bed' files are expected as input", bedFileTableModel);
		bedButton.addActionListener(fillBedTable);
		//bedControlButton.addActionListener(new FillComboFormWithFiles(PeakDetection.this, "bed", frame, "'.bed' files are expected as input"));
		bedControlButton.addActionListener(new FillComboFormWithFiles(PeakDetection.this, "bed", frame, "'.bed' files are expected as input", "Control Experiment", 
				bedControlCombo, bedFileTable));
		
		macs2Checkbox.setFont(police);
		macs2Checkbox.setBackground(Color.white);
		macs2Checkbox.setSelected(true);
		macs2Checkbox.addActionListener(new DisplayPane());
		hiddenDomainsCheckbox.setFont(police);
		hiddenDomainsCheckbox.setBackground(Color.white);
		hiddenDomainsCheckbox.addActionListener(new DisplayPane());
		sicerCheckbox.setFont(police);
		sicerCheckbox.setBackground(Color.white);
		sicerCheckbox.addActionListener(new DisplayPane());
		sppCheckbox.setFont(police);
		sppCheckbox.setBackground(Color.white);
		sppCheckbox.addActionListener(new DisplayPane());
		toolSelectionPane.setBackground(Color.white);
		toolSelectionPane.add(macs2Checkbox);
		toolSelectionPane.add(hiddenDomainsCheckbox);
		toolSelectionPane.add(sicerCheckbox);
		toolSelectionPane.add(sppCheckbox);
		
		qvalueLabel.setFont(police2);
		qvalueLabel.setBackground(Color.white);
		qvalueField.setFont(police);
		qvalueField.setBackground(Color.white);
		qvalueField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		fdrLabel.setFont(police2);
		fdrLabel.setBackground(Color.white);
		fdrField.setFont(police);
		fdrField.setBackground(Color.white);
		fdrField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		zscoreLabel.setFont(police2);
		zscoreLabel.setBackground(Color.white);
		zscoreField.setFont(police);
		zscoreField.setBackground(Color.white);
		zscoreField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		qvaluePane.setBackground(Color.white);
		fdrPane.setBackground(Color.white);
		zscorePane.setBackground(Color.white);
		qvaluePane.add(qvalueLabel);
		qvaluePane.add(qvalueField);
		fdrPane.add(fdrLabel);
		fdrPane.add(fdrField);
		fdrPane.setVisible(false);
		zscorePane.add(zscoreLabel);
		zscorePane.add(zscoreField);
		zscorePane.setVisible(false);
		thresholdsPane.setBackground(Color.white);
		thresholdsPane.add(qvaluePane);
		thresholdsPane.add(fdrPane);
		thresholdsPane.add(zscorePane);
		
		outputFolderPane = new OutputFolderChooserPane(PeakDetection.this, frame, preProcWidth, preProcHeight);
		
		bamTableLabel.setFont(police2);
		bamTableLabel.setBackground(Color.white);
		bamTableLabelPane.setBackground(Color.white);
		bamTableLabelPane.add(bamTableLabel);
		
		bedTableLabel.setFont(police2);
		bedTableLabel.setBackground(Color.white);
		bedTableLabelPane.setBackground(Color.white);
		bedTableLabelPane.add(bedTableLabel);
		bedTableLabelPane.setVisible(false);
		scrollPaneBed.setVisible(false);
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(bamSelectionPane);
		this.add(bedSelectionPane);
		this.add(toolSelectionPane);
		this.add(organismGenomePane);
		this.add(thresholdsPane);
		this.add(outputFolderPane);
		this.add(bamTableLabelPane);
		this.add(scrollPaneBam);
		this.add(bedTableLabelPane);
		this.add(scrollPaneBed);
		this.add(submitPanel);
		this.setVisible(true);
	}
	
	
	
	class DisplayPane implements ActionListener {
		
		
		public void actionPerformed(ActionEvent e){
			
			if(macs2Checkbox.isSelected() || sppCheckbox.isSelected() || hiddenDomainsCheckbox.isSelected()){
				bamSelectionPane.setVisible(true);
				bamTableLabelPane.setVisible(true);
				scrollPaneBam.setVisible(true);
			}
			else{
				bamSelectionPane.setVisible(false);
				bamTableLabelPane.setVisible(false);
				scrollPaneBam.setVisible(false);
			}
			
			
			if(sicerCheckbox.isSelected()){
				bedSelectionPane.setVisible(true);
				bedTableLabelPane.setVisible(true);
				scrollPaneBed.setVisible(true);
			}
			else{
				bedSelectionPane.setVisible(false);
				bedTableLabelPane.setVisible(false);
				scrollPaneBed.setVisible(false);
			}
			
			
			if(macs2Checkbox.isSelected() || sicerCheckbox.isSelected())
				organismGenomePane.setVisible(true);
			else
				organismGenomePane.setVisible(false);
			
			
			if(macs2Checkbox.isSelected())
				qvaluePane.setVisible(true);
			else
				qvaluePane.setVisible(false);
			
			if(sicerCheckbox.isSelected() || sppCheckbox.isSelected())
				fdrPane.setVisible(true);
			else
				fdrPane.setVisible(false);
			
			if(sppCheckbox.isSelected())
				zscorePane.setVisible(true);
			else
				zscorePane.setVisible(false);
			
			//Modify the columns of the table macs2/spp/hiddendomains: THIS IS NOT OPTIMAL
			
			if(hiddenDomainsCheckbox.isSelected() && !macs2Checkbox.isSelected() && !sppCheckbox.isSelected()){
				
				final String[] newColumnNames1 = {"Experiment", "Experiment Name", "Control Experiment", "Window Size"};
				rowDataBamTable = new EmptyObjectArray(40, 4).getRowData();
				bamFileTableModel = new CustomTableModel(rowDataBamTable, newColumnNames1);
				
			}else if(!hiddenDomainsCheckbox.isSelected() && macs2Checkbox.isSelected() && !sppCheckbox.isSelected()){
				
				final String[] newColumnNames2 = {"Experiment", "Experiment Name", "Control Experiment", "Tag-size", "Duplicate Threshold", "Elongation size"};
				rowDataBamTable = new EmptyObjectArray(40, 6).getRowData();
				bamFileTableModel = new CustomTableModel(rowDataBamTable, newColumnNames2);
				
			}else if((!hiddenDomainsCheckbox.isSelected() && !macs2Checkbox.isSelected() && sppCheckbox.isSelected()) ||
					(hiddenDomainsCheckbox.isSelected() && !macs2Checkbox.isSelected() && sppCheckbox.isSelected())){
				
				final String[] newColumnNames3 = {"Experiment", "Experiment Name", "Control Experiment", "Gap size", "Window Size"};
				rowDataBamTable = new EmptyObjectArray(40, 5).getRowData();
				bamFileTableModel = new CustomTableModel(rowDataBamTable, newColumnNames3);
				
			}else if(hiddenDomainsCheckbox.isSelected() && macs2Checkbox.isSelected() && !sppCheckbox.isSelected()){
				
				final String[] newColumnNames4 = {"Experiment", "Experiment Name", "Control Experiment", "Tag-size", "Duplicate Threshold", "Window Size", "Elongation size"};
				rowDataBamTable = new EmptyObjectArray(40, 7).getRowData();
				bamFileTableModel = new CustomTableModel(rowDataBamTable, newColumnNames4);
				
			}else{
				
				final String[] newColumnNames5 = {"Experiment", "Experiment Name", "Control Experiment", "Tag-size", "Duplicate Threshold", "Gap size", "Window Size", "Elongation size"};
				rowDataBamTable = new EmptyObjectArray(40, 8).getRowData();
				bamFileTableModel = new CustomTableModel(rowDataBamTable, newColumnNames5);
			}
			
			bamFileTable.setModel(bamFileTableModel);
			fillBamTable.setModel(bamFileTableModel);
		}
	}
	
	
	/*class FillComboFormWithFiles implements ActionListener {
		
		private Component className;
		private String format;
		private JFrame frame;
		private String formatErrorMessage;
		
		public FillComboFormWithFiles(Component className, String format, JFrame frame, String formatErrorMessage){
			
			this.className = className;
			this.format = format;
			this.frame = frame;
			this.formatErrorMessage = formatErrorMessage;
		}
		
		
		public void actionPerformed(ActionEvent e){
			
			final JFileChooser fc = new JFileChooser();
			int returnVal;
			
			fc.setMultiSelectionEnabled(true);
			returnVal = fc.showOpenDialog(className);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				
				File[] results = fc.getSelectedFiles();
				String[] filesPathes = new String[results.length];
				String[] filesNames = new String[results.length];
				int nbValidFiles = 0;
				
				//Verify proper extension
				for (int i = 0; i < results.length; i++) {
					
					String filePath = results[i].getAbsolutePath();
					String fileNameWithExtension = results[i].getName();
					String fileName = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf("."));
					int indexPoint = filePath.lastIndexOf(".");
					String extension = filePath.substring(indexPoint+1);
					
					if(!extension.equals(format)){
						JOptionPane.showMessageDialog(frame, formatErrorMessage, "Format error", JOptionPane.ERROR_MESSAGE);
						break;
					}else{
						filesPathes[i] = filePath;
						filesNames[i] = fileName;
						nbValidFiles++;
						
						if(format.equals("bam")){
							bamControlCombo = new JComboBox<String>(filesNames);
							bamFileTable.getColumn("Control Experiment").setCellEditor(new DefaultCellEditor(bamControlCombo));
						}else{
							bedControlCombo = new JComboBox<String>(filesNames);
							bedFileTable.getColumn("Control Experiment").setCellEditor(new DefaultCellEditor(bedControlCombo));
						}
					}
				}
			}
		}
	}*/
}



