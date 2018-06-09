package GenomicBox.toolsTabClasses;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import GenomicBox.graphicClasses.actionClasses.FillFormWithFiles;
import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;
import GenomicBox.graphicClasses.modelClasses.CustomTableModel;
import GenomicBox.graphicClasses.paneClasses.EmptyObjectArray;
import GenomicBox.graphicClasses.paneClasses.FormatChooserPane;
import GenomicBox.graphicClasses.paneClasses.LabelAndSelectButtonPane;
import GenomicBox.graphicClasses.paneClasses.OrganismGenomePane;
import GenomicBox.graphicClasses.paneClasses.OutputFolderChooserPane;
import GenomicBox.graphicClasses.paneClasses.SubmitPane;
import GenomicBox.toolsTabClasses.PeakDetection.DisplayPane;



public class ClusterProfiler extends JPanel {
	
	private JFrame frame = new JFrame();
	private Font police = new Police();
	
	private LabelAndSelectButtonPane GFFSelectPane = new LabelAndSelectButtonPane("Genes GFF:");
	private JButton gffButton = GFFSelectPane.getButton();
	private LabelAndSelectButtonPane GMTSelectPane = new LabelAndSelectButtonPane("GMT files:");
	private JButton gmtButton = GMTSelectPane.getButton();
	private OrganismGenomePane organismGenomePane = new OrganismGenomePane();
	
	private FormatChooserPane formatPane = new FormatChooserPane();
	private JCheckBox gmtCheckbox = new JCheckBox("GMT files");
	private JPanel formatGMTPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	private OutputFolderChooserPane outputFolderPane;
	
	private Object rawDataGFF[][] = new EmptyObjectArray(40, 2).getRowData();
	private String[] columnNamesGFF = {"GFF file", "Name"};
	private CustomTableModel modelGFF = new CustomTableModel(rawDataGFF, columnNamesGFF);
    private JTable tableGFF = new JTable(modelGFF);
    private JScrollPane scrollPaneGFF = new JScrollPane(tableGFF);
    
    private Object rowDataGMT[][] = new EmptyObjectArray(40, 2).getRowData();
	private String[] columnNamesGMT = {"GMT file", "Name"};
	private CustomTableModel modelGMT = new CustomTableModel(rowDataGMT, columnNamesGMT);
    private JTable tableGMT = new JTable(modelGMT);
    private JScrollPane scrollPaneGMT = new JScrollPane(tableGMT);
	
    private SubmitPane submitPanel = new SubmitPane();
    
    
	
	public ClusterProfiler(int preProcWidth, int preProcHeight){
		
		gffButton.addActionListener(new FillFormWithFiles(ClusterProfiler.this, "gff", frame,
				"'.gff' files are expected as input", modelGFF));
		gmtButton.addActionListener(new FillFormWithFiles(ClusterProfiler.this, "gmt", frame,
				"'.gmt' files are expected as input", modelGMT));
		
		GMTSelectPane.setVisible(false);
		
		gmtCheckbox.setFont(police);
		gmtCheckbox.setBackground(Color.white);
		gmtCheckbox.setSelected(false);
		gmtCheckbox.addActionListener(new DisplayPane());
		formatGMTPane.setBackground(Color.white);
		formatGMTPane.add(formatPane);
		formatGMTPane.add(gmtCheckbox);
		
		outputFolderPane = new OutputFolderChooserPane(ClusterProfiler.this, frame, preProcWidth, preProcHeight);
		
		scrollPaneGMT.setVisible(false);
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(GFFSelectPane);
		this.add(GMTSelectPane);
		this.add(organismGenomePane);
		this.add(formatGMTPane);
		this.add(outputFolderPane);
		this.add(scrollPaneGFF);
		this.add(scrollPaneGMT);
		this.add(submitPanel);
		this.setVisible(true);
	}
	
	
	
	class DisplayPane implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			
			if(gmtCheckbox.isSelected()){
				GMTSelectPane.setVisible(true);
				scrollPaneGMT.setVisible(true);
			}else{
				GMTSelectPane.setVisible(false);
				scrollPaneGMT.setVisible(false);
			}
				
		}
	}

}
