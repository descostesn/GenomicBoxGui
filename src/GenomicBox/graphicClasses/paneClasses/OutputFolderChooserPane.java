package GenomicBox.graphicClasses.paneClasses;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GenomicBox.graphicClasses.actionClasses.FileSelectionLabelSet;
import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;



public class OutputFolderChooserPane extends JPanel {

	private Font police = new Police();
	private Font police2 = new Police2();
	private JLabel outputfolderLabel = new JLabel("Output folder:");
	private JButton outputFolderButton = new JButton("Select");
	private JLabel outputfolderDisplay = new JLabel("");
	
	public OutputFolderChooserPane(Component className, JFrame frame, 
			int preProcWidth, int preProcHeight){
		
		outputfolderLabel.setFont(police2);
		outputFolderButton.setFont(police2);
		outputFolderButton.addActionListener(new FileSelectionLabelSet(className, 
				outputfolderDisplay, frame));
		outputfolderDisplay.setFont(police);
		outputfolderDisplay.setPreferredSize(new Dimension(preProcWidth/2, preProcHeight/20));
		outputfolderDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
		outputfolderDisplay.setBackground(Color.white);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		this.add(outputfolderLabel);
		this.add(outputFolderButton);
		this.add(outputfolderDisplay);
	}
}
