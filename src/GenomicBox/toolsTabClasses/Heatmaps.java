package GenomicBox.toolsTabClasses;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;
import GenomicBox.graphicClasses.modelClasses.CustomTableModel;
import GenomicBox.graphicClasses.paneClasses.EmptyObjectArray;
import GenomicBox.graphicClasses.paneClasses.JRadioButtonAndShowPanelPane;
import GenomicBox.graphicClasses.paneClasses.SeqplotsToolPart1Pane;
import GenomicBox.graphicClasses.paneClasses.SeqplotsToolPart2Pane;
import GenomicBox.graphicClasses.paneClasses.SubmitPane;



public class Heatmaps extends JPanel {
	
	private Font police = new Police();
	private Font police2 = new Police2();
	private SeqplotsToolPart1Pane seqplotsToolPart1Pane;
	private JLabel nbOfGroupsLabel = new JLabel("Nb of groups:");
	private Integer[] comboNumbersData = new Integer[100];
	private JComboBox<Integer> nbOfGroupsCombo;
	private JPanel clusteringComboPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JRadioButtonAndShowPanelPane clusteringChoicePane = new JRadioButtonAndShowPanelPane("K-mean", "Hierarchical", "None", clusteringComboPane);
	private JPanel clusteringTypePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JRadioButtonAndShowPanelPane increasingDecreasingNonePane = new JRadioButtonAndShowPanelPane("Increasing", "Decreasing", "None");
	private JCheckBox autoScale = new JCheckBox("AutoScale");
	private JLabel minScaleLabel = new JLabel("Min. Scale:");
	private JLabel maxScaleLabel = new JLabel("Max. Scale:");
	private JComboBox<Integer> minScaleNbCombo;
	private JComboBox<Integer> maxScaleNbCombo;
	private JPanel minMaxScalePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel autoScalePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private SeqplotsToolPart2Pane seqplotsToolPart2Pane;
	private JLabel plotScaleLabel = new JLabel("Plot scale:");
	private String[] scaleTypeTab = {"None", "Log", "Linear", "Z-score"};
	private JComboBox<String> scaleCombo = new JComboBox<String>(scaleTypeTab);
	private JLabel colorLabel = new JLabel("Color:");
	private JButton colorButton = new JButton();
	private JPanel scaleColorPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private Object rowData[][] = new EmptyObjectArray(20, 3).getRowData();
	private String[] columnNames = {"Bigwig", "Name", "Use for Clustering"};
	private CustomTableModel model = new CustomTableModel(rowData, columnNames);
	private JTable filesTable = new JTable(model);
	private JScrollPane scrollPane = new JScrollPane(filesTable);
	
	private String[] includeExpComboData = {"FALSE", "TRUE"};
	private JComboBox<String> includeExpCombo = new JComboBox<String>(includeExpComboData); 
	private SubmitPane submitPane = new SubmitPane();
	
	public Heatmaps(int preProcWidth, int preProcHeight){
		
		seqplotsToolPart1Pane = new SeqplotsToolPart1Pane(preProcWidth, preProcHeight, "Heatmaps", model, "bw", true, "Bigwigs");
		nbOfGroupsLabel.setFont(police2);
		nbOfGroupsLabel.setBackground(Color.white);
		
		int inc = 1;
		for (int i = 0; i < comboNumbersData.length; ++i) {
			comboNumbersData[i] = inc;
			inc++;
		}
		
		nbOfGroupsCombo = new JComboBox<Integer>(comboNumbersData);
		clusteringChoicePane.setBackground(Color.white);
		clusteringComboPane.setBackground(Color.white);
		clusteringTypePane.setBackground(Color.white);
		clusteringComboPane.add(nbOfGroupsLabel);
		clusteringComboPane.add(nbOfGroupsCombo);
		clusteringTypePane.add(clusteringChoicePane);
		clusteringTypePane.add(clusteringComboPane);
		clusteringComboPane.setVisible(false);
		
		minScaleNbCombo = new JComboBox<Integer>(comboNumbersData);
		maxScaleNbCombo = new JComboBox<Integer>(comboNumbersData);
		minScaleLabel.setFont(police2);
		maxScaleLabel.setFont(police2);
		minScaleLabel.setBackground(Color.white);
		maxScaleLabel.setBackground(Color.white);
		minMaxScalePane.setBackground(Color.white);
		minMaxScalePane.add(minScaleLabel);
		minMaxScalePane.add(minScaleNbCombo);
		minMaxScalePane.add(maxScaleLabel);
		minMaxScalePane.add(maxScaleNbCombo);
		autoScale.setFont(police2);
		autoScale.setBackground(Color.white);
		autoScale.setSelected(true);
		autoScale.addActionListener(new showScaleCombo());
		autoScalePane.setBackground(Color.white);
		autoScalePane.add(autoScale);
		autoScalePane.add(minMaxScalePane);
		minMaxScalePane.setVisible(false);
		
		seqplotsToolPart2Pane = new SeqplotsToolPart2Pane(preProcWidth, preProcHeight);
		
		plotScaleLabel.setFont(police2);
		plotScaleLabel.setBackground(Color.white);
		colorLabel.setFont(police2);
		colorLabel.setBackground(Color.white);
		colorButton.setBackground(Color.blue);
		colorButton.addActionListener(new ChooseColorHeatmap());
		scaleColorPane.setBackground(Color.white);
		scaleColorPane.add(plotScaleLabel);
		scaleColorPane.add(scaleCombo);
		scaleColorPane.add(colorLabel);
		scaleColorPane.add(colorButton);
		
		filesTable.getColumn("Use for Clustering").setCellEditor(new DefaultCellEditor(includeExpCombo));
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(seqplotsToolPart1Pane);
		this.add(clusteringTypePane);
		this.add(increasingDecreasingNonePane);
		this.add(autoScalePane);
		this.add(seqplotsToolPart2Pane);
		this.add(scaleColorPane);
		this.add(scrollPane);
		this.add(submitPane);
		this.setVisible(true);
	}
	
	
	class showScaleCombo implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == autoScale){
				if(autoScale.isSelected())
					minMaxScalePane.setVisible(false);
				else
					minMaxScalePane.setVisible(true);
			}
		}
	}
	
	
	class ChooseColorHeatmap implements ActionListener{
		
		private Color colorSelected = Color.blue;
		
		public void actionPerformed(ActionEvent e){
			
			colorSelected = JColorChooser.showDialog(colorButton, "Choose a color", colorSelected);
            colorButton.setBackground(colorSelected);
		}
	}
}



