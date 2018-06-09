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
import javax.swing.JTextField;

import GenomicBox.graphicClasses.actionClasses.FileSelectionLabelSet;
import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;



//This class creates a panel used to select a GFF annotation file
//It is used in the meta-profiling, 

public class FileSelectionDisplayAndNamePane extends JPanel {

	private Font police = new Police();
	private Font police2 = new Police2();
	private JLabel selectionFileLabel;
	private JButton fileSelectionButton = new JButton("Select");
	private JLabel selectedFileDisplayLabel = new JLabel("");
	private JLabel nameLabel;
	private JTextField nameField = new JTextField();
	
	
	public FileSelectionDisplayAndNamePane(int preProcWidth, int preProcHeight, 
			String selectionFileString, String nameString, 
			Component classObject, JFrame frame, String type, 
			String extensionFormat){
		
		this.selectionFileLabel = new JLabel(selectionFileString);
		this.nameLabel = new JLabel(nameString);
		selectionFileLabel.setFont(police2);
		fileSelectionButton.setFont(police2);
		fileSelectionButton.addActionListener(new FileSelectionLabelSet(classObject, 
				"Annotation file should be in " + type + " format (." + extensionFormat + ")", 
				"Format error", selectedFileDisplayLabel, nameField, frame, extensionFormat));
		selectedFileDisplayLabel.setFont(police);
		selectedFileDisplayLabel.setPreferredSize(new Dimension(preProcWidth/2, preProcHeight/20));
		selectedFileDisplayLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		selectedFileDisplayLabel.setBackground(Color.white);
		nameLabel.setFont(police2);
		nameField.setFont(police);
		nameField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		nameField.setBackground(Color.white);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		this.add(selectionFileLabel);
		this.add(fileSelectionButton);
		this.add(selectedFileDisplayLabel);
		this.add(nameLabel);
		this.add(nameField);
	}
}
