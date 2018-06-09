package GenomicBox.graphicClasses.paneClasses;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GenomicBox.graphicClasses.formatClasses.Police2;



public class FormatChooserPane extends JPanel {

	private JLabel formatLabel = new JLabel("Format:");
	private String[] formatValue = {"png", "pdf", "ps"};
	private JComboBox<String> formatCombo = new JComboBox<String>(formatValue);
	
	public FormatChooserPane(){
		
		Font police2 = new Police2();
		formatLabel.setFont(police2);
		
		this.setBackground(Color.white);
		this.add(formatLabel);
		this.add(formatCombo);
	}
}
