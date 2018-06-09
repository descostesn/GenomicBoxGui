package GenomicBox.graphicClasses.paneClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;
import GenomicBox.toolsTabClasses.MetaProfiling;



public class SeqplotsToolPart2Pane extends JPanel {

	private JFrame frame = new JFrame();
	private Font police = new Police();
	private Font police2 = new Police2();
	private JPanel formatInterpolationReprPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private FormatChooserPane formatChooser = new FormatChooserPane();
	private JLabel nbInterpolationLabel = new JLabel("Nb Interpolation:");
	private JTextField nbInterpolationField = new JTextField("1000");
	private JLabel representationLabel = new JLabel("Representation:");
	private String[] representationValues = {"Mean", "Median"};
	private JComboBox<String> representationCombo = new JComboBox<String>(representationValues);
	private String[] plotGroupingValues = {"Grouped", "Separated"};
	private JComboBox<String> plotGroupingCombo = new JComboBox<String>(plotGroupingValues);
	private JLabel binSizeLabel = new JLabel("Bin Size:");
	private JTextField binSizeField = new JTextField("50");
	private OutputFolderChooserPane outputFolderPane;
	private JPanel outputFolderBinsizePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	
	
	public SeqplotsToolPart2Pane(int preProcWidth, int preProcHeight, boolean showInterpolationAndRepresentation){
		
		formatInterpolationReprPane.setBackground(Color.white);
		nbInterpolationLabel.setFont(police2);
		nbInterpolationField.setFont(police);
		nbInterpolationField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		nbInterpolationField.setBackground(Color.white);
		representationLabel.setFont(police2);
		formatInterpolationReprPane.add(formatChooser);
		formatInterpolationReprPane.add(nbInterpolationLabel);
		formatInterpolationReprPane.add(nbInterpolationField);
		formatInterpolationReprPane.add(representationLabel);
		formatInterpolationReprPane.add(representationCombo);
		formatInterpolationReprPane.add(plotGroupingCombo);
		
		outputFolderPane = new OutputFolderChooserPane(SeqplotsToolPart2Pane.this, frame, preProcWidth, preProcHeight);
		outputFolderBinsizePane.setBackground(Color.white);
		binSizeLabel.setFont(police2);
		binSizeField.setFont(police);
		binSizeField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		binSizeField.setBackground(Color.white);
		outputFolderBinsizePane.add(outputFolderPane);
		outputFolderBinsizePane.add(binSizeLabel);
		outputFolderBinsizePane.add(binSizeField);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.white);
		this.add(formatInterpolationReprPane);
		this.add(outputFolderBinsizePane);
		this.setVisible(true);
	}
	
	
	public SeqplotsToolPart2Pane(int preProcWidth, int preProcHeight){
		
		formatInterpolationReprPane.setBackground(Color.white);
		formatInterpolationReprPane.add(formatChooser);
		
		outputFolderPane = new OutputFolderChooserPane(SeqplotsToolPart2Pane.this, frame, preProcWidth, preProcHeight);
		outputFolderBinsizePane.setBackground(Color.white);
		binSizeLabel.setFont(police2);
		binSizeField.setFont(police);
		binSizeField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		binSizeField.setBackground(Color.white);
		outputFolderBinsizePane.add(outputFolderPane);
		outputFolderBinsizePane.add(binSizeLabel);
		outputFolderBinsizePane.add(binSizeField);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.white);
		this.add(formatInterpolationReprPane);
		this.add(outputFolderBinsizePane);
		this.setVisible(true);
	}
}
