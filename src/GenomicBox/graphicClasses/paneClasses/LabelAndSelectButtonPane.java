package GenomicBox.graphicClasses.paneClasses;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;


//This class creates a panel containing a label and a button.
// It is used in MetaProfiling to select Bigwigs

public class LabelAndSelectButtonPane extends JPanel {

	private Font police = new Police();
	private Font police2 = new Police2();
	private JLabel label;
	private JButton selectButton = new JButton("Select");
	
	public LabelAndSelectButtonPane(String label){
		
		this.label = new JLabel(label);
		this.label.setFont(police2);
		selectButton.setFont(police2);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		this.add(this.label);
		this.add(selectButton);
	}
	
	public JButton getButton(){
		return selectButton;
	}
}
