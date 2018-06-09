package GenomicBox.graphicClasses.paneClasses;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//class creating a JPanel to enable user to indicate if the data are single or paired end

public class EndTypePane extends JPanel{

	private Font police2 = new Font("Arial", Font.BOLD, 12);
	private JRadioButton singleButton = new JRadioButton("Single-ended");
	private JRadioButton pairedButton = new JRadioButton("Paired-ended");
	private ButtonGroup bgEnded = new ButtonGroup();
	
	
	public EndTypePane(){
		
		singleButton.setFont(police2);
		pairedButton.setFont(police2);
		singleButton.setBackground(Color.white);
		pairedButton.setBackground(Color.white);
		bgEnded.add(singleButton);
		bgEnded.add(pairedButton);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		this.add(singleButton);
		this.add(pairedButton);
	}
	
	public JRadioButton getSingleButton(){
		return singleButton;
	}
	
	public JRadioButton getPairedButton(){
		return pairedButton;
	}
}
