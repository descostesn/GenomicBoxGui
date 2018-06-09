package GenomicBox.graphicClasses.paneClasses;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import GenomicBox.graphicClasses.formatClasses.Police2;




public class SubmitPane extends JPanel {

	private JButton submitButton = new JButton("Submit");
	
	
	public SubmitPane(){
		
		this.setBackground(Color.white);
		submitButton.setBackground(Color.white);
		submitButton.setFont(new Police2());
		this.add(submitButton);
	}
	
	public JButton getButton(){
		return submitButton;
	}
}
