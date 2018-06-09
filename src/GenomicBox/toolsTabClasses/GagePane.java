package GenomicBox.toolsTabClasses;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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




public class GagePane extends JPanel {
	
	private Font police = new Police();
	private Font police2 = new Police2();
	private JFrame frame = new JFrame();
	
	private LabelAndSelectButtonPane downSelectPane = new LabelAndSelectButtonPane("Down-regulated genes:");
	private JButton downButton = downSelectPane.getButton();
	private LabelAndSelectButtonPane upSelectPane = new LabelAndSelectButtonPane("Up-regulated genes:");
	private JButton upButton = upSelectPane.getButton();
	private JComboBox<String> upFilesCombo;
	
	private LabelAndSelectButtonPane gmtSelectPane = new LabelAndSelectButtonPane("GMT files:");
	private JButton gmtButton = gmtSelectPane.getButton();
	
	private JRadioButton deseq2Choice = new JRadioButton("DESeq2");
	private JRadioButton edgeRChoice = new JRadioButton("edgeR");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel formatChoicePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	private OrganismGenomePane organismGenomePane = new OrganismGenomePane();
	private JCheckBox gmtCheckbox = new JCheckBox("GMT files");
	private JPanel organismGMTPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	private OutputFolderChooserPane outputFolderPane;
	private JLabel numberColOutputLabel = new JLabel("Nb. Col. Output:");
	private Integer[] comboNumbersOutput = new Integer[10];
	private JComboBox<Integer> numberColOutputCombo;
	private JPanel outputOptionsPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	private Object rawDataDownUpTable[][] = new EmptyObjectArray(40, 3).getRowData();
	private String[] columnNamesDownUp = {"Down file", "Output prefix", "Up file"};
	private CustomTableModel downUpTableModel = new CustomTableModel(rawDataDownUpTable, columnNamesDownUp);
	private JTable downUpTable = new JTable(downUpTableModel);
	private JScrollPane scrollPaneDownUP = new JScrollPane(downUpTable);
	
	private Object rowDataGMT[][] = new EmptyObjectArray(40, 2).getRowData();
	private String[] columnNamesGMT = {"GMT file", "Name"};
	private CustomTableModel modelGMT = new CustomTableModel(rowDataGMT, columnNamesGMT);
    private JTable tableGMT = new JTable(modelGMT);
    private JScrollPane scrollPaneGMT = new JScrollPane(tableGMT);
	
    private SubmitPane submitPanel = new SubmitPane();
    
    
    
	public GagePane(int preProcWidth, int preProcHeight){
		
		downButton.addActionListener(new FillFormWithFiles(GagePane.this, "txt", frame,
				"'.txt' files are expected as input", downUpTableModel));
		upButton.addActionListener(new FillComboFormWithFiles(GagePane.this, "txt", frame, "'.txt' files are expected as input", "Up file", 
				upFilesCombo, downUpTable));
		
		gmtButton.addActionListener(new FillFormWithFiles(GagePane.this, "gmt", frame,
				"'.gmt' files are expected as input", modelGMT));
		gmtSelectPane.setVisible(false);
		
		deseq2Choice.setFont(police2);
		edgeRChoice.setFont(police2);
		deseq2Choice.setBackground(Color.white);
		edgeRChoice.setBackground(Color.white);
		buttonGroup.add(deseq2Choice);
		buttonGroup.add(edgeRChoice);
		formatChoicePane.setBackground(Color.white);
		formatChoicePane.add(deseq2Choice);
		formatChoicePane.add(edgeRChoice);
		
		gmtCheckbox.setFont(police);
		gmtCheckbox.setBackground(Color.white);
		gmtCheckbox.addActionListener(new DisplayTable());
		organismGMTPanel.setBackground(Color.white);
		organismGMTPanel.add(organismGenomePane);
		organismGMTPanel.add(gmtCheckbox);
		
		outputFolderPane = new OutputFolderChooserPane(GagePane.this, frame, preProcWidth, preProcHeight);
		numberColOutputLabel.setFont(police2);
		numberColOutputLabel.setBackground(Color.white);
		int inc = 1;
		for (int i = 0; i < comboNumbersOutput.length; ++i) {
			comboNumbersOutput[i] = inc;
			inc++;
		}
		numberColOutputCombo = new JComboBox<Integer>(comboNumbersOutput);
		outputOptionsPane.setBackground(Color.white);
		outputOptionsPane.add(outputFolderPane);
		outputOptionsPane.add(numberColOutputLabel);
		outputOptionsPane.add(numberColOutputCombo);
		
		scrollPaneGMT.setVisible(false);
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(downSelectPane);
		this.add(upSelectPane);
		this.add(gmtSelectPane);
		this.add(formatChoicePane);
		this.add(organismGMTPanel);
		this.add(outputOptionsPane);
		this.add(scrollPaneDownUP);
		this.add(scrollPaneGMT);
		this.add(submitPanel);
		this.setVisible(true);
	}

	
	class DisplayTable implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			
			if(gmtCheckbox.isSelected()){
				gmtSelectPane.setVisible(true);
				scrollPaneGMT.setVisible(true);
			}else{
				gmtSelectPane.setVisible(false);
				scrollPaneGMT.setVisible(false);
			}
		}
	}
}
